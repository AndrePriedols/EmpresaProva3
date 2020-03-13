package br.com.contmatic.empresa;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.contmatic.utils.Regex;

public class Telefone {

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID, message="ID só pode conter números.")
    private String id;

    @NotNull(message = "Tipo telefone não pode ser nulo.")
    private EnumTipoTelefone tipoTelefone;

    @NotBlank(message = "DDD não pode ser deixado em branco.")
    @Max(value=3, message="DDD no máximo com 3 dígitos.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_TELEFONE_DDD, message="DDD aceita apenas números.")
    private String ddd;

    @NotNull(message = "Telefone não pode ser nulo.")
    @Size(max = 9, min = 8)
    @javax.validation.constraints.Pattern(regexp="[0-9]")
    private String numeroTelefone;

    public Telefone() {
    }

    public Telefone(String id, EnumTipoTelefone tipoTelefone, String ddd, String numeroTelefone) {
        super();
        this.id = id;
        this.tipoTelefone = tipoTelefone;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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
