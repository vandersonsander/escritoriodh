package com.br.dh.escritorio.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.br.dh.escritorio.model.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, String>{
	
	Iterable<Pedido> findByNfe(String nfe);

}
