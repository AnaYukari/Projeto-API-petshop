package org.serratec.ecommerce.petshop.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.petshop.controllers.PedidoController;
import org.serratec.ecommerce.petshop.controllers.ProdutoController;
import org.serratec.ecommerce.petshop.dtos.ItemPedidoDto;
import org.serratec.ecommerce.petshop.dtos.PedidoResumidoDto;
import org.serratec.ecommerce.petshop.entities.ItemPedido;
import org.serratec.ecommerce.petshop.entities.Pedido;
import org.serratec.ecommerce.petshop.entities.Produto;
import org.serratec.ecommerce.petshop.exceptions.EntidadeNotFoundException;
import org.serratec.ecommerce.petshop.repositories.ItemPedidoRepository;
import org.serratec.ecommerce.petshop.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Table;

@Service
@Table (name = "item_pedido")
public class ItemPedidoService {
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	EmailService emailService;
	@Autowired
	PedidoController pedidoController;
	@Autowired
	ProdutoController produtoController;
	public List<ItemPedidoDto> findAll(){
		List<ItemPedido> itensPedidos = itemPedidoRepository.findAll();
		List<ItemPedidoDto> itensPedidosDto = new ArrayList<>();

		for (ItemPedido itemPedido : itensPedidos){
			ItemPedidoDto itemPedidoDto = modelMapper.map(itemPedido, ItemPedidoDto.class);
			itensPedidosDto.add(itemPedidoDto);
		}
		return itensPedidosDto;
	}
	
	public ItemPedidoDto findById(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um ItemPedido com o id " + id)
		);
		ItemPedidoDto itemPedidoDto = modelMapper.map(itemPedido, ItemPedidoDto.class);
		return itemPedidoDto;
	}
	public ItemPedido findByIdCompleto(Integer id) {
		return itemPedidoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um ItemPedido com o id " + id));
	}
	
	public ItemPedido save(ItemPedido itemPedido) {
		Produto produto = produtoService.findByIdcompleto(itemPedido.getProduto().getIdProduto());
		Double precoVenda = produto.getValorUnitario();
		itemPedido.setPrecoVenda(precoVenda);
		produto.setQtdEstoque(produto.getQtdEstoque()-itemPedido.getQuantidade());
		Double valorBruto = precoVenda * itemPedido.getQuantidade();
		itemPedido.setValorBruto(valorBruto);
		Double valorLiquido = valorBruto - (valorBruto * itemPedido.getPercentualDesconto()/100);
		itemPedido.setValorLiquido(valorLiquido);
		Pedido pedido = pedidoService.findByIdCompleto(itemPedido.getPedido().getIdPedido());
		pedido.setValorTotal(pedido.getValorTotal() + valorLiquido);
		itemPedidoRepository.save(itemPedido);

		produtoController.updateProduto(produto);
		pedidoController.atualizaItem(pedido);
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
