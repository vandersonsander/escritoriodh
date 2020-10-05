package com.br.dh.escritorio.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Gerente {
	@Id
	@Column(name = "id_gerente")
	private int idGerente;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gerente")
	@JsonIgnoreProperties({"gerente"})
	private Funcionario funcionario;
	
	public Gerente() {}

	public Gerente(int idGerente) {
		this.idGerente = idGerente;
	}

	public int getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(int idGerente) {
		this.idGerente = idGerente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
