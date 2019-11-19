package br.com.contmatic.empresa;

public class Utilitarios {

    private static final String REGEX_VALIDACAO_EMAIL = "^[a-zA-Z0-9_-][a-zA-Z0-9._-]+@[a-zA-Z0_9][a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public Utilitarios() {
    }

    public String getRegexValidacaoEmail() {
        return REGEX_VALIDACAO_EMAIL;
    }
    
    
}
