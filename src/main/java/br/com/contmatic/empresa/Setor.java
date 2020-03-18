package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.contmatic.enums.EnumNomeSetor;
import br.com.contmatic.utils.Regex;

public class Setor {

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID, message="ID só pode conter números.")
    private String id;

    @NotNull(message = "Nome Setor não pode ser nulo.")
    private EnumNomeSetor nome;

    @NotNull(message = "Ramal não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_RAMAL, message="Ramal só pode conter números.")
    private String ramal;

    @NotEmpty(message = "Responsável não pode ser nulo ou vazio.")
    @Size(max = 70, min = 2, message="Responsável deve ter entre 70 e 2 caracteres.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_NOME_VALIDO, message="Responsável com caracteres inválidos.")
    private String responsavel;

    public Setor(EnumNomeSetor nome) {
        setNome(nome);
    }

    public Setor() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNome(EnumNomeSetor nome) {
        this.nome = nome;
    }

    public EnumNomeSetor getNome() {
        return this.nome;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getRamal() {
        return this.ramal;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getResponsavel() {
        return this.responsavel;
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
