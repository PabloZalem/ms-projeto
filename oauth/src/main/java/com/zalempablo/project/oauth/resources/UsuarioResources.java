package com.zalempablo.project.oauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zalempablo.project.oauth.entities.Usuarios;
import com.zalempablo.project.oauth.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResources {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/pesquisa")
	public ResponseEntity<Usuarios> findByEmail(@RequestParam String email){
		try {
		Usuarios usuarios = usuarioService.findByEmail(email);
		return ResponseEntity.ok(usuarios);
		}catch(IllegalArgumentException argumentException) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
