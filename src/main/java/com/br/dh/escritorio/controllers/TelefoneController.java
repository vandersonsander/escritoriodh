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

import com.br.dh.escritorio.model.entities.Cliente;
import com.br.dh.escritorio.model.entities.Funcionario;
import com.br.dh.escritorio.model.entities.Telefone;
import com.br.dh.escritorio.model.repositories.ClienteRepository;
import com.br.dh.escritorio.model.repositories.FuncionarioRepository;
import com.br.dh.escritorio.model.repositories.TelefoneRepository;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public Iterable<Telefone> getTelefones() {
		return telefoneRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Telefone> getTelefone(@PathVariable int id) {
		return telefoneRepository.findById(id);
	}
	
	@PostMapping
	public Telefone addTelefone(@RequestBody Telefone telefone) throws Exception {
		Cliente cliente;
		Funcionario funcionario;
		
		if(telefone.getIdCliente() > 0) {
			 cliente = clienteRepository.findById(telefone.getIdCliente())
					 .orElseThrow(() -> new Exception());
			 telefone.setCliente(cliente);
		}
		if(telefone.getIdFuncionario() > 0) {
			funcionario = funcionarioRepository.findById(telefone.getIdFuncionario())
					 .orElseThrow(() -> new Exception());
			 telefone.setFuncionario(funcionario);
		}

		telefoneRepository.save(telefone);
		return telefone;
	}
	
	@DeleteMapping("/{id}")
	public void removeTelefone(@PathVariable int id) {
		telefoneRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Telefone updateTelefone(@RequestBody Telefone telefone, @PathVariable int id)
			throws Exception {
		Telefone currentTelefone = telefoneRepository.findById(id)
				.orElseThrow(() -> new Exception());
		Cliente cliente = clienteRepository.findById(telefone.getIdCliente())
				.orElseThrow(() -> new Exception());
		Funcionario funcionario = funcionarioRepository.findById(telefone.getIdFuncionario())
				.orElseThrow(() -> new Exception());
		
		telefone.setCliente(cliente);
		telefone.setFuncionario(funcionario);
		
		if (telefone.getIdCliente() > 0
				&& telefone.getIdCliente() != currentTelefone.getIdCliente())
			currentTelefone.setCliente(telefone.getCliente());
		if (telefone.getIdFuncionario() > 0
				&& telefone.getIdFuncionario() != currentTelefone.getIdFuncionario())
			currentTelefone.setFuncionario(telefone.getFuncionario());
		if (telefone.getDdd() != null
				&& telefone.getDdd() != currentTelefone.getDdd())
			currentTelefone.setDdd(telefone.getDdd());
		if (telefone.getNumero() != null
				&& telefone.getNumero() != currentTelefone.getNumero())
			currentTelefone.setNumero(telefone.getNumero());
		
		telefoneRepository.save(currentTelefone);
		return telefone;
	}

}
