package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

import br.com.contmatic.enums.EnumNomeSetor;

public class Setor {

	private static final int QUANTIDADE_FUNCIONARIOS_ZERO = 0;

	private static final int PRIMEIRO_DIGITO_RESPONSAVEL = 0;
	
	@NotNull(message = "ID não pode ser nulo.")
    private int id; 
	
    @NotNull(message = "Nome Setor não pode ser nulo.")
	private EnumNomeSetor nomeSetor;

    @NotNull(message = "Ramal não pode ser nulo.")
	private String ramal;

    @NotNull(message = "Responsável não pode ser nulo.")
	private String responsavel;

	private int quantidadeFuncionarios;

	public Setor(EnumNomeSetor nomeSetor) {
		setNome(nomeSetor);
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public void setNome(EnumNomeSetor nomeSetor) {
		impedeNomeNulo(nomeSetor);
		this.nomeSetor = nomeSetor;
	}

	private void impedeNomeNulo(EnumNomeSetor nomeSetor) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(nomeSetor.toString()), "Nome Setor não pode ser nulo");
	}

	public EnumNomeSetor getNome() {
		return this.nomeSetor;
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
		result = prime * result + ((nomeSetor == null) ? 0 : nomeSetor.hashCode());
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
		if (nomeSetor == null) {
			if (other.nomeSetor != null)
				return false;
		} else if (!nomeSetor.equals(other.nomeSetor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Setor [" + (nomeSetor != null ? "nome setor =" + nomeSetor + ", " : "") + (ramal != null ? "ramal=" + ramal + ", " : "")
				+ (responsavel != null ? "responsavel=" + responsavel : "") + "]";
	}

}