package br.com.contmatic.empresa;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.utils.Regex;
import br.com.contmatic.utils.ValidadorCpfNumerosIguais;

public class Cliente {

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID, message="ID só pode conter números.")
    private String id;

    @NotEmpty(message = "CPF não pode ser nulo ou vazio.")
    @CPF(message="CPF em formato inválido.")
    private String cpf;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio.")
    @Size(max = 70, min = 2, message="Nome deve ter entre 70 e 2 caracteres.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_NOME_VALIDO, message="Nome com caracteres inválidos.")
    private String nome;
    
    Set<Endereco> enderecos = new HashSet<Endereco>();

    @NotNull(message = "Endereço não pode ser nulo.")
    private Endereco endereco;

    Set<Telefone> telefones = new HashSet<Telefone>();
    
    @NotNull(message = "Telefone não pode ser nulo.")
    private Telefone telefone;

    @NotEmpty(message = "Email não pode ser nulo ou vazio.")
    @Length(min=3)
    @Email(message="Email em formato inválido.")
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
    
    public void addEndereco(Endereco endereco) {
    	boolean enderecoRepetido = enderecos.contains(endereco);
    	assertFalse("Não aceita endereço repetido", enderecoRepetido);
    	enderecos.add(endereco);
    }
    
    public void addTelefone(Telefone telefone) {
    	boolean telefoneRepetido = telefones.contains(telefone);
    	assertFalse("Não aceita telefone repetido", telefoneRepetido);
    	telefones.add(telefone);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
