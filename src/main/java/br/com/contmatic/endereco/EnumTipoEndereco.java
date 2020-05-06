package br.com.contmatic.endereco;

public enum EnumTipoEndereco {

                              RESIDENCIAL(1, "Residencial"),
                              COMERCIAL(2, "Comercial"),
                              CORRESPONDENCIA(3, "Correspondência");

    private int codigo;
    private String descricao;

    private EnumTipoEndereco(int codigo, String descricao) {
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
