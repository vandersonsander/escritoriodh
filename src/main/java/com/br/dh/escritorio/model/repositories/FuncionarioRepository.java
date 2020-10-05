package com.br.dh.escritorio.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.br.dh.escritorio.model.entities.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

}
