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

import com.br.dh.escritorio.model.entities.Produto;
import com.br.dh.escritorio.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public Iterable<Produto> getProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> getProduto(@PathVariable int id) {
		return produtoRepository.findById(id);
	}
	
	@PostMapping
	public Produto addProduto(@RequestBody Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	@PutMapping("/{id}")
	public Produto updateProduto(@RequestBody Produto produto,
			@PathVariable int id) throws Exception {
		Produto currentProduto = produtoRepository.findById(id)
				.orElseThrow(() -> new Exception());

		if (produto.getNome() != null
				&& produto.getNome() != currentProduto.getNome())
			currentProduto.setNome(produto.getNome());
		if (produto.getEstoque() > 0
				&& produto.getEstoque() != currentProduto.getEstoque())
			currentProduto.setEstoque(produto.getEstoque());;
		if (produto.getDescricao() != null
				&& produto.getDescricao() != currentProduto.getDescricao())
			currentProduto.setDescricao(produto.getDescricao());;
		if (produto.getPreco() > 0
				&& produto.getPreco() != currentProduto.getPreco())
			currentProduto.setPreco(produto.getPreco());;
		
		produtoRepository.save(currentProduto);
		return currentProduto;
	}
	
	@DeleteMapping("/{id}")
	public void removeProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}
}
