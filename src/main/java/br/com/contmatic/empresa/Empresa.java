package br.com.contmatic.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

import br.com.contmatic.enums.EnumTipoEndereco;
import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.contmatic.utils.Regex;

public class Empresa {
	
	public static final int EXTENSAO_OBRIGATORIA_CNPJ = 14;
	
	public static final int EXTENSAO_OBRIGATORIA_DATA_ABERTURA = 8;

	private static final int VALOR_CAPITAL_SOCIAL_ZERO = 0;
	
	@NotNull(message = "ID não pode ser nulo.")
    private int id; 
	
    @CNPJ
    @NotNull(message = "CNPJ não pode ser nulo.")
	private String cnpj;

    @Size(max = 70)
    @NotNull(message = "Razão Social não pode ser nula.")
	private String razaoSocial;

    @NotNull(message = "Data Abertura não pode ser nula.")
	private DateTime dataAbertura;

    @NotNull(message = "Capital Social não pode ser nulo.")
	private double capitalSocial;

    @NotNull(message = "Endereço não pode ser nulo.")
	private Endereco endereco;
    
	@NotNull(message="Telefone não pode ser nulo.")
	private Telefone telefone;
	
	@NotNull(message="Email não pode ser nulo.")
	private String email;

	public String getEmail() {
		return email;
	}

	public Empresa(String cnpj) {
		setCnpj(cnpj);
	}
	
	public int getId() {
        return id;
    }

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	    Pattern pattern = Pattern.compile(util.getRegexRazaoSocial());
        Matcher matcher = pattern.matcher(razaoSocial);
        Preconditions.checkArgument(matcher.find(), "Razão Social em formato inválido");
	}

	private void impedeRazaoSocialNula(String razaoSocial) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(razaoSocial), "Razão Social não pode ser nulo");
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setDataAbertura(DateTime dataAbertura) {
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
	
	public EnumTipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
    Regex util = new Regex();
	
	public void setEmail(String email) {
		impedeEmailNulo(email);
		impedeEmailInvalido(email);
		this.email = email;
	}

	private void impedeEmailInvalido(String email) {
	    Pattern pattern = Pattern.compile(util.getRegexValidacaoEmail());
        Matcher matcher = pattern.matcher(email);
        Preconditions.checkArgument(matcher.find(), "Email em formato inválido");
	}


	private void impedeEmailNulo(String email) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(email), "Email não pode ser nulo");
	}

	@NotNull(message = "Tipo de Telefone não pode ser nulo.")
	private EnumTipoTelefone tipoTelefone;

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