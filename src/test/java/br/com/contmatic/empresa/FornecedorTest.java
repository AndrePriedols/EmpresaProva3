package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
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

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class FornecedorTest {
    
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public Set<String> getErros(Fornecedor fornecedor) {
        Set<String> erros = new HashSet<>();
        for(ConstraintViolation<Fornecedor> constraintViolation : validator.validate(fornecedor)) {
            erros.add(constraintViolation.getMessageTemplate());
            System.out.println(constraintViolation.getMessageTemplate());
        }
        return erros;
    }

    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }

    Fornecedor fornecedorTeste = Fixture.from(Fornecedor.class).gimme("fornecedorValido");

    @Test
    public void deve_aceitar_fornecedor_valido() {
        assertTrue(getErros(fornecedorTeste).isEmpty());
    }
   
    @Test
    public void deve_aceitar_id_valido() {
        assertFalse(getErros(fornecedorTeste).contains("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_invalido() {
        fornecedorTeste.setId(null);
        assertThat(getErros(fornecedorTeste), hasItem("ID não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_letras() {
        fornecedorTeste.setId("a");
        assertThat(getErros(fornecedorTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_caracter_especial() {
        fornecedorTeste.setId("@");
        assertThat(getErros(fornecedorTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_espaco() {
        fornecedorTeste.setId("1 1");
        assertThat(getErros(fornecedorTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        fornecedorTeste.setCnpj(null);
        assertThat(getErros(fornecedorTeste), hasItem("CNPJ não pode ser nulo."));
    }

    @Test
    public void c_nao_deve_aceitar_cnpj_menor_14_caracteres() {
        fornecedorTeste.setCnpj("1234567890123");
        assertThat(getErros(fornecedorTeste), hasItem("CNPJ em formato inválido."));
    }

    @Test
    public void d_nao_deve_aceitar_cnpj_maior_14_caracteres() {
        fornecedorTeste.setCnpj("123456789012345");
        assertThat(getErros(fornecedorTeste), hasItem("CNPJ em formato inválido."));
    }

    @Test
    public void f_nao_deve_aceitar_cnpj_com_letras() {
        fornecedorTeste.setCnpj("1234567890123f");
        assertThat(getErros(fornecedorTeste), hasItem("CNPJ em formato inválido."));
    }

    @Test
    public void g_nao_deve_aceitar_cnpj_com_caracter_especial() {
        fornecedorTeste.setCnpj("1234567890123@");
        assertThat(getErros(fornecedorTeste), hasItem("CNPJ em formato inválido."));
    }
    
    @Test
    public void h_nao_deve_aceitar_caracter_com_espaco() {
        fornecedorTeste.setCnpj("123456789 1234");
        assertThat(getErros(fornecedorTeste), hasItem("CNPJ em formato inválido."));
    }

    @Test
    public void deve_respeitar_o_get_set_cnpj() {
        Fornecedor fornecedorTeste = new Fornecedor("12345678901234");
        assertTrue("Get e Set Cnpj deve funcionar.", fornecedorTeste.getCnpj().equals("12345678901234"));
    }

    @Test
    public void j_deve_aceitar_razao_social_valida() {
        fornecedorTeste.setRazaoSocial("Lero Lero.SA");
        assertFalse(getErros(fornecedorTeste).contains("Razão Social com caracteres inválidos."));
    }

    @Test
    public void k_nao_deve_aceitar_razao_social_nula() {
        fornecedorTeste.setRazaoSocial(null);
        assertThat(getErros(fornecedorTeste), hasItem("Razão Social não pode ser nula."));
    }

    @Test
    public void deve_respeitar_o_get_set_razao_social() {
        fornecedorTeste.setRazaoSocial("Oliveiras");
        assertTrue("Get e Set Cnpj deve funcionar.", fornecedorTeste.getRazaoSocial().equals("Oliveiras"));
    }

    @Test
    public void deve_aceitar_email_valido() {
        fornecedorTeste.setEmail("anitta.suellen@gmail.com");
        assertTrue(getErros(fornecedorTeste).isEmpty());
    }

    @Test
    public void nao_deve_aceitar_email_fornecedor_nulo() {
        fornecedorTeste.setEmail(null);
        assertThat(getErros(fornecedorTeste), hasItem("Email não pode ser nulo ou vazio."));
    }

    @Test
    public void nao_deve_aceitar_email_mais_de_uma_arroba() {
        fornecedorTeste.setEmail("@geovanemacuser@apple.com");
        assertThat(getErros(fornecedorTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_iniciando_arroba() {
        fornecedorTeste.setEmail("ge@ovanemacuser@apple.com");
        assertThat(getErros(fornecedorTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_iniciando_ponto() {
        fornecedorTeste.setEmail(".geovanemacuser@apple.com");
        assertThat(getErros(fornecedorTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_terminando_arroba() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com@");
        assertThat(getErros(fornecedorTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_terminando_ponto() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com.");
        assertThat(getErros(fornecedorTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void deve_respeitar_o_get_set_email_fornecedor() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com");
        assertTrue("Get e Set Email deve funcionar.", fornecedorTeste.getEmail().equals("geovanemacuser@apple.com"));
    }


    @Test
    public void y_deve_aceitar_fornecedor_valido() {
        fornecedorTeste.setEndereco(new Endereco("Rua 1", "408", "Ap 2", "02030040"));
        assertFalse(getErros(fornecedorTeste).contains("Endereço não pode ser nulo."));
    }

    @Test
    public void z_nao_deve_aceitar_fornecedor_nulo() {
        fornecedorTeste.setEndereco(null);
        assertThat(getErros(fornecedorTeste), hasItem("Endereço não pode ser nulo."));
    }

    @Test(timeout = 100)
    public void z00_deve_respeitar_o_get_set_fornecedor() {
        Endereco endereco1 = new Endereco("Rua 1", "408", "Ap 2", "02030040");
        fornecedorTeste.setEndereco(endereco1);
        boolean a = fornecedorTeste.getEndereco() == endereco1;
        assertTrue("Get e Set Endereço deve funcionar.", a);
    }
    
    @Test
    public void equals_deve_ser_true_cnpj_igual() {
        Fornecedor fornecedorTeste2 = new Fornecedor("12345678901234");
        Fornecedor fornecedorTeste = new Fornecedor("12345678901234");
        boolean a = fornecedorTeste.equals(fornecedorTeste2);
        assertTrue("Cnpj igual representa Empresa igual", a);
    }

    @Test
    public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
        assertNotEquals("Equals dá false se objetos forem de classes diferentes", fornecedorTeste, new String());
    }

    @Test
    public void equals_deve_ser_false_cnpj_diferente() {
        Fornecedor fornecedorTeste2 = new Fornecedor("12345678901235");
        assertNotEquals("Cnpj diferente representa Empresa diferente", fornecedorTeste, fornecedorTeste2);
    }

    @Test
    public void z07_testar_hashcode_igual() {
        Fornecedor fornecedorTeste2 = new Fornecedor("12345678901234");
        Fornecedor fornecedorTeste = new Fornecedor("12345678901234");
        assertTrue(fornecedorTeste.hashCode() == fornecedorTeste2.hashCode());
    }

    @Test
    public void testar_hashcode_diferente() {
        Fornecedor fornecedorTeste2 = new Fornecedor("12345678901235");
        assertFalse(fornecedorTeste.hashCode() == fornecedorTeste2.hashCode());
    }

    @Test
    public void to_string_deve_conter_razao_social() {
        fornecedorTeste.setRazaoSocial("Lero Lero");
        assertTrue("Confere se Razão Social Fornecedor está no toString", fornecedorTeste.toString().contains(fornecedorTeste.getRazaoSocial()));
    }

    @Test
    public void to_string_deve_conter_cpf() {
        fornecedorTeste.setCnpj("12345678901234");
        assertTrue("Confere se Cnpj está no toString", fornecedorTeste.toString().contains(fornecedorTeste.getCnpj()));
    }

    @Test
    public void to_string_deve_conter_email() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com");
        assertTrue("Confere se Email Fornecedor está no toString", fornecedorTeste.toString().contains(fornecedorTeste.getEmail()));
    }

}
