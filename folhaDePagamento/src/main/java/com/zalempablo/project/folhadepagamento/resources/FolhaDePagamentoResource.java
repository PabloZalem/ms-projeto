package com.zalempablo.project.folhadepagamento.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;
import com.zalempablo.project.folhadepagamento.service.FolhaDePagamentoService;

@RestController
@RequestMapping(value = "/folhadepagamentos")
public class FolhaDePagamentoResource {
	
	@Autowired
	private FolhaDePagamentoService folhaDePagamentoService;
	
	
	@GetMapping(value = "/{trabalhador}/dias/{dias}")
	public ResponseEntity<FolhaDePagamento> getPagamento(@PathVariable Long trabalhador
														,@PathVariable Integer dias){
		FolhaDePagamento folhaDePagamento = folhaDePagamentoService.getPagamento(trabalhador, dias);
		return ResponseEntity.ok(folhaDePagamento);
	}
	
	public ResponseEntity<FolhaDePagamento> getPaymentAlternative(Long workerId, Integer days) {
		FolhaDePagamento payment = new FolhaDePagamento("Brann", 400.0, days);
		return ResponseEntity.ok(payment);
	}
}
