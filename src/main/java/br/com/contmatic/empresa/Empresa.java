package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

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

	public Empresa(String cnpj) {
		setCnpj(cnpj);
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
		return this.razaoSocial;
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