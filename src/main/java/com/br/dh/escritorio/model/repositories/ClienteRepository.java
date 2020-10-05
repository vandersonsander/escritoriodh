package com.br.dh.escritorio.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.br.dh.escritorio.model.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
