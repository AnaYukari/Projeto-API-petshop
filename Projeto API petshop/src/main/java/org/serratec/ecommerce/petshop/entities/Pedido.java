package org.serratec.ecommerce.petshop.entities;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import org.serratec.ecommerce.petshop.enuns.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@PastOrPresent
	@Column(name = "data_pedido")
	public LocalDate dataPedido;

	@PastOrPresent
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@PastOrPresent
	@Column(name = "data_envio")
	private LocalDate dataEnvio;

	@Enumerated (EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "valor_total")
	private Double valorTotal;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedido;

	public Pedido(Integer idPedido, LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio, Status status, Double valorTotal, Cliente cliente, List<ItemPedido> itemPedido) {
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.itemPedido = itemPedido;
	}

	public Pedido() {
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

	public Status validaStatus() {
		if(getDataEntrega()==null && getDataEnvio()==null) {
			status = Status.PREPARANDO;
		}
		if(getDataEnvio()!= null && getDataEntrega()==null) {
			status = Status.ENVIADO;
		}
		if(getDataEntrega()!=null) {
			status = Status.ENTREGUE;
		}
		return status;
	}
}