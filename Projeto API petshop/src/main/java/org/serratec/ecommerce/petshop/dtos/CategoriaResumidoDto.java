package org.serratec.ecommerce.petshop.dtos;

public class CategoriaResumidoDto {
	
	private String nome;
	private String descricao;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "CategoriaResumidoDto [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	
	
}