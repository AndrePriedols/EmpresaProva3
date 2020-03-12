package br.com.contmatic.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

import br.com.contmatic.enums.EnumTipoEstadoCivil;
import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.contmatic.utils.Regex;

public class Funcionario {

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

    public static final int TAMANHO_DATA_NASCIMENTO = 8;

    private static final int SALARIO_ZERO = 0;

    @NotNull(message = "ID não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID, message="ID só pode conter números.")
    private String id;

    @NotEmpty(message = "CPF não pode ser nulo ou vazio.")
    @CPF(message="CPF em formato inválido.")
    private String cpf;

    @NotNull(message = "Nome não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_NOME_VALIDO, message="Nome com caracteres inválidos.")
    private String nome;

    @NotNull(message = "Data Nascimento não pode ser nula.")
    @Size(max = 8, min = 8)
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_DATA, message="Data aceita apenas números.")
    private DateTime dataNascimento;

    @NotNull(message = "Estado Civil não pode ser nulo.")
    private EnumTipoEstadoCivil estadoCivil;

    @NotNull(message = "Endereço não pode ser nulo.")
    private Endereco endereco;

    @NotNull(message = "Telefone não pode ser nulo.")
    private Telefone telefone;

    @NotEmpty(message = "Email não pode ser nulo ou vazio.")
    @Length(min=3)
    @Email(message="Email em formato inválido.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_VALIDACAO_EMAIL, message="Email em formato inválido.")
    private String email;

    @NotNull(message = "Data Contratação não pode ser nula.")
    @Size(max = 8, min = 8)
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_DATA, message="Data aceita apenas números.")
    private DateTime dataContratacao;

    @NotNull(message = "Setor não pode ser nulo.")
    private Setor setor;

    @NotNull(message = "Cargo não pode ser nulo.")
    private String cargo;

    @Min(value=1)
    private double salario;

    public Funcionario(String cpf) {
        setCpf(cpf);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

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
            throw new IllegalArgumentException("Cpf deve ter " + EXTENSAO_OBRIGATORIA_CPF + " digitos.");
        }
    }

    private void impedeCpfNulo(String cpf) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(cpf), "CPF não pode ser nulo");
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setNome(String nome) {
        impedeNomeNulo(nome);
        impedeNomeInvalido(nome);
        this.nome = nome;
    }

    private void impedeNomeInvalido(String nome) {
        Pattern pattern = Pattern.compile(Regex.REGEX_NOME_VALIDO);
        Matcher matcher = pattern.matcher(nome);
        Preconditions.checkArgument(matcher.find(), "Nome em formato inválido");
    }

    private void impedeNomeNulo(String nome) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(email), "Nome não pode ser nulo");
    }

    public String getNome() {
        return this.nome;
    }

    public void setDataNascimento(DateTime dataNascimento) {
        impedeDataNascimentoNula(dataNascimento);
        impedeDataNascimentoPosteriorAtual(dataNascimento);
        this.dataNascimento = dataNascimento;
    }

    private void impedeDataNascimentoPosteriorAtual(DateTime dataNascimento) {
        Preconditions.checkArgument(dataNascimento.isBeforeNow(), "Data Nascimento não pode ser posteior à atual.");
    }

    private void impedeDataNascimentoNula(DateTime dataNascimento) {
        Preconditions.checkNotNull(dataNascimento, "Data de nascimento não pode ser nula");
    }

    public DateTime getDataNascimento() {
        return this.dataNascimento;
    }

    public void setEstadoCivil(EnumTipoEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public EnumTipoEstadoCivil getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEndereco(Endereco endereco) {
        impedeEnderecoNulo(endereco);
        this.endereco = endereco;
    }

    private void impedeEnderecoNulo(Endereco endereco) {
        Preconditions.checkNotNull(endereco, "Endereço não pode ser nulo");
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public EnumTipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        impedeEmailNulo(email);
        impedeEmailInvalido(email);
        this.email = email;
    }

    private void impedeEmailInvalido(String email) {
        Pattern pattern = Pattern.compile(Regex.REGEX_VALIDACAO_EMAIL);
        Matcher matcher = pattern.matcher(email);
        Preconditions.checkArgument(matcher.find(), "Email em formato inválido");
    }

    private void impedeEmailNulo(String email) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(email), "Email não pode ser nulo");
    }

    @NotNull(message = "Tipo de Telefone não pode ser nulo.")
    private EnumTipoTelefone tipoTelefone;

    public void setDataContratacao(DateTime dataContratacao) {
        impedeDataContratacaoNula(dataContratacao);
        impedeDataContratacaoPosteriorAtual(dataContratacao);
        this.dataContratacao = dataContratacao;
    }

    private void impedeDataContratacaoPosteriorAtual(DateTime dataContratacao) {
        Preconditions.checkArgument(dataContratacao.isBeforeNow(), "Data Contratação não pode ser posteior à atual.");
    }

    private void impedeDataContratacaoNula(DateTime dataContratacao) {
        Preconditions.checkNotNull(dataContratacao, "Data de Contratação não pode ser nula");
    }

    public DateTime getDataContratacao() {
        return this.dataContratacao;
    }

    public void setSetor(Setor setor) {
        impedeSetorNulo(setor);
        this.setor = setor;
    }

    private void impedeSetorNulo(Setor setor) {
        Preconditions.checkNotNull(setor, "Nome do setor não pode ser nulo.");
    }

    public Setor getSetor() {
        return this.setor;
    }

    public void setCargo(String cargo) {
        impedeCargoNulo(cargo);
        this.cargo = cargo;
    }

    private void impedeCargoNulo(String cargo) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(cargo), "Cargo não pode ser nulo");
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setSalario(double salario) {
        obrigaSalarioPositivo(salario);
        this.salario = salario;
    }

    private void obrigaSalarioPositivo(double salario) {
        if (salario <= SALARIO_ZERO) {
            throw new IllegalArgumentException("Salário deve ser maior que " + SALARIO_ZERO + ".");
        }
    }

    public double getSalario() {
        return this.salario;
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
