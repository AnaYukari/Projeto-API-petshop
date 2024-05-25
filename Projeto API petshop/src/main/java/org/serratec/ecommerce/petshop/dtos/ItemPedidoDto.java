package org.serratec.ecommerce.petshop.dtos;

public class ItemPedidoDto {
	private int quantidade;
	private Double valorLiquido;
	private ProdutoDto produto;

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
	public ProdutoDto getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}
}