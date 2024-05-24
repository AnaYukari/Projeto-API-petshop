package org.serratec.ecommerce.petshop.dtos;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.petshop.entities.Cliente;
import org.serratec.ecommerce.petshop.entities.ItemPedido;

public class PedidoResumidoDto {
	
	private Integer idPedido;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Cliente cliente;
	private List<ItemPedido> itemPedido;
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public LocalDate getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}

}