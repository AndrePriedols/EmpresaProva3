package br.com.contmatic.empresa;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class ClienteTest {

    @BeforeClass
    public static void chama_template_loader() {
        new TemplateLoader().load();
    }
    
    Cliente clienteTeste = Fixture.from(Cliente.class).gimme("clienteValido");

    @Test
    public void deve_aceitar_nome_cpf_valido() {
        assertNotNull(clienteTeste);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_nulo() {
        new Cliente(null);
    }

    @Test
    public void deve_aceitar_cpf_valido_digito_zero() {
        clienteTeste.setCpf("46776847070");
        assertNotNull("Cpf deve ser válido", clienteTeste.getCpf());
    }

    @Test
    public void deve_aceitar_cpf_valido_digito_zero_b() {
        clienteTeste.setCpf("82533451002");
        assertNotNull("Cpf deve ser válido", clienteTeste.getCpf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_invalido_digito_k() {
        clienteTeste.setCpf("65528642050");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_invalido_digito_j() {
        clienteTeste.setCpf("65528642041");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_maior_11_digitos() {
        clienteTeste.setCpf("1234567890123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_menor_11_digitos() {
        clienteTeste.setCpf("1234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_letra() {
        clienteTeste.setCpf("1234567890a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_caracter_especial() {
        clienteTeste.setCpf("1234567890@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_espaco() {
        clienteTeste.setCpf("123456 8901");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_todos_digitos_iguais() {
        clienteTeste.setCpf("11111111111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_onze_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11111111112");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_dez_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11111111121");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_nove_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11111111211");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_oito_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11111112111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_sete_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11111121111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_seis_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11111211111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_cinco_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11112111111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_quatro_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11121111111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_tres_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("11211111111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digito_dois_diferente_nao_anula_a_priori() {
        clienteTeste.setCpf("12111111111");
    }

    @Test
    public void deve_aceitar_nome_valido() {
        clienteTeste.setNome("André");
        if (StringUtils.isNotEmpty(clienteTeste.getNome()))
            assertNotNull("Nome deve ser válido", clienteTeste.getNome());
    }

    @Test
    public void nao_deve_aceitar_nome_cliente_nulo() {
        clienteTeste.setNome(null);
        assertNull(clienteTeste.getNome());
    }

    @Test
    public void deve_aceitar_endereco_cliente_valido() {
        assertNotNull("Endereço deve ser válido.", clienteTeste.getEndereco());
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_endereco_cliente_nulo() {
        clienteTeste.setEndereco(null);
    }

    @Test
    public void deve_respeitar_o_get_set_endereco_cliente() {
        Endereco enderecoTeste = new Endereco();
        clienteTeste.setEndereco(enderecoTeste);
        assertTrue(clienteTeste.getEndereco().equals(enderecoTeste));
    }

    @Test
    public void deve_aceitar_email_valido() {
        clienteTeste.setEmailCliente("ge_ovan-ema.cuser@ap--ple.com.br");
        assertNotNull(clienteTeste.getEmailCliente());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_cliente_nulo() {
        clienteTeste.setEmailCliente(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_mais_de_uma_arroba() {
        clienteTeste.setEmailCliente("@geovanemacuser@apple.com");
        assertNull(clienteTeste.getEmailCliente());
  }


    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_iniciando_arroba() {
        clienteTeste.setEmailCliente("ge@ovanemacuser@apple.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_iniciando_ponto() {
        clienteTeste.setEmailCliente(".geovanemacuser@apple.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_terminando_arroba() {
        clienteTeste.setEmailCliente("geovanemacuser@apple.com@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_terminando_ponto() {
        clienteTeste.setEmailCliente("geovanemacuser@apple.com.");
    }

    @Test
    public void deve_respeitar_o_get_set_email_cliente() {
        clienteTeste.setEmailCliente("geovanemacuser@apple.com");
        assertTrue("Get e Set Cnpj deve funcionar.", clienteTeste.getEmailCliente().equals("geovanemacuser@apple.com"));
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
        clienteTeste.setEmailCliente("geovanemacuser@apple.com");
        assertTrue("Confere se Email Cliente está no toString", clienteTeste.toString().contains(clienteTeste.getEmailCliente()));
    }

}
