package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public class Cliente {

	@NotNull(message = "ID não pode ser nulo.")
	private int id;

	@CPF
	@NotEmpty(message = "CPF não pode ser nulo ou vazio.")
	private String cpf;

	@Size(max = 70)
	@NotEmpty(message = "Nome não pode ser nulo ou vazio.")
	private String nome;

	@NotNull(message = "Endereço não pode ser nulo.")
	private Endereco endereco;

	@NotEmpty(message = "Email não pode ser nulo ou vazio.")
	private String emailCliente;

	public Cliente(String cpf) {
		setCpf(cpf);
	}

	Utilitarios util = new Utilitarios();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getEmailCliente() {
		return this.emailCliente;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	 public boolean equals(Object obj) {
	    return EqualsBuilder.reflectionEquals(this, obj);
	  }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
