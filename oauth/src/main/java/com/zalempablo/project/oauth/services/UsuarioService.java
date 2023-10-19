package com.zalempablo.project.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zalempablo.project.oauth.entities.Usuarios;
import com.zalempablo.project.oauth.feign.UsuarioFeignClient;

@Service
public class UsuarioService implements UserDetailsService{

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioFeignClient usuarioFeignClient;
	
	public Usuarios findByEmail(String string){
		Usuarios usuarios = usuarioFeignClient.findByEmail(string).getBody();
		if(usuarios == null) {
			logger.error("Email nao encontrado" + string);
			throw new IllegalArgumentException("Email nao encontrado");
		}
		logger.info("Email encontrado" +  string);
		return usuarios;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuarios = usuarioFeignClient.findByEmail(username).getBody();
		if(usuarios == null) {
			logger.error("Email nao encontrado" + username);
			throw new UsernameNotFoundException("Email nao encontrado");
		}
		logger.info("Email encontrado" +  username);
		return usuarios;
	}
}
