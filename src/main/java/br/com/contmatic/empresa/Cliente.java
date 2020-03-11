package br.com.contmatic.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.google.common.base.Preconditions;

import br.com.contmatic.utils.Regex;

public class Cliente {

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID)
    private String id;

    @NotNull(message = "CPF não pode ser nulo ou vazio.")
    @CPF(message="CPF em formato inválido.")
    private String cpf;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio.")
    @Size(max = 70, min = 2, message="Nome deve ter entre 70 e 2 caracteres.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_NOME_VALIDO, message="Nome com caracteres inválidos.")
    private String nome;

    @NotNull(message = "Endereço não pode ser nulo.")
    private Endereco endereco;

    @NotNull(message = "Telefone não pode ser nulo.")
    private Telefone telefone;

    @NotEmpty(message = "Email não pode ser nulo ou vazio.")
    @Min(3)
    @Email
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_VALIDACAO_EMAIL, message="Email em formato inválido.")
    private String email;

    public Cliente(String cpf) {
        setCpf(cpf);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setNome(String nome) {
        impedeNomeNulo(nome);
        impedeNomeInvalido(nome);
        this.nome = nome;
    }

    private void impedeNomeInvalido(String nome) {
        Pattern pattern = Pattern.compile(Regex.REGEX_NOME_VALIDO);
        Matcher matcher = pattern.matcher(nome);
        Preconditions.checkArgument(matcher.find(), "Nome em formato inválido");
    }

    private void impedeNomeNulo(String nome) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(email), "Nome não pode ser nulo");
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

    public void setEmailCliente(String emailCliente) {
        this.email = emailCliente;
    }

    public String getEmailCliente() {
        return this.email;
    }

    public void setEmail(String email) {
        impedeEmailNulo(email);
        impedeEmailInvalido(email);
        this.email = email;
    }

    private void impedeEmailInvalido(String email) {
        Pattern pattern = Pattern.compile(Regex.REGEX_VALIDACAO_EMAIL);
        Matcher matcher = pattern.matcher(email);
        Preconditions.checkArgument(matcher.find(), "Email em formato inválido");
    }

    private void impedeEmailNulo(String email) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(email), "Email deve ser informado.");
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
