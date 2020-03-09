package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Preconditions;

import br.com.contmatic.enums.EnumNomeSetor;

public class Setor {

	private static final int QUANTIDADE_FUNCIONARIOS_ZERO = 0;

	private static final int PRIMEIRO_DIGITO_RESPONSAVEL = 0;
	
	@NotNull(message = "ID não pode ser nulo.")
    private int id; 
	
    @NotNull(message = "Nome Setor não pode ser nulo.")
	private EnumNomeSetor nome;

    @NotNull(message = "Ramal não pode ser nulo.")
	private String ramal;

    @NotNull(message = "Responsável não pode ser nulo.")
	private String responsavel;

	private int quantidadeFuncionarios;

	public Setor(EnumNomeSetor nome) {
		setNome(nome);
	}
	
	public Setor() {
		
	}
	
	public int getId() {
        return id;
    }

	public void setNome(EnumNomeSetor nome) {
		impedeNomeNulo(nome);
		this.nome = nome;
	}

	private void impedeNomeNulo(EnumNomeSetor nome) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(nome.toString()), "Nome Setor não pode ser nulo");
	}

	public EnumNomeSetor getNome() {
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