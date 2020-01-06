package br.com.contmatic.enums;

public enum EnumTipoEstadoCivil {
	
	SOLTEIRO(1, "Solteiro"),	
	CASADO(2, "Casado"),
	SEPARADO(3, "Separado"),	
	DIVORCIADO(4, "Divorciado"),	
	VIUVO(5, "Viúvo");
	
    private int id;
	private String descricao;
	
	private EnumTipoEstadoCivil(int id, String descricao) {
		this.id = id;
	    this.descricao = descricao;
	}
	
	public int getId() {
	    return this.id;
	}
	
	public String getDescricao() {
	    return this.descricao;
	}

}
