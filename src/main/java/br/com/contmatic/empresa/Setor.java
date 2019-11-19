package br.com.contmatic.empresa;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public class Setor {

	private static final int QUANTIDADE_FUNCIONARIOS_ZERO = 0;

	private static final int PRIMEIRO_DIGITO_RESPONSAVEL = 0;
	
	private String nome;

	private String ramal;

	private String responsavel;

	private int quantidadeFuncionarios;

	public Setor(String nome) {
		setNome(nome);
	}

	public void setNome(String nome) {
		impedeNomeNulo(nome);
		this.nome = nome;
	}

	private void impedeNomeNulo(String nome) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(nome), "Nome Setor não pode ser nulo");
	}

	public String getNome() {
		return this.nome;
	}

	public void setRamal(String ramal) {
		impedeRamalNulo(ramal);
		impedeCaracteresNaoNumericosRamal(ramal);
		this.ramal = ramal;

	}

	private void impedeCaracteresNaoNumericosRamal(String ramal) {
		char[] ramalComoArrayChars = ramal.toCharArray();
		for (char caracterRamal: ramalComoArrayChars) {
			if (!Character.isDigit(caracterRamal))  {
				throw new IllegalArgumentException("Ramal apenas com Números.");
			}
		}		
	}

	private void impedeRamalNulo(String ramal) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(ramal), "Ramal não pode ser nulo");
	}

	public String getRamal() {
		return this.ramal;
	}

	public void setResponsavel(String responsavel) {
		impedeResponsavelNulo(responsavel);
		impedeCaracteresNumericosResponsavel(responsavel);
		this.responsavel = responsavel;
	}

	private void impedeCaracteresNumericosResponsavel(String responsavel) {
		for (int caracterResponsavel = PRIMEIRO_DIGITO_RESPONSAVEL; caracterResponsavel < responsavel.length(); caracterResponsavel++) {
			if (Character.isDigit(responsavel.charAt(caracterResponsavel))) {
				throw new IllegalArgumentException("Responsável não pode ter números.");
			}
		}
	}

	private void impedeResponsavelNulo(String responsavel) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(responsavel), "Responsável não pode ser nulo");
	}

	public String getResponsavel() {
		return this.responsavel;
	}

	public void setQuantidadeFuncionarios(int quantidadeFuncionarios) {
		impedeQuantidadeFuncionariosNegativa(quantidadeFuncionarios);
		this.quantidadeFuncionarios = quantidadeFuncionarios;
	}

	private void impedeQuantidadeFuncionariosNegativa(int quantidadeFuncionarios) {
		if (quantidadeFuncionarios < QUANTIDADE_FUNCIONARIOS_ZERO) {
			throw new IllegalArgumentException("Quantidade de Funcionários deve ser maior ou igual a " + QUANTIDADE_FUNCIONARIOS_ZERO + ".");
		}
	}

	public int getQuantidadeFuncionarios() {
		return this.quantidadeFuncionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Setor other = (Setor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Setor [" + (nome != null ? "nome =" + nome + ", " : "") + (ramal != null ? "ramal=" + ramal + ", " : "")
				+ (responsavel != null ? "responsavel=" + responsavel : "") + "]";
	}

}