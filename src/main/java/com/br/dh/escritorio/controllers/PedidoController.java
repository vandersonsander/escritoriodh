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
import com.br.dh.escritorio.model.entities.Pedido;
import com.br.dh.escritorio.model.repositories.ClienteRepository;
import com.br.dh.escritorio.model.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public Iterable<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/{nfe}")
	public Optional<Pedido> getPedido(@PathVariable String nfe) {
		return pedidoRepository.findById(nfe);
	}
	
	@PostMapping
	public Pedido addPedido(@RequestBody Pedido pedido) throws Exception {
		Cliente cliente = clienteRepository.findById(pedido.getIdCliente())
				.orElseThrow(() -> new Exception());
		pedido.setCliente(cliente);
		
		pedidoRepository.save(pedido);
		return pedido;
	}
	
	@PutMapping("/{nfe}")
	public Pedido updatePedido(@RequestBody Pedido pedido,
			@PathVariable String nfe) throws Exception {
		Pedido currentPedido = pedidoRepository.findById(nfe)
				.orElseThrow(() -> new Exception());
		Cliente cliente;
		
		if (pedido.getIdCliente() > 0
				&& pedido.getIdCliente() != currentPedido.getIdCliente()) {
			cliente = clienteRepository.findById(pedido.getIdCliente())
					.orElseThrow(() -> new Exception());
			currentPedido.setCliente(cliente);
		}
		if (pedido.getDataEmissao() != null
				&& pedido.getDataEmissao() != currentPedido.getDataEmissao())
			currentPedido.setDataEmissao(pedido.getDataEmissao());
		if (pedido.getValorTotal() > 0
				&& pedido.getValorTotal() != currentPedido.getValorTotal())
			currentPedido.setValorTotal(pedido.getValorTotal());;
		if (pedido.getDescricao() != null
				&& pedido.getDescricao() != currentPedido.getDescricao())
			currentPedido.setDescricao(pedido.getDescricao());;
		if (pedido.getStatusPedido() != null
				&& pedido.getStatusPedido() != currentPedido.getStatusPedido())
			currentPedido.setStatusPedido(pedido.getStatusPedido());;
		
		pedidoRepository.save(currentPedido);
		return currentPedido;
	}
	
	@DeleteMapping("/{nfe}")
	public void removePedido(@PathVariable String nfe) {
		pedidoRepository.deleteById(nfe);
	}

}
