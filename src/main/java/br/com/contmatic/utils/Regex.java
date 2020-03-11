package br.com.contmatic.utils;

public class Regex {

    public static final String REGEX_VALIDACAO_EMAIL = "^[a-zA-Z0-9_-][a-zA-Z0-9._-]+@[a-zA-Z0_9][a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final String REGEX_NOME_VALIDO = "/ˆ[A-Za-záàâãéèêíïóôõöúüçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÜÇÑ'\\s\\-]+$/";
    
    public static final String REGEX_RAZAO_SOCIAL_VALIDA = "/ˆ[A-Za-záàâãéèêíïóôõöüúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÜÇÑ'0-9\\s\\-]+$/";
    
    public static final String REGEX_ENDERECO_NUMERO = "[0-9]";
    
    public static final String REGEX_DATA = "[0-9]";
    
    public static final String REGEX_ID = "[0-9]";
    
    private Regex() {
    }    
}
