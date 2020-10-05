package com.br.dh.escritorio.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "folha_de_ponto")
@JsonIgnoreProperties({"funcionario"})
public class FolhaDePonto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_folha_de_ponto")
	private int idFolhaDePonto;

	@Column(name = "data_registro")
	@CreationTimestamp
	private LocalDate dataRegistro;
	
	@Column(name = "hora_entrada")
	@CreationTimestamp
	private LocalDateTime horaEntrada;
	
	@Column(name = "hora_saida", nullable = true)
	private LocalDateTime horaSaida;
	
	private transient int idFuncionario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funcionario")
	@JsonIgnoreProperties({"folhaDePonto"})
	private Funcionario funcionario;
	
	public FolhaDePonto() {}

	public FolhaDePonto(LocalDate dataRegistro, LocalDateTime horaEntrada,
			LocalDateTime horaSaida, int idFuncionario, Funcionario funcionario) {
		this.dataRegistro = dataRegistro;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.idFuncionario = idFuncionario;
		this.funcionario = funcionario;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalDateTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalDateTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public int getIdFuncionario() {
		return 	this.idFuncionario <= 0 ? this.funcionario.getIdFuncionario()
				: 	this.idFuncionario ;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public int getIdFolhaDePonto() {
		return idFolhaDePonto;
	}

}
