package com.br.dh.escritorio.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.dh.escritorio.model.entities.Funcionario;
import com.br.dh.escritorio.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/gerente")
public class GerenteController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@DeleteMapping("/demicao/{id}")
	public String demitirFuncionario(@PathVariable int id,
			HttpServletResponse response) throws Exception {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new Exception());
		if (funcionario.getAtivo() <= 0) {
			response.setStatus(401);
			return funcionario.getNome() + " já foi demitido";
		}
		funcionario.setAtivo(0);
		funcionario.setDataDemissao(LocalDate.now());
		funcionarioRepository.save(funcionario);
		return funcionario.getNome() + " está demitido";
	}
	
	@PutMapping("/reajuste-salario/{id}")
	public String reajustarSalario(@RequestBody Funcionario funcionario,
			@PathVariable int id, HttpServletResponse response)
					throws Exception {
		Funcionario currentFuncionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new Exception());
		if (currentFuncionario.getAtivo() <= 0) {
			response.setStatus(401);
			return "Funcionário foi demitido";
		}
		System.out.println(funcionario.getSalario());
		if (funcionario.getSalario() > 0
				&& funcionario.getSalario() != currentFuncionario.getSalario())
			currentFuncionario.setSalario(funcionario.getSalario());
		
		funcionarioRepository.save(currentFuncionario);
		
		return currentFuncionario.getNome() + 
				"\nNovo Salário: R$" + currentFuncionario.getSalario();
	}

}
