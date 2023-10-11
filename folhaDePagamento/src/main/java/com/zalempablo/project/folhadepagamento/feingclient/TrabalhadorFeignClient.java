package com.zalempablo.project.folhadepagamento.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zalempablo.project.folhadepagamento.entities.Trabalhador;

@Component
@FeignClient(name="trabalhador"
			,path="/trabalhadores")
public interface TrabalhadorFeignClient {

	@GetMapping(value = "/{id}")
	public ResponseEntity<Trabalhador> findById(@PathVariable Long id);
}
