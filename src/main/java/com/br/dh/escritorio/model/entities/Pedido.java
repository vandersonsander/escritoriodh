package com.br.dh.escritorio.model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Pedido {
	
	@Id
	private String nfe;
	
	@Column(name = "data_emissao")
	private LocalDate dataEmissao;
	
	@Column(name = "valor_total")
	private double valorTotal;
	
	private String descricao;
	
	@Column(name = "status_pedido")
	private String statusPedido;
	private transient int idCliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties({"pedido"})
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnoreProperties({"pedido"})
	private List<ProdPedido> prodPedido; 
	
	public Pedido() {}

	public Pedido(String nfe, LocalDate dataEmissao, double valorTotal,
			String descricao, String statusPedido, int idCliente) {
		this.nfe = nfe;
		this.dataEmissao = dataEmissao;
		this.valorTotal = valorTotal;
		this.descricao = descricao;
		this.statusPedido = statusPedido;
		this.idCliente = idCliente;
	}

	public String getNfe() {
		return nfe;
	}

	public void setNfe(String nfe) {
		this.nfe = nfe;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = LocalDate.parse(dataEmissao);
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
