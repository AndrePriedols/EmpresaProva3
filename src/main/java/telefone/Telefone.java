package telefone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import br.com.contmatic.utils.Regex;

public class Telefone {

    @Min(value = 1, message = "ID não pode ser menor que 1.")
    private Integer id;

    @NotNull(message = "Tipo telefone não pode ser nulo.")
    private EnumTipoTelefone tipoTelefone;

    @NotBlank(message = "DDD não pode ser deixado em branco.")
    @Size(max = 3, min = 2, message = "DDD no máximo com 3 dígitos.")
    @javax.validation.constraints.Pattern(regexp = Regex.REGEX_TELEFONE_DDD, message = "DDD aceita apenas números.")
    private String ddd;

    @NotBlank(message = "Telefone não pode ser nulo.")
    @Size(max = 9, min = 8, message = "Telefone entre 8 e 9 dígitos.")
    @javax.validation.constraints.Pattern(regexp = Regex.REGEX_TELEFONE, message = "Número Telefone apenas números.")
    private String numeroTelefone;

    public Telefone() {
    }

    public Telefone(EnumTipoTelefone tipoTelefone, String ddd, String numeroTelefone) {
        this.tipoTelefone = tipoTelefone;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
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
