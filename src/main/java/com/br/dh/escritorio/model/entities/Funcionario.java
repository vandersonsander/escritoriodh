package com.br.dh.escritorio.model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionario")
	private int idFuncionario;
	private String nome;
	private String cpf;
	private String rg;
	private String ctps;
	private double salario;
	private String email;
	private String senha;
	private int ativo;
	
	@Column(name = "data_admissao")
	private LocalDate dataAdmissao;
	
	@Column(name = "data_demissao")
	private LocalDate dataDemissao;
	
	@OneToMany(mappedBy = "funcionario")
	@JsonIgnoreProperties({"funcionario"})
	private List<FolhaDePonto> folhaDePonto;
	
	@OneToMany(mappedBy = "funcionario")
	@JsonIgnoreProperties({"funcionario"})
	private List<Endereco> enderecos;
	
	@OneToMany(mappedBy = "funcionario")
	@JsonIgnoreProperties({"funcionario"})
	private List<Telefone> telefones;
	
	@OneToOne(mappedBy = "funcionario")
	@JsonIgnoreProperties({"funcionario"})
	private Gerente gerente;
	
	public Funcionario() {}

	public Funcionario(int idFuncionario, String nome, String cpf, String rg,
			String email, String senha, int ativo, String ctps, double salario,
			LocalDate dataAdmissao, LocalDate dataDemissao) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.ctps = ctps;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
		this.dataDemissao = dataDemissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		if (salario < this.salario) return;
		this.salario = salario;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public List<FolhaDePonto> getFolhaDePonto() {
		return folhaDePonto;
	}

	public void setFolhaDePonto(List<FolhaDePonto> folhaDePonto) {
		this.folhaDePonto = folhaDePonto;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerentes) {
		this.gerente = gerentes;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}
}
