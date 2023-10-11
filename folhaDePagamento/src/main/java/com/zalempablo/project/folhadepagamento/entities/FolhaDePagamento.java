package com.zalempablo.project.folhadepagamento.entities;

import java.io.Serializable;

public class FolhaDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private Double ganhaPorDia;
	private Integer diasTrablhados;

	public FolhaDePagamento() {
	}

	public FolhaDePagamento(String nome, Double ganhaPorDia, Integer diasTrablhados) {
		super();
		this.nome = nome;
		this.ganhaPorDia = ganhaPorDia;
		this.diasTrablhados = diasTrablhados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getGanhaPorDia() {
		return ganhaPorDia;
	}

	public void setGanhaPorDia(Double ganhaPorDia) {
		this.ganhaPorDia = ganhaPorDia;
	}

	public Integer getDiasTrablhados() {
		return diasTrablhados;
	}

	public void setDiasTrablhados(Integer diasTrablhados) {
		this.diasTrablhados = diasTrablhados;
	}

	public double getTotal() {
		return ganhaPorDia * diasTrablhados;
	}
}
