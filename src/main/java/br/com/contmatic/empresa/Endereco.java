package br.com.contmatic.empresa;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public class Endereco {
	
	public static final int EXTENSAO_OBRIGATORIA_CEP = 8;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	Endereco(String logradouro, String numero, String complemento, String bairro, String cep) {
		setLogradouro(logradouro);
		setNumero(numero);
		setComplemento(complemento);
		setBairro(bairro);
		setCep(cep);
	}

	Endereco() {
	}

	public void setLogradouro(String logradouro) {
		impedeLogradouroNulo(logradouro);
		this.logradouro = logradouro;
	}

	private void impedeLogradouroNulo(String logradouro) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(logradouro), "Logradouro não pode ser nulo");
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setNumero(String numero) {
		impedeNumeroNulo(numero);
		impedeCaracteresNaoNumericosEmNumero(numero);
		this.numero = numero;
	}

	private void impedeCaracteresNaoNumericosEmNumero(String numero) {
		impedeCaracteresNaoNumericosCep(numero);
	}

	private void impedeNumeroNulo(String numero) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(numero), "Número não pode ser nulo");
	}

	public String getNumero() {
		return this.numero;
	}

	public void setComplemento(String complemento) {
		impedeComplementoNulo(complemento);
		this.complemento = complemento;
	}

	private void impedeComplementoNulo(String complemento) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(complemento), "Complemento não pode ser nulo");
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setBairro(String bairro) {
		impedeBairroNulo(bairro);
		this.bairro = bairro;
	}

	private void impedeBairroNulo(String bairro) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(bairro), "Bairro não pode ser nulo");
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setCep(String cep) {
		impedeCepNulo(cep);
		obrigaCepCom8Digitos(cep);
		impedeCaracteresNaoNumericosCep(cep);
		this.cep = cep;
	}

	private void impedeCaracteresNaoNumericosCep(String cep) {
		char[] cepComoArrayChars = cep.toCharArray();
		for (char caracterCep : cepComoArrayChars) {
			if (!Character.isDigit(caracterCep))  {
				throw new IllegalArgumentException("CEP apenas com Números.");
			}
		}	
	}

	private void obrigaCepCom8Digitos(String cep) {
		if (cep.length() != EXTENSAO_OBRIGATORIA_CEP) {
			throw new IllegalArgumentException("Cep precisa ter " + EXTENSAO_OBRIGATORIA_CEP +  " dígitos.");
		}
	}

	private void impedeCepNulo(String cep) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(cep), "CEP não pode ser nulo");
	}

	public String getCep() {
		return this.cep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Endereco other = (Endereco) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public String toString() {
		return "Endereço: " + logradouro + ", " + numero + ", " + complemento + ", " + bairro + ", " + "CEP " + cep
				+ ".\n";
	}

}