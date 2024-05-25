package org.serratec.ecommerce.petshop.dtos;

import org.serratec.ecommerce.petshop.entities.Categoria;

import java.sql.Date;
import java.util.List;

public class ProdutoDto {
	private String nome;
	private Double valorUnitario;
    private Categoria categoria;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getCategoria() {
		return categoria.getNome();
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}