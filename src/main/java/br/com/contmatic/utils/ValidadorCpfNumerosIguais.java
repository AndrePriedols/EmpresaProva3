package br.com.contmatic.utils;

import com.google.common.base.Preconditions;

public class ValidadorCpfNumerosIguais {

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

    private ValidadorCpfNumerosIguais() {
    }

    public static String impedeCpfTodosDigitosIguais(String cpf) {
        Preconditions.checkArgument(
            !(cpf.charAt(SEGUNDO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(TERCEIRO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
                cpf.charAt(QUARTO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(QUINTO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
                cpf.charAt(SEXTO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(SETIMO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
                cpf.charAt(OITAVO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(NONO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) &&
                cpf.charAt(DECIMO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF) && cpf.charAt(DECIMO_PRIMEIRO_DIGITO_CPF) == cpf.charAt(PRIMEIRO_DIGITO_CPF)),
            "CPF não pode ter todos os dígitos iguais.");

        return cpf;
    }

}
