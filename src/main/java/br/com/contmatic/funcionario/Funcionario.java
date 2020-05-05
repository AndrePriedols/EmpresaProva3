package br.com.contmatic.funcionario;

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
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.setor.Setor;
import br.com.contmatic.utils.Regex;
import br.com.contmatic.utils.ValidadorCpfNumerosIguais;
import telefone.Telefone;

public class Funcionario {

	private static final int SALARIO_ZERO = 0;

	@Min(value = 1, message = "ID não pode ser menor que 1.")
	private Integer id;

	@NotBlank(message = "CPF não pode ser nulo ou vazio.")
	@CPF(message = "CPF em formato inválido.")
	private String cpf;

	@NotBlank(message = "Nome não pode ser nulo.")
	@Size(max = 70, min = 2, message = "Nome deve ter entre 70 e 2 caracteres.")
	@javax.validation.constraints.Pattern(regexp = Regex.REGEX_NOME_VALIDO, message = "Nome com caracteres inválidos.")
	private String nome;

	@NotNull(message = "Data Nascimento não pode ser nula.")
	private DateTime dataNascimento;

	@NotNull(message = "Estado Civil não pode ser nulo.")
	private EnumTipoEstadoCivil estadoCivil;

	@NotNull(message = "Endereço não pode ser nulo.")
	private Endereco endereco;

	@NotNull(message = "Telefone não pode ser nulo.")
	private Telefone telefone;

	@NotBlank(message = "Email não pode ser nulo ou vazio.")
	@Length(min = 3)
	@Email(message = "Email em formato inválido.")
	@javax.validation.constraints.Pattern(regexp = Regex.REGEX_VALIDACAO_EMAIL, message = "Email em formato inválido.")
	private String email;

	@NotNull(message = "Data Contratação não pode ser nula.")
	private DateTime dataContratacao;

	@NotNull(message = "Setor não pode ser nulo.")
	private Setor setor;

	@NotNull(message = "Cargo não pode ser nulo.")
	private String cargo;

	@Min(value = 1, message = "Salário deve ser igual ou maior a 1.")
	private double salario;

	public Funcionario() {
	}

	public Funcionario(String cpf) {
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

	public void setDataNascimento(DateTime dataNascimento) {
		impedeDataNascimentoPosteriorAtual(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	private void impedeDataNascimentoPosteriorAtual(DateTime dataNascimento) {
		Preconditions.checkArgument(dataNascimento.isBeforeNow(), "Data Nascimento não pode ser posteior à atual.");
	}

	public DateTime getDataNascimento() {
		return this.dataNascimento;
	}

	public void setEstadoCivil(EnumTipoEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public EnumTipoEstadoCivil getEstadoCivil() {
		return this.estadoCivil;
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

	public void setDataContratacao(DateTime dataContratacao) {
		impedeDataContratacaoNula(dataContratacao);
		impedeDataContratacaoPosteriorAtual(dataContratacao);
		this.dataContratacao = dataContratacao;
	}

	private void impedeDataContratacaoPosteriorAtual(DateTime dataContratacao) {
		Preconditions.checkArgument(dataContratacao.isBeforeNow(), "Data Contratação não pode ser posteior à atual.");
	}

	private void impedeDataContratacaoNula(DateTime dataContratacao) {
		Preconditions.checkNotNull(dataContratacao, "Data de Contratação não pode ser nula");
	}

	public DateTime getDataContratacao() {
		return this.dataContratacao;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Setor getSetor() {
		return this.setor;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setSalario(double salario) {
		obrigaSalarioPositivo(salario);
		this.salario = salario;
	}

	private void obrigaSalarioPositivo(double salario) {
		if (salario <= SALARIO_ZERO) {
			throw new IllegalArgumentException("Salário deve ser maior que " + SALARIO_ZERO + ".");
		}
	}

	public double getSalario() {
		return this.salario;
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
