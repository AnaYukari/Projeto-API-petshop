package org.serratec.ecommerce.petshop.dtos;

import org.serratec.ecommerce.petshop.entities.Produto;

public class ItemPedidoDto {
	private Integer idItemPedido;
	private Integer quantidade;
	private Double valorBruto;
	private Integer percentualDesconto;
	private Double valorLiquido;
	private Produto produto;

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Integer getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Integer percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
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
				Id Produto: %s
				Produto: %s  R$%.2f x%s
				Valor Bruto: R$%.2f
				Percentual de Desconto: %s%%
				Valor Liquido: R$%.2f""",produto.getIdProduto(), produto.getNome(), 
				produto.getValorUnitario(), quantidade, valorBruto, percentualDesconto, valorLiquido);
	}
}