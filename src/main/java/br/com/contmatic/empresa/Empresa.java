package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
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

    @NotNull(message = "Lista Funcionários não pode ser nula.")
	private Funcionario[] listaFuncionarios;

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

	public void setFuncionarios(Funcionario[] listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Funcionario[] getFuncionarios() {
		return this.listaFuncionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	public String toString() {
		return "Empresa: " + razaoSocial + ", " + "Cnpj número " + cnpj + ".";
	}

}