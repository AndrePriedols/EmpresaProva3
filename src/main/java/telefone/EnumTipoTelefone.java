package telefone;

public enum EnumTipoTelefone {

	FIXO(1, "Fixo"), CELULAR(2, "Celular"), RECADO(3, "Recado");

	private int codigo;
	private String descricao;

	private EnumTipoTelefone(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
