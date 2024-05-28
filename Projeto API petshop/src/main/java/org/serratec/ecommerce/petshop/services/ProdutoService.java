package org.serratec.ecommerce.petshop.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.petshop.dtos.ProdutoDto;
import org.serratec.ecommerce.petshop.entities.Produto;
import org.serratec.ecommerce.petshop.exceptions.EntidadeNotFoundException;
import org.serratec.ecommerce.petshop.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<ProdutoDto> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDto> produtosDto = new ArrayList<>();
		for (Produto produto : produtos){
			ProdutoDto produtodto = modelMapper.map(produto, ProdutoDto.class);
			produtosDto.add(produtodto);
		}
		return produtosDto;
	}

	public ProdutoDto findById(Integer id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um Produto com o id " + id));
		ProdutoDto produtodto = modelMapper.map(produto, ProdutoDto.class);
		return produtodto;
	}

	public Produto findByIdcompleto(Integer id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um Produto com o id " + id));
		return produto;
	}

	public Produto save(MultipartFile file, Produto produto) throws IOException {
		produto.setImagem(file.getBytes());
		return produtoRepository.save(produto);
	}


	public Produto update(MultipartFile file, Produto produto) {
		Integer id = produto.getIdProduto();
		produto = produtoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um Produto com o id " + id));
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Produto produto){
		return produtoRepository.save(produto);
	}

	public Produto delete(Integer id) {
		if (produtoRepository.existsById(id) == true) {
			Produto excluir = produtoRepository.findById(id).orElse(null);
			try {
				produtoRepository.deleteById(id);
				return excluir;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}
}
