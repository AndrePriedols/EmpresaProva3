package br.com.contmatic.utils;

public class Regex {

    private static final String REGEX_VALIDACAO_EMAIL = "^[a-zA-Z0-9_-][a-zA-Z0-9._-]+@[a-zA-Z0_9][a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private static final String REGEX_NOME_VALIDO = "/ˆ[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ'\\s\\-]+$/";
    
    private static final String REGEX_RAZAO_SOCIAL_VALIDA = "/ˆ[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ'0-9\\s\\-]+$/";
    
    public Regex() {
    }

    public String getRegexValidacaoEmail() {
        return REGEX_VALIDACAO_EMAIL;
    }
    
    public String getRegexNome() {
    	return REGEX_NOME_VALIDO;
    }
    
    public String getRegexRazaoSocial() {
    	return REGEX_RAZAO_SOCIAL_VALIDA;
    }    
    
}
