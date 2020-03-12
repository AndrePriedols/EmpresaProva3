package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class EnderecoTest {
    
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public Set<String> getErros(Endereco endereco) {
        Set<String> erros = new HashSet<>();
        for(ConstraintViolation<Endereco> constraintViolation : validator.validate(endereco)) {
            erros.add(constraintViolation.getMessageTemplate());
            System.out.println(constraintViolation.getMessageTemplate());
        }
        return erros;
    }

    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }

    @BeforeClass
    public static void mensagem_inicio_para_testes_endereco() {
        System.out.println("Início dos testes da classe Endereço.");
    }

    @Before
    public void mensagem_reiterada_de_testes_endereco() {
        System.out.println("Método sendo testado.");
    }

    @After
    public void mensagem_reiterada_para_prosseguir_com_testes_endereco() {
        System.out.println("Seguir para próximo método.");
    }

    Endereco enderecoTeste = Fixture.from(Endereco.class).gimme("enderecoValido");

    @Test
    public void construtor_funciona_com_valores_validos() {
        assertTrue(getErros(enderecoTeste).isEmpty());
    }

    @Test
    public void deve_aceitar_logradouro_com_numero_caracteres_corretos() {
        assertFalse(getErros(enderecoTeste).contains("Logradouro deve ter entre 1 e 70 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_logradouro_nulo() {
        enderecoTeste.setLogradouro(null);
        assertThat(getErros(enderecoTeste), hasItem("Logradouro não pode ser nulo."));

    }

    @Test
    public void deve_respeitar_o_get_set_logradouro() {
        String logradouro = "Rua 1";
        enderecoTeste.setLogradouro("Rua 1");
        assertEquals(enderecoTeste.getLogradouro(), logradouro);
    }

    @Test
    public void deve_aceitar_numero_quantidade_caracteres_correta() {
        assertFalse(getErros(enderecoTeste).contains("Número só pode ter até 10 caracteres."));
    }
    
    @Test
    public void nao_deve_aceitar_numero_quantidade_caracteres_incorreta() {
        enderecoTeste.setNumero("99999999999");
        assertThat(getErros(enderecoTeste), hasItem("Número só pode ter até 10 caracteres."));
    }
    
    @Test
    public void deve_aceitar_numero_apenas_caracteres_numericos() {
        assertFalse(getErros(enderecoTeste).contains("Número só aceita caracteres numéricos."));
    }
    
    @Test
    public void nao_deve_aceitar_numero_nulo() {
        enderecoTeste.setNumero(null);
        assertThat(getErros(enderecoTeste), hasItem("Número não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_numero_com_letras() {
        enderecoTeste.setNumero("9a9c99");
        assertThat(getErros(enderecoTeste), hasItem("Número só aceita caracteres numéricos."));
    }

    @Test
    public void nao_deve_aceitar_numero_com_caracteres_especiais() {
        enderecoTeste.setNumero("12@");
        assertThat(getErros(enderecoTeste), hasItem("Número só aceita caracteres numéricos."));
    }

    @Test
    public void nao_deve_aceitar_numero_com_espaco() {
        enderecoTeste.setNumero("12 3");
        assertThat(getErros(enderecoTeste), hasItem("Número só aceita caracteres numéricos."));
    }

    @Test
    public void deve_respeitar_o_get_set_numero() {
        String numero = "12";
        enderecoTeste.setNumero("12");
        assertEquals(enderecoTeste.getNumero(), numero);
    }

    @Test
    public void deve_aceitar_complemento_com_extensao_correta() {
        assertFalse(getErros(enderecoTeste).contains("Complemento só pode ter até 30 caracteres."));        
    }
    
    @Test
    public void nao_deve_aceitar_complemento_com_extensao_maior_que_permitido() {
        enderecoTeste.setComplemento("casa amarela ao lado da padaria do Seo Joaquim.");
        assertThat(getErros(enderecoTeste), hasItem("Complemento só pode ter até 30 caracteres."));        
    }

    @Test
    public void nao_deve_aceitar_complemento_nulo() {
        enderecoTeste.setComplemento(null);
        assertThat(getErros(enderecoTeste), hasItem("Complemento não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_complemento() {
        String complemento = "12";
        enderecoTeste.setComplemento("12");
        assertEquals(enderecoTeste.getComplemento(), complemento);
    }

    @Test
    public void deve_aceitar_bairro_com_extensao_correta() {
        assertFalse(getErros(enderecoTeste).contains("Bairro só pode ter até 30 caracteres."));        
    }

    @Test
    public void nao_deve_aceitar_bairro_nulo() {
        enderecoTeste.setBairro(null);
        assertThat(getErros(enderecoTeste), hasItem("Bairro não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_bairro() {
        String bairro = "Jd França";
        enderecoTeste.setBairro("Jd França");
        assertEquals(enderecoTeste.getBairro(), bairro);
    }

    @Test
    public void deve_aceitar_cep_valido() {
        enderecoTeste.setCep("02039020");
        assertFalse(getErros(enderecoTeste).contains("CEP não pode ser nulo."));
        assertFalse(getErros(enderecoTeste).contains("CEP deve ter 8 dígitos."));
        assertFalse(getErros(enderecoTeste).contains("CEP só aceita caracteres numéricos."));        
    }

    @Test
    public void nao_deve_aceitar_cep_nulo() {
        enderecoTeste.setCep(null);
        assertThat(getErros(enderecoTeste), hasItem("CEP não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_cep_menor_8_digitos() {
        enderecoTeste.setCep("0203902");
        assertThat(getErros(enderecoTeste), hasItem("CEP deve ter 8 dígitos."));
    }

    @Test
    public void nao_deve_aceitar_cep_maior_8_digitos() {
        enderecoTeste.setCep("020390202");
        assertThat(getErros(enderecoTeste), hasItem("CEP deve ter 8 dígitos."));
    }

    @Test
    public void nao_deve_aceitar_cep_com_letras() {
        enderecoTeste.setCep("0203902a");
        assertThat(getErros(enderecoTeste), hasItem("CEP só aceita caracteres numéricos."));        
    }

    @Test
    public void nao_deve_aceitar_cep_com_caracteres_especiais() {
        enderecoTeste.setCep("0203902@");
        assertThat(getErros(enderecoTeste), hasItem("CEP só aceita caracteres numéricos."));        
    }

    @Test
    public void nao_deve_aceitar_cep_com_espaco() {
        enderecoTeste.setCep("0203 020");
        assertThat(getErros(enderecoTeste), hasItem("CEP só aceita caracteres numéricos."));        
    }

    @Test
    public void deve_respeitar_o_get_set_cep() {
        String cep = "02039020";
        enderecoTeste.setCep("02039020");
        assertEquals(enderecoTeste.getCep(), cep);
    }

    @Test
    public void equals_deve_ser_true_logradouro_numero_complemento_cep_iguais() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 1", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 1", "02039020");
        assertEquals("Endereços iguais para logradouros/números/complementos/ceps iguais", endereco1, endereco2);
    }

    @Test
    public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 1", "02039020");
        assertNotEquals("Equals dá false se forem objetos de classes diferentes", endereco1, new String());
    }

    @Test
    public void not_equals_se_houver_logradouro_diferente() {
        Endereco endereco1 = new Endereco("Rua 2", "12", "casa 2", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "02039020");
        assertNotEquals("Endereços diferentes, pois logradouros diferentes", endereco1, endereco2);
    }

    @Test
    public void not_equals_se_houver_numero_diferente() {
        Endereco endereco1 = new Endereco("Rua 2", "10", "casa 2", "02039020");
        Endereco endereco2 = new Endereco("Rua 2", "12", "casa 2", "02039020");
        assertNotEquals("Endereços diferentes, pois numeros diferentes", endereco1, endereco2);
    }

    @Test
    public void not_equals_se_houver_complemento_diferente() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 1", "02039020");
        assertNotEquals("Endereços diferentes, pois complementos diferentes", endereco1, endereco2);
    }

    @Test
    public void not_equals_se_houver_cep_diferente() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2",  "02039021");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2",  "02039020");
        assertNotEquals("Endereços diferentes, pois ceps diferentes", endereco1, endereco2);
    }

    @Test
    public void testa_hashcode_igual() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "02039021");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "02039021");
        assertTrue(endereco1.hashCode() == endereco2.hashCode());
    }

    @Test
    public void testa_hashcode_diferente() {
        Endereco endereco1 = new Endereco("Rua 2", "13", "casa 4", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "02039021");
        assertFalse(endereco1.hashCode() == endereco2.hashCode());
    }

    @Test
    public void to_string_deve_conter_logradouro() {
        assertTrue("Confere se Logradouro está no toString", enderecoTeste.toString().contains(enderecoTeste.getLogradouro()));
    }
    
    @Test
    public void to_string_deve_conter_tipo_logradouro() {
        assertTrue("Confere se Tipo Logradouro está no toString", enderecoTeste.toString().contains(enderecoTeste.getTipoLogradouro().toString()));
    }

    @Test
    public void to_string_deve_conter_numero() {
        assertTrue("Confere se Número está no toString", enderecoTeste.toString().contains(enderecoTeste.getNumero()));
    }

    @Test
    public void to_string_deve_conter_complemento() {
        assertTrue("Confere se Complemento está no toString", enderecoTeste.toString().contains(enderecoTeste.getComplemento()));
    }

    @Test
    public void to_string_deve_conter_bairro() {
        assertTrue("Confere se Bairro está no toString", enderecoTeste.toString().contains(enderecoTeste.getBairro()));
    }

    @Test
    public void to_string_deve_conter_cep() {
        assertTrue("Confere se Cep está no toString", enderecoTeste.toString().contains(enderecoTeste.getCep()));
    }

    @Test
    public void atesta_que_logradouro_contem_ce_cedilha() {
        Endereco endereco1 = new Endereco("Rua dos Caiçaras", "12", "casa 2", "02039021");
        assertTrue("Logradouro contém ç", endereco1.getLogradouro().contains("ç"));
    }

//    @Ignore
    @Test
    public void atesta_que_logradouro_nao_contem_ce_cedilha() {
        Endereco endereco1 = new Endereco("Rua dos Pescadores", "12", "casa 2", "02039021");
        assertFalse("Logradouro não contém ç", endereco1.getLogradouro().contains("ç"));
    }

    @AfterClass
    public static void mensagem_final_dos_testes() {
        System.out.println("Fim dos testes da classe Endereço.");
    }

}
