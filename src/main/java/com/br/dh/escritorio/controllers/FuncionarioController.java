package com.br.dh.escritorio.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.dh.escritorio.model.entities.Funcionario;
import com.br.dh.escritorio.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public Iterable<Funcionario> getFuncionarios() {
		return funcionarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Funcionario> getFuncionario(@PathVariable int id) {
		return funcionarioRepository.findById(id);
	}
	
	@PostMapping
	public Funcionario addFuncionario(@RequestBody Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return funcionario;
	}
	
	@DeleteMapping("/{id}")
	public void removeCliente(@PathVariable int id) {
		funcionarioRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Funcionario updateFuncionario(@RequestBody Funcionario funcionario,
			@PathVariable int id) throws Exception {
		Funcionario currentFuncionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new Exception());

		if (funcionario.getNome() != null
				&& funcionario.getNome() != currentFuncionario.getNome())
			currentFuncionario.setNome(funcionario.getNome());
		if (funcionario.getCpf() != null
				&& funcionario.getCpf() != currentFuncionario.getCpf())
			currentFuncionario.setCpf(funcionario.getCpf());
		if (funcionario.getRg() != null
				&& funcionario.getRg() != currentFuncionario.getRg())
			currentFuncionario.setRg(funcionario.getRg());
		if (funcionario.getCtps() != null
				&& funcionario.getCtps() != currentFuncionario.getCtps())
			currentFuncionario.setCtps(funcionario.getCtps());
		if (funcionario.getSalario() > 0
				&& funcionario.getSalario() != currentFuncionario.getSalario())
			currentFuncionario.setSalario(funcionario.getSalario());
		if (funcionario.getEmail() != null
				&& funcionario.getEmail() != currentFuncionario.getEmail())
			currentFuncionario.setEmail(funcionario.getEmail());
		if (funcionario.getSenha() != null
				&& funcionario.getSenha() != currentFuncionario.getSenha())
			currentFuncionario.setSenha(funcionario.getSenha());
		if (funcionario.getDataAdmissao() != null
				&& funcionario.getDataAdmissao() != currentFuncionario.getDataAdmissao())
			currentFuncionario.setDataAdmissao(funcionario.getDataAdmissao());
		if (funcionario.getDataDemissao() != null
				&& funcionario.getDataDemissao() != currentFuncionario.getDataDemissao())
			currentFuncionario.setDataDemissao(funcionario.getDataDemissao());
		
		funcionarioRepository.save(currentFuncionario);
		
		return currentFuncionario;
	}

}
