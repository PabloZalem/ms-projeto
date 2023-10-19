package com.zalempablo.project.oauth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zalempablo.project.oauth.entities.Usuarios;

@Component
@FeignClient(name = "usuario", path = "/usuarios")
public interface UsuarioFeignClient  {

	@GetMapping(value = "/pesquisa")
	ResponseEntity<Usuarios> findByEmail(@RequestParam String email);

}
