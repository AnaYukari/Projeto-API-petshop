package org.serratec.ecommerce.petshop.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Column(unique =  true , name = "email")
	@NotBlank(message = "O campo email não pode ser vazio.")
	@Pattern(regexp = "^[\\w!#$%&amp;'*+/=?{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$" , message = "Digite um email válido")
	private String email;
	
	
	@Column(name = "nome_completo")
	@NotBlank(message = "O campo nome completo não pode ser vazio.")
	private String nomeCompleto;

	@Column(unique = true, name = "cpf")
	@NotBlank(message = "O campo CPF não pode ser vazio.")
	@Size(min = 11, max = 11)
	private String cpf;

	@Column(name = "telefone")
	@NotBlank(message = "O campo telefone não pode ser vazio.")
	private String telefone;

	@Column(name = "data_nascimento")
	@NotNull(message = "O campo data de nascimento não pode ser vazio.")
	private Date dataNascimento;

	@OneToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "endereco_Id")
	@JsonManagedReference
	private Endereco endereco;
	
	public Cliente() {
	}

	public Cliente(Integer idCliente, String email, String nomeCompleto, String cpf, String telefone,
			Date dataNascimento) {
		this.idCliente = idCliente;
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
}