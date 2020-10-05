package com.br.dh.escritorio.model.repositories;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.br.dh.escritorio.model.entities.ProdPedido;
import com.br.dh.escritorio.model.entities.ProdPedidoPK;

public interface ProdPedidoRepository extends CrudRepository<ProdPedido, ProdPedidoPK> {
	
	Optional<ProdPedido> findOneById(ProdPedidoPK id);

	List<ProdPedido> findByIdIdProduto(int id_produto);
	List<ProdPedido> findByIdNfe(String nfe);
	
}
