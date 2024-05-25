package org.serratec.ecommerce.petshop.services;

import java.util.List;

import org.serratec.ecommerce.petshop.entities.ItemPedido;
import org.serratec.ecommerce.petshop.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Table;

@Service
@Table (name = "item_pedido")
public class ItemPedidoService {
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAll(){
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido save(ItemPedido itemPedido) {
		itemPedido.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedido.setValorBruto(itemPedido.getValorBruto());
		itemPedido.setValorLiquido(itemPedido.getValorLiquido());
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido update(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido delete(Integer id) {
		if (itemPedidoRepository.existsById(id) == true) {
			ItemPedido itemPedidoDeletado = itemPedidoRepository.findById(id).orElse(null);
			try {
				itemPedidoRepository.deleteById(id);
				return itemPedidoDeletado;
			} catch (Exception e) {
			 System.out.println(e);
			 return null;
			}
		}
		return null;
	}
}
