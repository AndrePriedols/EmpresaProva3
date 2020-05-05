package br.com.contmatic.fornecedor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.utils.Regex;
import telefone.Telefone;

public class Fornecedor {

	@Min(value = 1, message = "ID não pode ser menor que 1.")
	private Integer id;

	@NotBlank(message = "CNPJ não pode ser nulo.")
	@CNPJ(message = "CNPJ em formato inválido.")
	private String cnpj;

	@NotBlank(message = "Razão Social não pode ser nula.")
	@Size(max = 70)
	@javax.validation.constraints.Pattern(regexp = Regex.REGEX_RAZAO_SOCIAL_VALIDA, message = "Razão Social com caracteres inválidos.")
	private String razaoSocial;

	@NotBlank(message = "Email não pode ser nulo ou vazio.")
	@Length(min = 3)
	@Email(message = "Email em formato inválido.")
	@javax.validation.constraints.Pattern(regexp = Regex.REGEX_VALIDACAO_EMAIL, message = "Email em formato inválido.")
	private String email;

	@NotNull(message = "Endereço não pode ser nulo.")
	private Endereco endereco;

	@NotNull(message = "Telefone não pode ser nulo.")
	private Telefone telefone;

	@URL(message = "URL do Site inválida.")
	private String website;

	public Fornecedor(String cnpj) {
		setCnpj(cnpj);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String site) {
		this.website = site;
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
