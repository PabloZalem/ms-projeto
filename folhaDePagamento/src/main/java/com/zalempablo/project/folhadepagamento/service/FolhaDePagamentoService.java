package com.zalempablo.project.folhadepagamento.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;
import com.zalempablo.project.folhadepagamento.entities.Trabalhador;

@Service
public class FolhaDePagamentoService {
	
	@Value("${trabalhador.host}")
	private String trabalhador;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public FolhaDePagamento getPagamento(long workerId, int dias) {
		Map<String, String> variaveiesUri = new HashMap<>();
		variaveiesUri.put("id", ""+workerId);
		
		Trabalhador trabalhadores = restTemplate.getForObject(trabalhador+"/trabalhadores/{id}",Trabalhador.class, variaveiesUri);
		return new FolhaDePagamento(trabalhadores.getNome(),trabalhadores.getGanhaPorDia(), dias);
	}
}
