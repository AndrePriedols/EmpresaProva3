package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.contmatic.enums.EnumTipoLogradouro;
import br.com.contmatic.enums.EnumUF;

public class Endereco {

    @NotNull(message = "ID não pode ser nulo.")
    private int id;
    
    private EnumTipoLogradouro tipoLogradouro;

    @Size(max = 70)
    @NotNull(message = "Logradouro não pode ser nulo.")
    private String logradouro;

    @NotNull(message = "Número não pode ser nulo.")
    private String numero;

    @NotNull(message = "Complemento não pode ser nulo.")
    private String complemento;

    @NotNull(message = "Bairro não pode ser nulo.")
    private String bairro;

    @NotNull(message = "Cidade não pode ser nulo.")
    private String cidade;

    @NotNull(message = "UF não pode ser nulo.")
    private EnumUF UF;

    @NotNull(message = "CEP não pode ser nulo.")
    private String cep;

    Endereco(String logradouro, String numero, String complemento, String bairro, String cep) {
        setLogradouro(logradouro);
        setNumero(numero);
        setComplemento(complemento);
        setBairro(bairro);
        setCep(cep);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public EnumTipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(EnumTipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return this.cep;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
        result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
        result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
        Endereco other = (Endereco) obj;
        if (cep == null) {
            if (other.cep != null)
                return false;
        } else if (!cep.equals(other.cep))
            return false;
        if (complemento == null) {
            if (other.complemento != null)
                return false;
        } else if (!complemento.equals(other.complemento))
            return false;
        if (logradouro == null) {
            if (other.logradouro != null)
                return false;
        } else if (!logradouro.equals(other.logradouro))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Endereco [tipoLogradouro=" + tipoLogradouro + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade +
            ", UF=" + UF + ", cep=" + cep + "]";
    }

}
