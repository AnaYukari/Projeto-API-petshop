package org.serratec.ecommerce.petshop.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer idProduto;

	@NotBlank (message = "Produto precisa de um nome")
	@Size(min = 3, max = 50)
	@Column(name = "nome", unique = true)
	private String nome;

	@NotBlank (message = "Produto precisa de uma descricao")
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull (message = "O estoque deve ser um numero valido")
	@Column(name = "qtd_estoque")
	private int qtdEstoque;
	
	
	@FutureOrPresent
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@NotNull (message = "Produto precisa de um valor")
	@Column(name = "valor_unitario")
	private double valorUnitario;

	@Column(columnDefinition = "bytea", name = "imagem")
	private byte[] imagem;
	@ManyToOne
    @JoinColumn (name = "id_categoria")
    private Categoria categoria;
	@OneToMany(mappedBy = "produto")
	@JsonIgnore
	private List<ItemPedido> itemPedido;
	
	public Produto() {
	}
	
	public Produto(Integer idProduto, String nome, String descricao, int qtdEstoque, Date dataCadastro,
			double valorUnitario, byte[] imagem) {
		this.idProduto = idProduto;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.dataCadastro = dataCadastro;
		this.valorUnitario = valorUnitario;
		this.imagem = imagem;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

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

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}

}