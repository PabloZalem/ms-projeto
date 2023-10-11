package com.zalempablo.project.folhadepagamento.service;

import org.springframework.stereotype.Service;

import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;

@Service
public class FolhaDePagamentoService {
	
	public FolhaDePagamento getPagamento(long workerId, int dias) {
		return new FolhaDePagamento("Bob",200.0, dias);
	}
}
