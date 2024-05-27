package org.serratec.ecommerce.petshop.dtos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.serratec.ecommerce.petshop.entities.Cliente;
import org.serratec.ecommerce.petshop.enuns.Status;

public class PedidoResumidoDto {
	
	private Integer idPedido;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Cliente cliente;
	private List<ItemPedidoDto> itemPedido;
	private Double valorTotal;

	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

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
	public String getCliente() {
		return cliente.getNomeCompleto();
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemPedidoDto> getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(List<ItemPedidoDto> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public String listaItens(){
		String itens = " ";
		for (ItemPedidoDto item : itemPedido){
			itens += "\n\n" + "===========\n" + item.toString();
		}
		return itens;
	}
	
	public String formataData(LocalDate dataPedido) {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = dataFormatada.format(dataPedido);
		return data;
	}

	@Override
	public String toString() {
		return String.format("""
				Código do Pedido: %s
				Data de Pedido: %s
				Cliente: %s
				Itens do Pedido: %s
				
				Valor total: R$%.2f""", idPedido, formataData(dataPedido), cliente.getNomeCompleto(), listaItens(), valorTotal);
	}
}