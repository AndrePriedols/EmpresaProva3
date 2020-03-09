package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.contmatic.enums.EnumTipoTelefone;

public class Telefone {
	
	@NotNull(message="Tipo telefone não pode ser nulo.")
	private EnumTipoTelefone tipoTelefone;
	
	@NotNull(message="DDD não pode ser nulo.")
	@Size(max = 2)
	private String ddd;
	
	@NotNull(message="Telefone não pode ser nulo.")
	@Size(max = 9, min = 8)
	private String numeroTelefone;
	
	public Telefone() {		
	}

	public EnumTipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

}