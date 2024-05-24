package org.serratec.ecommerce.petshop.services;

import java.util.List;

import org.serratec.ecommerce.petshop.entities.Categoria;
import org.serratec.ecommerce.petshop.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
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
	/*
	public List<Produto> findEmprestimoByAlunoId(Integer id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.get().getProduto();
	}
	*/
}
