package org.serratec.ecommerce.petshop.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.petshop.dtos.PedidoResumidoDto;
import org.serratec.ecommerce.petshop.entities.ItemPedido;
import org.serratec.ecommerce.petshop.entities.Pedido;
import org.serratec.ecommerce.petshop.repositories.ItemPedidoRepository;
import org.serratec.ecommerce.petshop.repositories.PedidoRepository;
import org.serratec.ecommerce.petshop.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Table;

@Service
@Table (name = "pedido")
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
    ProdutoRepository produtoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public List<PedidoResumidoDto> findAll(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResumidoDto> pedidosdto = new ArrayList<>();

		for (Pedido pedido : pedidos){
			PedidoResumidoDto pedidodto = modelMapper.map(pedido, PedidoResumidoDto.class);
			pedidosdto.add(pedidodto);
		}
		return pedidosdto;
	}
	
	public PedidoResumidoDto findById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		PedidoResumidoDto pedidodto = modelMapper.map(pedido, PedidoResumidoDto.class);
		return pedidodto;
	}

	public Pedido findByIdCompleto(Integer id){
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido save(Pedido pedido) {
		if (pedido.getDataEnvio() == null && pedido.getDataEntrega() != null) {
			throw new IllegalArgumentException("A data de envio não pode ser nula quando a data de entrega está definida.");
		}else if (pedido.getDataEnvio() != null && pedido.getDataEnvio().isBefore(pedido.getDataPedido())) {
			throw new IllegalArgumentException("A data de envio não pode ser antes da data do pedido.");
		}else if (pedido.getDataEntrega() != null && pedido.getDataEntrega().isBefore(pedido.getDataPedido())) {
			throw new IllegalArgumentException("A data de entrega não pode ser antes da data do pedido.");
		}else if (pedido.getDataEnvio() != null && pedido.getDataEntrega() != null && pedido.getDataEnvio().isAfter(pedido.getDataEntrega())) {
			throw new IllegalArgumentException("A data de envio não pode ser depois da data de entrega.");
		}

		pedido.setValorTotal(0.0);
		pedido.setStatus(pedido.validaStatus());
	    return pedidoRepository.save(pedido);
	}
	
	public Pedido update(Pedido pedido) {
		pedido.setStatus(pedido.validaStatus());
		return pedidoRepository.save(pedido);
	}
	
	public Pedido delete(Integer id) {
		if (pedidoRepository.existsById(id) == true) {
			Pedido pedidoDeletado = pedidoRepository.findById(id).orElse(null);
			try {
				pedidoRepository.deleteById(id);
				return pedidoDeletado;
			} catch (Exception e) {
			 System.out.println(e);
			 return null;
			}
		}
		return null;
	}
}
