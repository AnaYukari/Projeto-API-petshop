package org.serratec.ecommerce.petshop.controllers;

import java.io.IOException;
import java.util.List;

import org.serratec.ecommerce.petshop.dtos.ProdutoDto;
import org.serratec.ecommerce.petshop.entities.Produto;
import org.serratec.ecommerce.petshop.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<ProdutoDto>> findAll() {
		return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> findById(@PathVariable Integer id) {
		ProdutoDto produto = produtoService.findById(id);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Produto> save(@Valid @RequestPart("imagem") MultipartFile file, @RequestPart("produto") Produto produto) throws IOException {
		return new ResponseEntity<>(produtoService.save(file, produto), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Produto> update(@Valid @RequestParam("file") MultipartFile file, @RequestPart("produto") Produto produto) {
		return new ResponseEntity<>(produtoService.update(file, produto), HttpStatus.OK);
	}
	@PutMapping("atualizaProduto")
	public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.updateProduto(produto), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> delete(@PathVariable Integer id) {
		Produto produto = produtoService.findByIdcompleto(id);
		if (produto == null) {
			return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
		} else {
			produtoService.delete(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		}
	}
}
