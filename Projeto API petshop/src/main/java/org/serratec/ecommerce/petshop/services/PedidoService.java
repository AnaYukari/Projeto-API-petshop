package org.serratec.ecommerce.petshop.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.petshop.controllers.EmailController;
import org.serratec.ecommerce.petshop.controllers.PedidoController;
import org.serratec.ecommerce.petshop.dtos.PedidoResumidoDto;
import org.serratec.ecommerce.petshop.entities.Pedido;
import org.serratec.ecommerce.petshop.exceptions.EntidadeNotFoundException;
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
	EmailService emailService;

	@Autowired
	EmailController emailController;
	
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
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um Pedido com o id " + id));
		PedidoResumidoDto pedidodto = modelMapper.map(pedido, PedidoResumidoDto.class);
		return pedidodto;
	}

	public Pedido findByIdCompleto(Integer id){
		return pedidoRepository.findById(id).orElseThrow(
				() -> new EntidadeNotFoundException
						("Não foi encontrado um Pedido com o id " + id));
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
	
	public String enviarEmail(Integer id) {
		Pedido pedido = findByIdCompleto(id);
		PedidoResumidoDto pedidoDto = findById(id);

		if (pedido.getDataEnvio()==null && pedido.getDataEntrega()==null) {
			emailService.enviarEmail(pedido.getCliente().getEmail(),
					"Pedido Feito com Sucesso", pedidoDto.toString());
		}
		if (pedido.getDataEnvio()!=null && pedido.getDataEntrega()==null){
			emailService.enviarEmail(pedido.getCliente().getEmail(), "Pedido Enviado",
					"Seu Pedido foi enviado com sucesso!\n" + pedidoDto.toString());
		}
		if (pedido.getDataEntrega()!=null){
			String email = "Seu Pedido foi entregue em " + pedido.getDataEntrega() + " com sucesso!\n"
					+ pedidoDto.toString() + "\n\n\t\tOBRIGADO POR COMPRAR NA OSWALDINATO PETSHOP ";
			emailService.enviarEmail(pedido.getCliente().getEmail(), "Pedido Entregue", email);
		}
		return "Email enviado com sucesso";
	}
	
	public PedidoResumidoDto update(Pedido pedido) {
		PedidoResumidoDto pedidoDto = modelMapper.map(pedido,PedidoResumidoDto.class);
		pedidoDto.setValorTotal(pedido.getValorTotal());
		pedido.setStatus(pedido.validaStatus()); 
		pedidoRepository.save(pedido);
		emailController.enviarEmail(pedido.getIdPedido());
		return pedidoDto;
	}
	public String atualizaItem(Pedido pedido){
		pedido.setStatus(pedido.validaStatus());
		pedidoRepository.save(pedido);
		return "Pedido Atualizado";
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
