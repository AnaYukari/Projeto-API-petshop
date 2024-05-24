package org.serratec.ecommerce.petshop.controllers;

import java.util.List;

import org.serratec.ecommerce.petshop.entities.Produto;
import org.serratec.ecommerce.petshop.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		if (produto == null) {
			return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.save(produto), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.update(produto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> delete(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		if (produto == null) {
			return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
		} else {
			produtoService.delete(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		}
	}
}