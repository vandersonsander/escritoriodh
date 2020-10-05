package com.br.dh.escritorio.model.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ProdPedido {
	
	@EmbeddedId
	private ProdPedidoPK id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", insertable = false, updatable = false)
	@JsonIgnoreProperties({"prodPedido"})
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nfe", insertable = false, updatable = false)
	@JsonIgnoreProperties({"prodPedido"})
	private Pedido pedido;
	
	private int quantidade;
	
	public ProdPedido() {}

	public ProdPedido(ProdPedidoPK id, Produto produto, Pedido pedido,
			int quantidade) {
		this.id = id;
		this.produto = produto;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}

	public ProdPedidoPK getId() {
		return id;
	}

	public void setId(ProdPedidoPK id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
