package br.com.contmatic.empresa;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

import br.com.contmatic.utils.Regex;

public class Empresa {

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID, message="ID só pode conter números.")
    private String id;

    @NotNull(message = "CNPJ não pode ser nulo.")
    @CNPJ(message="CNPJ em formato inválido.")
    private String cnpj;

    @NotNull(message = "Razão Social não pode ser nula.")
    @Size(max = 70)
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_RAZAO_SOCIAL_VALIDA, message="Razão Social com caracteres inválidos.")
    private String razaoSocial;

    @NotNull(message = "Data Abertura não pode ser nula.")
    private DateTime dataAbertura;

    @Min(value=1, message="Capital Social deve ser maior ou igual a 1.")
    private double capitalSocial;

    @NotNull(message = "Endereço não pode ser nulo.")
    private Endereco endereco;    

    @NotNull(message = "Telefone não pode ser nulo.")
    private Telefone telefone;

    @NotEmpty(message = "Email não pode ser nulo ou vazio.")
    @Length(min=3)
    @Email(message="Email em formato inválido.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_VALIDACAO_EMAIL, message="Email em formato inválido.")
    private String email;

    @URL(message="URL do Site inválida.")
    private String website;

    public Empresa(String cnpj) {
        setCnpj(cnpj);
    }    
    
    public void setEmail (String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public void setDataAbertura(DateTime dataAbertura) {
        Preconditions.checkArgument(!dataAbertura.isAfterNow(), "Data de Abertura não pode ser posterior à data atual.");
        this.dataAbertura = dataAbertura;
    }

    public DateTime getDataAbertura() {
        return this.dataAbertura;
    }

    public void setCapitalSocial(double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public double getCapitalSocial() {
        return this.capitalSocial;
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
