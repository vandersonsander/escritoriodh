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
import com.br.dh.escritorio.model.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public Iterable<Cliente> getClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Cliente> getCliente(@PathVariable int id) {
		return clienteRepository.findById(id);
	}
	
	@PostMapping
	public Cliente addCliente(@RequestBody Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@DeleteMapping("/{id}")
	public void removeCliente(@PathVariable int id) {
		clienteRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Cliente updateCliente(@RequestBody Cliente cliente, @PathVariable int id)
		throws Exception {
		Cliente currentCliente = clienteRepository.findById(id)
				.orElseThrow(() -> new Exception());
		
		if (cliente.getNome() != null && cliente.getNome() != currentCliente.getNome())
			currentCliente.setNome(cliente.getNome());
		if (cliente.getEmail() != null && cliente.getEmail() != currentCliente.getEmail())
			currentCliente.setEmail(cliente.getEmail());
		if (cliente.getCpf() != null && cliente.getCpf() != currentCliente.getCpf())
			currentCliente.setCpf(cliente.getCpf());
		
		clienteRepository.save(currentCliente);
		
		return currentCliente;
	}
	
}
