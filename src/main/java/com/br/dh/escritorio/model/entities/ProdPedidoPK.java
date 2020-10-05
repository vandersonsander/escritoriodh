package com.br.dh.escritorio.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdPedidoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "id_produto")
	private int idProduto;
	private String nfe;
	
	public ProdPedidoPK() {}

	public ProdPedidoPK(int idProduto, String nfe) {
		this.idProduto = idProduto;
		this.nfe = nfe;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNfe() {
		return nfe;
	}

	public void setNfe(String nfe) {
		this.nfe = nfe;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_produto;
		result = prime * result + ((nfe == null) ? 0 : nfe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdPedidoPK other = (ProdPedidoPK) obj;
		if (id_produto != other.id_produto)
			return false;
		if (nfe == null) {
			if (other.nfe != null)
				return false;
		} else if (!nfe.equals(other.nfe))
			return false;
		return true;
	}//*/

	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}//*/
	
	
}
