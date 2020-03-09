package br.com.contmatic.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.contmatic.utils.Regex;

public class Fornecedor {
	
	public static final int EXTENSAO_OBRIGATORIA_CNPJ = 14;
	
	@NotNull(message = "ID não pode ser nulo.")
    private int id;
	
    @CNPJ
    @NotNull(message = "CNPJ não pode ser nulo.")
	private String cnpj;

    @NotNull(message = "Razão Social não pode ser nula.")
	private String razaoSocial;

    @NotNull(message = "Email não pode ser nulo.")
	private String email;

    @NotNull(message = "Endereço não pode ser nulo.")
	private Endereco endereco;
	
	@NotNull(message="Telefone não pode ser nulo.")
	private Telefone telefone;

	public Fornecedor(String cnpj) {
		setCnpj(cnpj);
	}
	
    Regex util = new Regex();

    public int getId() {
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
		for (char caracterCnpj : cnpjComoArrayChars) {
			if (!Character.isDigit(caracterCnpj))  {
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