package org.serratec.ecommerce.petshop.services;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.serratec.ecommerce.petshop.entities.Produto;
import org.serratec.ecommerce.petshop.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public Produto save(MultipartFile file, Produto produto) throws IOException {
		produto.setImagem(file.getBytes());
		return produtoRepository.save(produto);
	}


	public Produto update(Produto produto) {
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
