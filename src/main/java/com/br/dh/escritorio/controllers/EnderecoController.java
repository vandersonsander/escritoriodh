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
import com.br.dh.escritorio.model.entities.Endereco;
import com.br.dh.escritorio.model.entities.Funcionario;
import com.br.dh.escritorio.model.repositories.ClienteRepository;
import com.br.dh.escritorio.model.repositories.EnderecoRepository;
import com.br.dh.escritorio.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public Iterable<Endereco> getEnderecos() {
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Endereco> getEndereco(@PathVariable int id) {
		return enderecoRepository.findById(id);
	}
	
	@PostMapping
	public Endereco addEndereco(@RequestBody Endereco endereco)
			throws Exception {
		Cliente cliente;
		Funcionario funcionario;
		
		if(endereco.getIdCliente() > 0) {
			 cliente = clienteRepository.findById(endereco.getIdCliente())
					 .orElseThrow(() -> new Exception());
			 endereco.setCliente(cliente);
		}
		if(endereco.getIdFuncionario() > 0) {
			funcionario = funcionarioRepository.findById(endereco.getIdFuncionario())
					 .orElseThrow(() -> new Exception());
			endereco.setFuncionario(funcionario);
		}
		
		enderecoRepository.save(endereco);
		return endereco;
	}
	
	@DeleteMapping("/{id}")
	public void removeEndereco(@PathVariable int id) {
		enderecoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Endereco getEndereco(@PathVariable int id, @RequestBody Endereco endereco) 
		throws Exception {
		Endereco currentEndereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new Exception());
		Cliente cliente = clienteRepository.findById(endereco.getIdCliente())
				.orElseThrow(() -> new Exception());
		Funcionario funcionario = funcionarioRepository.findById(endereco.getIdFuncionario())
				.orElseThrow(() -> new Exception());
		
		endereco.setCliente(cliente);
		endereco.setFuncionario(funcionario);
		
		if (endereco.getIdCliente() > 0
				&& endereco.getIdCliente() != currentEndereco.getIdCliente())
			currentEndereco.setCliente(endereco.getCliente());
		if (endereco.getIdFuncionario() > 0
				&& endereco.getIdFuncionario() != currentEndereco.getIdFuncionario())
			currentEndereco.setFuncionario(endereco.getFuncionario());
		
		if (endereco.getLogradouro() != null
				&& endereco.getLogradouro() != currentEndereco.getLogradouro())
			currentEndereco.setLogradouro(endereco.getLogradouro());
		
		if (endereco.getNumero() != null
				&& endereco.getNumero() != currentEndereco.getNumero())
			currentEndereco.setNumero(endereco.getNumero());
		
		if (endereco.getComplemento() != null
				&& endereco.getComplemento() != currentEndereco.getComplemento())
			currentEndereco.setComplemento(endereco.getComplemento());
		
		if (endereco.getCep() != null
				&& endereco.getCep() != currentEndereco.getCep())
			currentEndereco.setCep(endereco.getCep());
		
		if (endereco.getCidade() != null
				&& endereco.getCidade() != currentEndereco.getCidade())
			currentEndereco.setCidade(endereco.getCidade());
		
		if (endereco.getEstado() != null
				&& endereco.getEstado() != currentEndereco.getEstado())
			currentEndereco.setEstado(endereco.getEstado());
		
		enderecoRepository.save(currentEndereco);
		return endereco;
	}
}
