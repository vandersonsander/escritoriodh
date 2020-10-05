package com.br.dh.escritorio.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.br.dh.escritorio.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

}
