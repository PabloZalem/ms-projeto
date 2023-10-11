package com.zalempablo.project.folhadepagamento.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;
import com.zalempablo.project.folhadepagamento.entities.Trabalhador;
import com.zalempablo.project.folhadepagamento.feingclient.TrabalhadorFeignClient;

@Service
public class FolhaDePagamentoService {

	@Autowired
	private TrabalhadorFeignClient trabalhadorFeignClient;

	public FolhaDePagamento getPagamento(long workerId, int dias) {

		Trabalhador trabalhadores = trabalhadorFeignClient.findById(workerId).getBody();
		return new FolhaDePagamento(trabalhadores.getNome(), trabalhadores.getGanhaPorDia(), dias);
	}
}
