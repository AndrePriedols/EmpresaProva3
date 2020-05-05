package br.com.contmatic.cliente;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.utils.Regex;
import br.com.contmatic.utils.ValidadorCpfNumerosIguais;
import telefone.Telefone;

public class Cliente {

	@Min(value = 1, message = "ID não pode ser menor que 1.")
	private Integer id;

	@NotBlank(message = "CPF não pode ser nulo ou vazio.")
	@CPF(message = "CPF em formato inválido.")
	private String cpf;

	@NotBlank(message = "Nome não pode ser nulo ou vazio.")
	@Size(max = 70, min = 2, message = "Nome deve ter entre 70 e 2 caracteres.")
	@javax.validation.constraints.Pattern(regexp = Regex.REGEX_NOME_VALIDO, message = "Nome com caracteres inválidos.")
	private String nome;

	private Set<Endereco> enderecos = new HashSet<>();

	@NotNull(message = "Endereço não pode ser nulo.")
	private Endereco endereco;

	private Set<Telefone> telefones = new HashSet<>();

	@NotNull(message = "Telefone não pode ser nulo.")
	private Telefone telefone;

	@NotBlank(message = "Email não pode ser nulo ou vazio.")
	@Length(min = 3)
	@Email(message = "Email em formato inválido.")
	@javax.validation.constraints.Pattern(regexp = Regex.REGEX_VALIDACAO_EMAIL, message = "Email em formato inválido.")
	private String email;

	public Cliente(String cpf) {
		setCpf(cpf);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setCpf(String cpf) {
		ValidadorCpfNumerosIguais.impedeCpfTodosDigitosIguais(cpf);
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
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
