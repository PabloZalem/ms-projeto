package com.zalempablo.project.trabalhador.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trabalhador")
public class Trabalhador implements Serializable {

	private static final long serialVersionUID = 1L; //Transformar a classe em bytes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double ganhaPorDia;

	public Trabalhador() {
	}

	public Trabalhador(Long id, String nome, Double ganhaPorDia) {
		super();
		this.id = id;
		this.nome = nome;
		this.ganhaPorDia = ganhaPorDia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabalhador other = (Trabalhador) obj;
		return Objects.equals(id, other.id);
	}

}
