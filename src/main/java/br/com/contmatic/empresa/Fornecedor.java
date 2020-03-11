package br.com.contmatic.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.contmatic.utils.Regex;

public class Fornecedor {

    public static final int EXTENSAO_OBRIGATORIA_CNPJ = 14;

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID)
    private String id;

    @NotNull(message = "CNPJ não pode ser nulo.")
    @CNPJ
    private String cnpj;

    @NotNull(message = "Razão Social não pode ser nula.")
    @Size(max = 70)
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_RAZAO_SOCIAL_VALIDA, message="Razão Social com caracteres inválidos.")
    private String razaoSocial;

    @NotNull(message = "Email não pode ser nulo.")
    @Email
    @javax.validation.constraints.Pattern(regexp="^[a-zA-Z0-9_-][a-zA-Z0-9._-]+@[a-zA-Z0_9][a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
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

   public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCnpj(String cnpj) {
        impedeCnpjNulo(cnpj);
        verificaTamanhoCnpj14Digitos(cnpj);
        impedeCaracteresNaoNumericosCnpj(cnpj);
        this.cnpj = cnpj;
    }

    private void impedeCaracteresNaoNumericosCnpj(String cnpj) {
        char[] cnpjComoArrayChars = cnpj.toCharArray();
        for(char caracterCnpj : cnpjComoArrayChars) {
            if (!Character.isDigit(caracterCnpj)) {
                throw new IllegalArgumentException("CEP apenas com Números.");
            }
        }
    }

    private void verificaTamanhoCnpj14Digitos(String cnpj) {
        if (cnpj.length() != EXTENSAO_OBRIGATORIA_CNPJ) {
            throw new IllegalArgumentException("Cnpj deve ter " + EXTENSAO_OBRIGATORIA_CNPJ + " dígitos.");
        }
    }

    private void impedeCnpjNulo(String cnpj) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(cnpj), "CNPJ não pode ser nulo");
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setRazaoSocial(String razaoSocial) {
        impedeRazaoSocialNula(razaoSocial);
        impedeRazaoSocialInvalida(razaoSocial);
        this.razaoSocial = razaoSocial;
    }

    private void impedeRazaoSocialInvalida(String razaoSocial) {
        Pattern pattern = Pattern.compile(Regex.REGEX_RAZAO_SOCIAL_VALIDA);
        Matcher matcher = pattern.matcher(razaoSocial);
        Preconditions.checkArgument(matcher.find(), "Razão Social em formato inválido");
    }

    private void impedeRazaoSocialNula(String razaoSocial) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(razaoSocial), "Razão Social não pode ser nulo");
    }

    public String getRazaoSocial() {
        return razaoSocial;
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
        Preconditions.checkArgument(StringUtils.isNotEmpty(email), "Email não pode ser nulo");
    }

    public String getEmail() {
        return this.email;
    }

    public void setEndereco(Endereco endereco) {
        impedeEnderecoNulo(endereco);
        this.endereco = endereco;
    }

    private void impedeEnderecoNulo(Endereco endereco) {
        Preconditions.checkNotNull(endereco, "Endereço não pode ser nulo");
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
