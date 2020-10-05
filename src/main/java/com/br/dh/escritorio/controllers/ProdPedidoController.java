package com.br.dh.escritorio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.dh.escritorio.model.entities.ProdPedido;
import com.br.dh.escritorio.model.entities.ProdPedidoPK;
import com.br.dh.escritorio.model.repositories.ProdPedidoRepository;

@RestController
@RequestMapping("/prod-pedido")
public class ProdPedidoController {
	@Autowired
	private ProdPedidoRepository prodPedidoRepository;

	@GetMapping
	public Iterable<ProdPedido> getProdPedidos() {
		return prodPedidoRepository.findAll();
	}

	@GetMapping("/id")
	public Optional<ProdPedido> getProdPedido(@RequestParam int idProduto,
			@RequestParam String nfe) {
		return prodPedidoRepository
				.findOneById(new ProdPedidoPK(idProduto, nfe));
	}
	
	@GetMapping("/nfe")
	public List<ProdPedido> getNfeProdPedido(@RequestParam String nfe) {
		return prodPedidoRepository
				.findByIdNfe(nfe);
	}
	
	@GetMapping("/prod")
	public List<ProdPedido> getNfeProdPedido(@RequestParam int idProduto) {
		return prodPedidoRepository
				.findByIdIdProduto(idProduto);
	}

	@PostMapping
	public ProdPedido addProdPedido(@RequestBody ProdPedido prodPedido) {
		prodPedidoRepository.save(prodPedido);
		return prodPedido;
	}
	
	@DeleteMapping
	public void removeProdPedido(@RequestParam int idProduto,
			@RequestParam String nfe) {
		prodPedidoRepository.deleteById(new ProdPedidoPK(idProduto, nfe));
	}
	
	@PutMapping
	public ProdPedido updateProdPedido(@RequestParam int idProduto,
			@RequestParam String nfe, @RequestBody ProdPedido prodPedido)
					throws Exception {
		ProdPedido currentProdPedido = prodPedidoRepository
				.findById(new ProdPedidoPK(idProduto, nfe))
				.orElseThrow(() -> new Exception());
		
		if (currentProdPedido == null) return null;
		
		if(prodPedido.getId().getNfe() != null
				&& prodPedido.getId().getNfe() != currentProdPedido.getId().getNfe())
			currentProdPedido.getId().setNfe(prodPedido.getId().getNfe());
		if(prodPedido.getId().getIdProduto() > 0
				&& prodPedido.getId().getNfe() != currentProdPedido.getId().getNfe())
			currentProdPedido.getId().setIdProduto(prodPedido.getId().getIdProduto());
		if(prodPedido.getQuantidade() > 0
				&& prodPedido.getQuantidade() != currentProdPedido.getQuantidade())
			currentProdPedido.setQuantidade(prodPedido.getQuantidade());
		
		prodPedidoRepository.save(currentProdPedido);
		return prodPedido;
	}
}
