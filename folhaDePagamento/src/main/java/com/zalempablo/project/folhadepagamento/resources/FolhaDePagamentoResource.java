package com.zalempablo.project.folhadepagamento.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;
import com.zalempablo.project.folhadepagamento.service.FolhaDePagamentoService;

@RestController
@RequestMapping(value = "/folhadepagamentos")
public class FolhaDePagamentoResource {
	
	@Autowired
	private FolhaDePagamentoService folhaDePagamentoService;
	
	@HystrixCommand(fallbackMethod = "getPagamentoAlternativo")
	@GetMapping(value = "/{trabalhador}/dias/{dias}")
	public ResponseEntity<FolhaDePagamento> getPagamento(@PathVariable Long trabalhador
														,@PathVariable Integer dias){
		/*
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		FolhaDePagamento folhaDePagamento = folhaDePagamentoService.getPagamento(trabalhador, dias);
		return ResponseEntity.ok(folhaDePagamento);
	}
	
	public ResponseEntity<FolhaDePagamento> getPagamentoAlternativo(Long trabalhador, Integer dias) {
		FolhaDePagamento payment = new FolhaDePagamento("Brann", 400.0, dias);
		return ResponseEntity.ok(payment);
	}

}
