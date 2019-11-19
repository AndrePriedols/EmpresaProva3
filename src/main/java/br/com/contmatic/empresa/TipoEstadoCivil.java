package br.com.contmatic.empresa;

public enum TipoEstadoCivil {
	
	SOLTEIRO("Solteiro"),
	
	CASADO("Casado"),
	
	SEPARADO("Separado"),
	
	DIVORCIADO("Divorciado"),
	
	VIUVO("Viuvo");
	
	private String descricao;
	
	private TipoEstadoCivil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
