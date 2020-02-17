package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.contmatic.enums.EnumTipoLogradouro;
import br.com.contmatic.enums.EnumUF;

public class Endereco {

    @NotNull(message = "ID não pode ser nulo.")
    private int id;
    
    @NotEmpty
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

    Endereco(EnumTipoLogradouro tipoLogradouro, String logradouro, String numero, String complemento, String bairro, String cidade, EnumUF uf, String cep) {
        setTipoLogradouro(tipoLogradouro);
    	setLogradouro(logradouro);
        setNumero(numero);
        setComplemento(complemento);
        setBairro(bairro);
        setCidade(cidade);
        setUF(uf);
        setCep(cep);        
    }
    
    Endereco(String logradouro, String numero, String complemento, String bairro, String cep) {
    	setLogradouro(logradouro);
        setNumero(numero);
        setComplemento(complemento);
        setBairro(bairro);
        setCep(cep);        
    }
    
	public Endereco() {    	
    }
    
    public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public EnumUF getUF() {
		return UF;
	}

	public void setUF(EnumUF uF) {
		UF = uF;
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
