package br.com.contmatic.empresa;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

public class Empresa {
	
	public static final int EXTENSAO_OBRIGATORIA_CNPJ = 14;
	
	public static final int EXTENSAO_OBRIGATORIA_DATA_ABERTURA = 8;

	private static final int VALOR_CAPITAL_SOCIAL_ZERO = 0;

	private String cnpj;

	private String razaoSocial;

	private DateTime dataAbertura;

	private double capitalSocial;

	private Endereco endereco;

	private Funcionario[] listaFuncionarios;

	public Empresa(String cnpj) {
		setCnpj(cnpj);
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
				throw new IllegalArgumentException("Cnpj apenas com Números.");
			}
		}		
	}

	private void verificaTamanhoCnpj14Digitos(String cnpj) {
		if (cnpj.length() != EXTENSAO_OBRIGATORIA_CNPJ) {
			throw new IllegalArgumentException("Cnpj deve ter " + EXTENSAO_OBRIGATORIA_CNPJ + " digitos.");
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
		this.razaoSocial = razaoSocial;
	}

	private void impedeRazaoSocialNula(String razaoSocial) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(razaoSocial), "Razão Social não pode ser nula");
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setDataAbertura(DateTime dataAbertura) {
		impedeDataAberturaNula(dataAbertura);
		impedeDataAberturaPosteriorDataAtual(dataAbertura);
	    this.dataAbertura = dataAbertura;
	}

    private void impedeDataAberturaPosteriorDataAtual(DateTime dataAbertura) {
        Preconditions.checkArgument(dataAbertura.isBeforeNow(), "Data abertura não pode ser posteior à atual.");
    }

	private void impedeDataAberturaNula(DateTime dataAbertura) {
		Preconditions.checkNotNull(dataAbertura, "Data abertura não pode ser nulo");
	}
	
	public DateTime getDataAbertura() {
		return this.dataAbertura;
	}

	public void setCapitalSocial(double capitalSocial) {
		verificaCapitalSocialPositivo(capitalSocial);
		this.capitalSocial = capitalSocial;
	}

	private void verificaCapitalSocialPositivo(double capitalSocial) {
		if (capitalSocial <= VALOR_CAPITAL_SOCIAL_ZERO) {
			throw new IllegalArgumentException("Capital Social deve ser maior que " + VALOR_CAPITAL_SOCIAL_ZERO + ".");
		}
	}

	public double getCapitalSocial() {
		return this.capitalSocial;
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

	public void setFuncionarios(Funcionario[] listaFuncionarios) {
		impedeListaFuncionariosNula(listaFuncionarios);
		this.listaFuncionarios = listaFuncionarios;
	}

	private void impedeListaFuncionariosNula(Funcionario[] listaFuncionarios) {
		Preconditions.checkNotNull(listaFuncionarios, "Lista funcionários não pode ser nulo");
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