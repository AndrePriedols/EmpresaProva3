package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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

public class ClienteTest {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public Set<String> getErros(Cliente cliente) {
        Set<String> erros = new HashSet<>();
        for (ConstraintViolation<Cliente> constraintViolation : validator.validate(cliente)) {
            erros.add(constraintViolation.getMessageTemplate());
            System.out.println(constraintViolation.getMessageTemplate()); 
        }
        return erros;
    }

    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }

    Cliente clienteTeste = Fixture.from(Cliente.class).gimme("clienteValido");

    @Test
    public void deve_aceitar_cliente_valido() {
        assertTrue(getErros(clienteTeste).isEmpty());
    }
    
    @Test
    public void deve_aceitar_id_valido() {
        assertFalse(getErros(clienteTeste).contains("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_invalido() {
        clienteTeste.setId(null);
        assertThat(getErros(clienteTeste), hasItem("ID não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_letras() {
        clienteTeste.setId("a");
        assertThat(getErros(clienteTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_caracter_especial() {
        clienteTeste.setId("@");
        assertThat(getErros(clienteTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_espaco() {
        clienteTeste.setId("1 1");
        assertThat(getErros(clienteTeste), hasItem("ID só pode conter números."));
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_cpf_nulo() {
        clienteTeste.setCpf(null);
    }

    @Test
    public void deve_aceitar_cpf_valido_digito_zero() {
        clienteTeste.setCpf("46776847070");
        assertFalse(getErros(clienteTeste).contains("CPF em formato inválido."));
    }

    @Test
    public void deve_aceitar_cpf_valido_digito_zero_b() {
        clienteTeste.setCpf("82533451002");
        assertFalse(getErros(clienteTeste).contains("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_invalido_digito_k() {
        clienteTeste.setCpf("65528642050");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_invalido_digito_j() {
        clienteTeste.setCpf("65528642041");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_maior_11_digitos() {
        clienteTeste.setCpf("1234567890123");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_menor_11_digitos() {
        clienteTeste.setCpf("1234567");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_letra() {
        clienteTeste.setCpf("1234567890a");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_caracter_especial() {
        clienteTeste.setCpf("1234567890@");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_espaco() {
        clienteTeste.setCpf("123456 8901");
        assertThat(getErros(clienteTeste), hasItem("CPF em formato inválido."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_todos_digitos_iguais() {
        clienteTeste.setCpf("11111111111");
    }

    @Test
    public void deve_aceitar_nome_conforme_regex() {
        clienteTeste.setNome("André");
        assertFalse(getErros(clienteTeste).contains("Nome com caracteres inválidos."));
    }
    
    @Test
    public void deve_aceitar_nome_extensao_correta() {
        clienteTeste.setNome("André");
        assertFalse(getErros(clienteTeste).contains("Nome deve ter entre 70 e 2 caracteres."));
    }
    
    @Test
    public void nao_deve_aceitar_nome_extensao_incorreta() {
        clienteTeste.setNome("A");
        assertTrue(getErros(clienteTeste).contains("Nome deve ter entre 70 e 2 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_nome_cliente_nulo() {
        clienteTeste.setNome(null);
        assertThat(getErros(clienteTeste), hasItem("Nome não pode ser nulo ou vazio."));
    }

    @Test
    public void deve_aceitar_endereco_cliente_valido() {
        Endereco enderecoTeste = new Endereco("Rua 1", "12", "casa 1", "02039020");
        clienteTeste.setEndereco(enderecoTeste);
        assertTrue(getErros(clienteTeste).isEmpty());
    }

    @Test
    public void nao_deve_aceitar_endereco_cliente_nulo() {
        clienteTeste.setEndereco(null);
        assertThat(getErros(clienteTeste), hasItem("Endereço não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_endereco_cliente() {
        Endereco enderecoTeste = new Endereco("Rua 1", "12", "casa 1", "02039020");
        clienteTeste.setEndereco(enderecoTeste);
        assertTrue(clienteTeste.getEndereco().equals(enderecoTeste));
    }

    @Test
    public void deve_aceitar_email_valido() {
        clienteTeste.setEmail("anitta.suellen@gmail.com");
        assertTrue(getErros(clienteTeste).isEmpty());
    }

    @Test
    public void nao_deve_aceitar_email_cliente_nulo() {
        clienteTeste.setEmail(null);
        assertThat(getErros(clienteTeste), hasItem("Email não pode ser nulo ou vazio."));
    }

    @Test
    public void nao_deve_aceitar_email_mais_de_uma_arroba() {
        clienteTeste.setEmail("@geovanemacuser@apple.com");
        assertThat(getErros(clienteTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_iniciando_arroba() {
        clienteTeste.setEmail("ge@ovanemacuser@apple.com");
        assertThat(getErros(clienteTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_iniciando_ponto() {
        clienteTeste.setEmail(".geovanemacuser@apple.com");
        assertThat(getErros(clienteTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_terminando_arroba() {
        clienteTeste.setEmail("geovanemacuser@apple.com@");
        assertThat(getErros(clienteTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_terminando_ponto() {
        clienteTeste.setEmail("geovanemacuser@apple.com.");
        assertThat(getErros(clienteTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void deve_respeitar_o_get_set_email_cliente() {
        clienteTeste.setEmail("geovanemacuser@apple.com");
        assertTrue("Get e Set Email deve funcionar.", clienteTeste.getEmail().equals("geovanemacuser@apple.com"));
    }

    @Test
    public void z04_equals_deve_ser_true_cpf_igual() {
        Cliente clienteTeste2 = new Cliente("49328402093");
        Cliente clienteTeste = new Cliente("49328402093");
        boolean a = clienteTeste.equals(clienteTeste2);
        assertTrue("Cpf igual representa Cliente igual", a);
    }

    @Test
    public void z05_equals_da_false_se_objetos_forem_de_classes_diferentes() {
        assertNotEquals("Equals dá false se objetos forem de classes diferentes", clienteTeste, new String());
    }

    @Test
    public void z06_equals_deve_ser_false_cpf_diferente() {
        Cliente clienteTeste2 = new Cliente("35819956893");
        assertNotEquals("Cpf diferente representa Cliente diferente", clienteTeste, clienteTeste2);
    }

    @Test
    public void testa_hashcode_igual() {
        Cliente clienteTeste2 = new Cliente("49328402093");
        Cliente clienteTeste = new Cliente("49328402093");
        assertTrue(clienteTeste.hashCode() == clienteTeste2.hashCode());
    }

    @Test
    public void testa_hashcode_diferente() {
        Cliente clienteTeste2 = new Cliente("35819956893");
        assertFalse(clienteTeste.hashCode() == clienteTeste2.hashCode());
    }

    @Test
    public void to_string_deve_conter_nome_cliente() {
        clienteTeste.setNome("Lero Lero");
        assertTrue("Confere se Nome Cliente está no toString", clienteTeste.toString().contains(clienteTeste.getNome()));
    }

    @Test
    public void to_string_deve_conter_cpf() {
        clienteTeste.setCpf("35819956893");
        assertTrue("Confere se Cpf está no toString", clienteTeste.toString().contains(clienteTeste.getCpf()));
    }

    @Test
    public void to_string_deve_conter_email() {
        clienteTeste.setEmail("geovanemacuser@apple.com");
        assertTrue("Confere se Email Cliente está no toString", clienteTeste.toString().contains(clienteTeste.getEmail()));
    }

}
