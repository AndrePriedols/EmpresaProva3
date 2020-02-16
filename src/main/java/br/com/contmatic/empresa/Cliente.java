package br.com.contmatic.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public class Cliente {
    
    @NotNull(message = "ID não pode ser nulo.")
    private int id; 

    @CPF
    @NotEmpty(message = "CPF não pode ser nulo.")
    private String cpf;

    @Size(max = 70)
    @NotNull(message = "Nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "Endereço não pode ser nulo.")
    private Endereco endereco;

    @NotNull(message = "Email não pode ser nulo.")
    private String emailCliente;

    public Cliente(String cpf) {
        setCpf(cpf);
    }

    Utilitarios util = new Utilitarios();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getEmailCliente() {
        return this.emailCliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Cliente other = (Cliente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [" + (nome != null ? "nome=" + nome + ", " : "") + (cpf != null ? "cpf=" + cpf + ", " : "") + (emailCliente != null ? "emailCliente=" + emailCliente : "") + "]";
    }

}
