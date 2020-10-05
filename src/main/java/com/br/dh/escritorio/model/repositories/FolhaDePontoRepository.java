package com.br.dh.escritorio.model.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.br.dh.escritorio.model.entities.FolhaDePonto;
import com.br.dh.escritorio.model.entities.Funcionario;

public interface FolhaDePontoRepository extends CrudRepository<FolhaDePonto, Integer> {
	List<FolhaDePonto> findByDataRegistro(LocalDate dataRegistro);
	
	List<FolhaDePonto> findByFuncionario(Funcionario funcionario);
	
	FolhaDePonto findOneByDataRegistroAndFuncionario(
			LocalDate dataRegistro, Funcionario funcionario);
	
	FolhaDePonto findOneByDataRegistroAndFuncionarioAndHoraSaida (
			LocalDate dataRegistro, Funcionario funcionario, LocalDate horaSaida);
}
