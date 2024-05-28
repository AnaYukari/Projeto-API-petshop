package org.serratec.ecommerce.petshop.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.petshop.dtos.PedidoResumidoDto;
import org.serratec.ecommerce.petshop.dtos.ProdutoDto;
import org.serratec.ecommerce.petshop.entities.Categoria;
import org.serratec.ecommerce.petshop.entities.Pedido;
import org.serratec.ecommerce.petshop.entities.Produto;
import org.serratec.ecommerce.petshop.exceptions.EntidadeNotFoundException;
import org.serratec.ecommerce.petshop.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado uma Categoria com o id " + id)
		);
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria delete(Integer id) {
		if (categoriaRepository.existsById(id) == true) {
			Categoria excluir = categoriaRepository.findById(id).orElse(null);
			try {
				categoriaRepository.deleteById(id);
				return excluir;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	public List<ProdutoDto> findProdutoByCategoriaId(Integer id){
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado uma Categoria com o id " + id)
		);
		List<Produto> produtos = categoria.getProduto();
		List<ProdutoDto> produtosDto = new ArrayList<>();
		for (Produto produto : produtos){
			ProdutoDto produtodto = modelMapper.map(produto, ProdutoDto.class);
			produtosDto.add(produtodto);
		}
		return produtosDto;
	}

}
