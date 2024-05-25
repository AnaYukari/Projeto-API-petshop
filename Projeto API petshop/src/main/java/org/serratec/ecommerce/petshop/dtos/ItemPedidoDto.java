package org.serratec.ecommerce.petshop.dtos;

import org.serratec.ecommerce.petshop.entities.Produto;

public class ItemPedidoDto {
	private Integer idItemPedido;
	private int quantidade;
	private Double valorLiquido;
	private Produto produto;

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return String.format("""
				Produto: %s   x%s
				Preço: %2f""", produto.getNome(), quantidade, valorLiquido);
	}
}