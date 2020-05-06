package br.com.contmatic.setor;

public enum EnumNomeSetor {

                           FINANCEIRO(1, "Financeiro"),
                           DESENVOLVIMENTO(2, "Desenvolvimento"),
                           COMPRAS(3, "Compras"),
                           COMERCIAL(4, "Comercial"),
                           RECURSOS_HUMANOS(5, "Recursos Humanos");

    private int codigo;
    private String descricao;

    private EnumNomeSetor(int codigo, String descricao) {
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
