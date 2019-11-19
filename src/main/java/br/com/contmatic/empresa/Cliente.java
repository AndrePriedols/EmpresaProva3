package br.com.contmatic.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public class Cliente {

    public static final int EXTENSAO_OBRIGATORIA_CPF = 11;

    private static final int TAMANHO_CPF_SEM_SEGUNDO_DIGITO_VERIFICADOR = 10;

    private static final int TAMANHO_CPF_SEM_DIGITOS_VERIFICADORES = 8;

    private static final int POSICAO_SEGUNDO_DIGITO_VERIFICADOR = 10;

    private static final int POSICAO_PRIMEIRO_DIGITO_VERIFICADOR = 9;

    private static final int DIVISOR_VALIDACAO_DIGITOS_VERIFICADORES = 11;

    private static final int VALOR_INICIAL_ZERO_PARA_SOMA_VALIDACAO_DIGITOS_VERIFICADORES = 0;

    private static final int DECIMO_PRIMEIRO_DIGITO_CPF = 10;

    private static final int DECIMO_DIGITO_CPF = 9;

    private static final int NONO_DIGITO_CPF = 8;

    private static final int OITAVO_DIGITO_CPF = 7;

    private static final int SETIMO_DIGITO_CPF = 6;

    private static final int SEXTO_DIGITO_CPF = 5;

    private static final int QUINTO_DIGITO_CPF = 4;

    private static final int QUARTO_DIGITO_CPF = 3;

    private static final int TERCEIRO_DIGITO_CPF = 2;

    private static final int SEGUNDO_DIGITO_CPF = 1;

    private static final int PRIMEIRO_DIGITO_CPF = 0;

    private String cpf;

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    private Endereco endereco;

    private String emailCliente;

    public Cliente(String cpf) {
        setCpf(cpf);
    }
    
    Utilitarios util = new Utilitarios();

    public void setCpf(String cpf) {
        impedeCpfNulo(cpf);
        obrigaCpf11Digitos(cpf);
        impedeCaracteresNaoNumericosCpf(cpf);
        impedeCpfTodosDigitosIguais(cpf);
        validadorCpf(cpf);
        this.cpf = cpf;
    }

    private void validadorCpf(String cpf) {
        int somaParaValidarPrimeiroDigitoVerificador = VALOR_INICIAL_ZERO_PARA_SOMA_VALIDACAO_DIGITOS_VERIFICADORES;
        int somaraParaValidarSegundoDigitoVerificador = VALOR_INICIAL_ZERO_PARA_SOMA_VALIDACAO_DIGITOS_VERIFICADORES;
        somaParaValidarPrimeiroDigitoVerificador = realizaSomaPrimeiroDigitoVerificador(cpf, somaParaValidarPrimeiroDigitoVerificador);
        int restoDivisaoSomaPrimeiroDigitoPor11 = somaParaValidarPrimeiroDigitoVerificador % DIVISOR_VALIDACAO_DIGITOS_VERIFICADORES;
        char primeiroDigitoVerificadorCpfParaValidar = cpf.charAt(POSICAO_PRIMEIRO_DIGITO_VERIFICADOR);
        int primeiroDigitoVerificadorParaNumero = Character.getNumericValue(primeiroDigitoVerificadorCpfParaValidar);
        somaraParaValidarSegundoDigitoVerificador = realizaSomaSegundoDigitoVerificador(cpf, somaraParaValidarSegundoDigitoVerificador);
        int restoDivisaoSomaSegundoDigitoVerificadorPor11 = somaraParaValidarSegundoDigitoVerificador % DIVISOR_VALIDACAO_DIGITOS_VERIFICADORES;
        char segundoDigitoVerificadorCpfParaValidar = cpf.charAt(POSICAO_SEGUNDO_DIGITO_VERIFICADOR);
        int segundoDigitoVerificadorParaNumero = Character.getNumericValue(segundoDigitoVerificadorCpfParaValidar);
        comparaDigitosVerificadoresComRegraCpf(restoDivisaoSomaPrimeiroDigitoPor11, primeiroDigitoVerificadorParaNumero, restoDivisaoSomaSegundoDigitoVerificadorPor11,
            segundoDigitoVerificadorParaNumero);
    }

    private void comparaDigitosVerificadoresComRegraCpf(int restoDivisaoSomaPrimeiroDigitoPor11, int primeiroDigitoVerificadorParaNumero, int restoDivisaoSomaSegundoDigitoVerificadorPor11,
                                                        int segundoDigitoVerificadorComoNumero) {
        if (((restoDivisaoSomaPrimeiroDigitoPor11 == 0 || restoDivisaoSomaPrimeiroDigitoPor11 == 1) && primeiroDigitoVerificadorParaNumero == 0) ||
            ((restoDivisaoSomaPrimeiroDigitoPor11 != 0 && restoDivisaoSomaPrimeiroDigitoPor11 != 1 && restoDivisaoSomaPrimeiroDigitoPor11 <= 10) &&
                11 - restoDivisaoSomaPrimeiroDigitoPor11 == primeiroDigitoVerificadorParaNumero) &&
                ((restoDivisaoSomaSegundoDigitoVerificadorPor11 == 0 || restoDivisaoSomaSegundoDigitoVerificadorPor11 == 1) && segundoDigitoVerificadorComoNumero == 0) ||
            ((restoDivisaoSomaSegundoDigitoVerificadorPor11 != 0 && restoDivisaoSomaSegundoDigitoVerificadorPor11 != 1 && restoDivisaoSomaSegundoDigitoVerificadorPor11 <= 10) &&
                11 - restoDivisaoSomaSegundoDigitoVerificadorPor11 == segundoDigitoVerificadorComoNumero)) {
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private int realizaSomaSegundoDigitoVerificador(String cpf, int somaParaValidarSegundoDigitoVerificador) {
        for(int indiceDigitoCpf = PRIMEIRO_DIGITO_CPF ; indiceDigitoCpf <= EXTENSAO_OBRIGATORIA_CPF ; indiceDigitoCpf++) {
            while (indiceDigitoCpf <= POSICAO_PRIMEIRO_DIGITO_VERIFICADOR) {
                char digitoCpfString = cpf.charAt(indiceDigitoCpf);
                int digitoCpfParaNumero = Character.getNumericValue(digitoCpfString);
                int valorQueDeveSerSomadoParaVerificacaoSegundoDigito = (EXTENSAO_OBRIGATORIA_CPF - indiceDigitoCpf) * digitoCpfParaNumero;
                somaParaValidarSegundoDigitoVerificador = somaParaValidarSegundoDigitoVerificador + valorQueDeveSerSomadoParaVerificacaoSegundoDigito;
                indiceDigitoCpf++;
            }
        }
        return somaParaValidarSegundoDigitoVerificador;
    }

    private int realizaSomaPrimeiroDigitoVerificador(String cpf, int somaParaValidarPrimeiroDigitoVerificador) {
        for(int indiceDigitoCpf = PRIMEIRO_DIGITO_CPF ; indiceDigitoCpf <= EXTENSAO_OBRIGATORIA_CPF ; indiceDigitoCpf++) {
            while (indiceDigitoCpf <= TAMANHO_CPF_SEM_DIGITOS_VERIFICADORES) {
                char digitoCpfString = cpf.charAt(indiceDigitoCpf);
                int digitoCpfParaNumero = Character.getNumericValue(digitoCpfString);
                int valorQueDeveSerSomadoParaVerificacaoPrimeiroDigito = (TAMANHO_CPF_SEM_SEGUNDO_DIGITO_VERIFICADOR - indiceDigitoCpf) * digitoCpfParaNumero;
                somaParaValidarPrimeiroDigitoVerificador = somaParaValidarPrimeiroDigitoVerificador + valorQueDeveSerSomadoParaVerificacaoPrimeiroDigito;
                indiceDigitoCpf++;
            }
        }
        return somaParaValidarPrimeiroDigitoVerificador;
    }

    private void impedeCpfTodosDigitosIguais(String cpf) {
        if (cpf.charAt(SEGUNDO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(TERCEIRO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
            cpf.charAt(QUARTO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(QUINTO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
            cpf.charAt(SEXTO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(SETIMO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
            cpf.charAt(OITAVO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(NONO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
            cpf.charAt(DECIMO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(DECIMO_PRIMEIRO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF)) {
            throw new IllegalArgumentException("Cpf não pode ter todos os dígitos iguais.");
        }
    }

    private void impedeCaracteresNaoNumericosCpf(String cpf) {
        char[] cpfComoArrayChars = cpf.toCharArray();
        for(char caracterCpf : cpfComoArrayChars) {
            if (!Character.isDigit(caracterCpf)) {
                throw new IllegalArgumentException("Cpf apenas com Números.");
            }
        }
    }

    private void obrigaCpf11Digitos(String cpf) {
        if (cpf.length() != EXTENSAO_OBRIGATORIA_CPF) {
            throw new IllegalArgumentException("Cpf deve ter" + EXTENSAO_OBRIGATORIA_CPF + " digitos.");
        }
    }

    private void impedeCpfNulo(String cpf) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(cpf), "CPF não pode ser nulo");
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
        impedeEnderecoNulo(endereco);
        this.endereco = endereco;
    }

    private void impedeEnderecoNulo(Endereco endereco) {
        Preconditions.checkNotNull(endereco, "Nome não pode ser nulo");
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEmailCliente(String emailCliente) {
        impedeEmailNulo(emailCliente);
        impede_email_fora_do_formato(emailCliente);
        this.emailCliente = emailCliente;
    }

    private void impede_email_fora_do_formato(String emailCliente) {
        Pattern pattern = Pattern.compile(util.getRegexValidacaoEmail());
        Matcher matcher = pattern.matcher(emailCliente);
        Preconditions.checkArgument(matcher.find(), "Email em formato inválido");
    }

    private void impedeEmailNulo(String emailCliente) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(emailCliente), "Nome não pode ser nulo");
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
