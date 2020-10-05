package com.br.dh.escritorio.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.dh.escritorio.model.entities.FolhaDePonto;
import com.br.dh.escritorio.model.entities.Funcionario;
import com.br.dh.escritorio.model.repositories.FolhaDePontoRepository;
import com.br.dh.escritorio.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/ponto")
public class FolhaDePontoController {
	@Autowired
	private FolhaDePontoRepository folhaDePontoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public Iterable<FolhaDePonto> getAll() {
		return folhaDePontoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public List<FolhaDePonto> getByIdFuncionario(@PathVariable int id) 
		throws Exception {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new Exception()); 
		return folhaDePontoRepository.findByFuncionario(funcionario);
	}
	
	@GetMapping("/data")
	public List<FolhaDePonto> getByDataRegistro(@RequestParam int dia,
			@RequestParam int mes, @RequestParam int ano) {
		return folhaDePontoRepository.findByDataRegistro(LocalDate.of(ano, mes, dia));
	}
	
	// O registro da entrada é feito apenas passando o id do funcionário
	@PutMapping("/registrar-entrada/{id}")
	public FolhaDePonto registrarEntrada(@PathVariable int id) throws Exception {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new Exception());
		FolhaDePonto folhaDePonto = new FolhaDePonto();
		folhaDePonto.setFuncionario(funcionario);
		folhaDePontoRepository.save(folhaDePonto);
		return folhaDePonto;
	}
	
	
	@PutMapping("/registrar-saida/{id}")
	public FolhaDePonto registrarSaida(@PathVariable int id)  throws Exception {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new Exception());
		// O registro da saída é permitido apenas para o dia de hoje e para quem a hora da
		// saída seja igual a null
		FolhaDePonto folhaDePonto = folhaDePontoRepository
				.findOneByDataRegistroAndFuncionarioAndHoraSaida (
						LocalDate.now(), funcionario, null);
		folhaDePonto.setHoraSaida(LocalDateTime.now());
		folhaDePontoRepository.save(folhaDePonto);
		return folhaDePonto;
	}

}
