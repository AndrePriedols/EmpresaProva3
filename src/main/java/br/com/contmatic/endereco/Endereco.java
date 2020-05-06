package br.com.contmatic.endereco;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import br.com.contmatic.utils.Regex;

public class Endereco {

    @Min(value = 1, message = "ID não pode ser menor que 1.")
    private Integer id;

    @NotNull(message = "Tipo Endereço não pode ser nulo.")
    private EnumTipoEndereco tipoEndereco;

    @NotNull(message = "Tipo Logradouro não pode ser vazio.")
    private EnumTipoLogradouro tipoLogradouro;

    @NotBlank(message = "Logradouro não pode ser nulo.")
    @Size(max = 70, min = 1, message = "Logradouro deve ter entre 1 e 70 caracteres.")
    private String logradouro;

    @NotBlank(message = "Número não pode ser nulo.")
    @Size(max = 10, message = "Número só pode ter até 10 caracteres.")
    @javax.validation.constraints.Pattern(regexp = Regex.REGEX_ENDERECO_NUMERO, message = "Número só aceita caracteres numéricos.")
    private String numero;

    @NotBlank(message = "Complemento não pode ser nulo.")
    @Size(max = 30, message = "Complemento só pode ter até 30 caracteres.")
    private String complemento;

    @NotBlank(message = "Bairro não pode ser nulo.")
    @Size(max = 30, message = "Bairro só pode ter até 50 caracteres.")
    private String bairro;

    @NotBlank(message = "Cidade não pode ser nula.")
    private String cidade;

    @NotNull(message = "UF não pode ser nulo.")
    private EnumUF uf;

    @NotBlank(message = "CEP não pode ser nulo.")
    @Size(max = 8, min = 8, message = "CEP deve ter 8 dígitos.")
    @javax.validation.constraints.Pattern(regexp = Regex.REGEX_ENDERECO_CEP, message = "CEP só aceita caracteres numéricos.")
    private String cep;

    public Endereco(String logradouro, String numero, String complemento, String cep) {
        setLogradouro(logradouro);
        setNumero(numero);
        setComplemento(complemento);
        setCep(cep);
    }

    public Endereco() {
    }

    public EnumTipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public EnumUF getUF() {
        return uf;
    }

    public void setUF(EnumUF uF) {
        uf = uF;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public EnumTipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(EnumTipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return this.cep;
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
