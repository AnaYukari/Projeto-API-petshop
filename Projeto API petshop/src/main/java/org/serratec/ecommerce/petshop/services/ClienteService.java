package org.serratec.ecommerce.petshop.services;

import java.util.List;

import org.serratec.ecommerce.petshop.entities.Cliente;
import org.serratec.ecommerce.petshop.exceptions.EntidadeNotFoundException;
import org.serratec.ecommerce.petshop.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um Cliente com o id " + id)
		);
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente delete(Integer id) {
		if (clienteRepository.existsById(id) == true) {
			Cliente excluir = clienteRepository.findById(id).orElse(null);
			try {
				clienteRepository.deleteById(id);
				return excluir;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}
}

