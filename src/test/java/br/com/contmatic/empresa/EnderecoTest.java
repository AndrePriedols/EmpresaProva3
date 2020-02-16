package br.com.contmatic.empresa;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class EnderecoTest {

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
        assertNotNull("Construtor cria Endereço se valores são válidos", enderecoTeste);
    }

    @Test
    public void deve_aceitar_logradouro_valido() {
        assertNotNull("Aceitou Logradouro válido", enderecoTeste.getLogradouro());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_logradouro_nulo() {
        enderecoTeste.setLogradouro(null);
    }

    @Test
    public void deve_respeitar_o_get_set_logradouro() {
        String logradouro = "Rua 1";
        enderecoTeste.setLogradouro("Rua 1");
        assertEquals(enderecoTeste.getLogradouro(), logradouro);
    }

    @Test
    public void deve_aceitar_numero_valido() {
        assertNotNull("Deve aceitar Número válido", enderecoTeste.getNumero());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_numero_nulo() {
        enderecoTeste.setNumero(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_numero_com_letras() {
        enderecoTeste.setNumero("12a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_numero_com_caracteres_especiais() {
        enderecoTeste.setNumero("12@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_numero_com_espaco() {
        enderecoTeste.setNumero("12 3");
    }

    @Test
    public void deve_respeitar_o_get_set_numero() {
        String numero = "12";
        enderecoTeste.setNumero("12");
        assertEquals(enderecoTeste.getNumero(), numero);
    }

    @Test
    public void deve_aceitar_complemento_valido() {
        assertNotNull("Aceitou Logradouro válido", enderecoTeste.getComplemento());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_complemento_nulo() {
        enderecoTeste.setComplemento(null);
    }

    @Test
    public void deve_respeitar_o_get_set_complemento() {
        String complemento = "12";
        enderecoTeste.setComplemento("12");
        assertEquals(enderecoTeste.getComplemento(), complemento);
    }

    @Test
    public void deve_aceitar_bairro_valido() {
        enderecoTeste.setBairro("Jd Miriam");
        assertNotNull("Aceitou Logradouro válido", enderecoTeste.getBairro());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_bairro_nulo() {
        enderecoTeste.setBairro(null);
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
        assertNotNull("Aceitou Cep válido", enderecoTeste.getCep());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cep_nulo() {
        enderecoTeste.setCep(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cep_menor_8_digitos() {
        enderecoTeste.setCep("0203902");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cep_maior_8_digitos() {
        enderecoTeste.setCep("020390202");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cep_com_letras() {
        enderecoTeste.setCep("0203902a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cep_com_caracteres_especiais() {
        enderecoTeste.setCep("0203902@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cep_com_espaco() {
        enderecoTeste.setCep("0203 020");
    }

    @Test
    public void deve_respeitar_o_get_set_cep() {
        String cep = "02039020";
        enderecoTeste.setCep("02039020");
        assertEquals(enderecoTeste.getCep(), cep);
    }

    @Test
    public void equals_deve_ser_true_logradouro_numero_complemento_cep_iguais() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 1", "jd Miriam", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 1", "jd Miriam", "02039020");
        assertEquals("Endereços iguais para logradouros/números/complementos/ceps iguais", endereco1, endereco2);
    }

    @Test
    public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 1", "jd Miriam", "02039020");
        assertNotEquals("Equals dá false se forem objetos de classes diferentes", endereco1, new String());
    }

    @Test
    public void not_equals_se_houver_logradouro_diferente() {
        Endereco endereco1 = new Endereco("Rua 2", "12", "casa 2", "jd Miriam", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039020");
        assertNotEquals("Endereços diferentes, pois logradouros diferentes", endereco1, endereco2);
    }

    @Test
    public void not_equals_se_houver_numero_diferente() {
        Endereco endereco1 = new Endereco("Rua 2", "10", "casa 2", "jd Miriam", "02039020");
        Endereco endereco2 = new Endereco("Rua 2", "12", "casa 2", "jd Miriam", "02039020");
        assertNotEquals("Endereços diferentes, pois numeros diferentes", endereco1, endereco2);
    }

    @Test
    public void not_equals_se_houver_complemento_diferente() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 1", "jd Miriam", "02039020");
        assertNotEquals("Endereços diferentes, pois complementos diferentes", endereco1, endereco2);
    }

    @Test
    public void not_equals_se_houver_cep_diferente() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039020");
        assertNotEquals("Endereços diferentes, pois ceps diferentes", endereco1, endereco2);
    }

    @Test
    public void testa_hashcode_igual() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        assertTrue(endereco1.hashCode() == endereco2.hashCode());
    }

    @Test
    public void testa_hashcode_diferente() {
        Endereco endereco1 = new Endereco("Rua 2", "13", "casa 4", "jd Angela", "02039020");
        Endereco endereco2 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        assertFalse(endereco1.hashCode() == endereco2.hashCode());
    }

    @Test
    public void to_string_deve_conter_logradouro() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        assertTrue("Confere se Logradouro está no toString", endereco1.toString().contains(endereco1.getLogradouro()));
    }

    @Test
    public void to_string_deve_conter_numero() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        assertTrue("Confere se Número está no toString", endereco1.toString().contains(endereco1.getNumero()));
    }

    @Test
    public void to_string_deve_conter_complemento() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        ;
        assertTrue("Confere se Complemento está no toString", endereco1.toString().contains(endereco1.getComplemento()));
    }

    @Test
    public void to_string_deve_conter_bairro() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        assertTrue("Confere se Bairro está no toString", endereco1.toString().contains(endereco1.getBairro()));
    }

    @Test
    public void to_string_deve_conter_cep() {
        Endereco endereco1 = new Endereco("Rua 1", "12", "casa 2", "jd Miriam", "02039021");
        assertTrue("Confere se Cep está no toString", endereco1.toString().contains(endereco1.getCep()));
    }

    @Test
    public void atesta_que_logradouro_contem_ce_cedilha() {
        Endereco endereco1 = new Endereco("Rua dos Caiçaras", "12", "casa 2", "jd Miriam", "02039021");
        assertTrue("Logradouro contém ç", endereco1.getLogradouro().contains("ç"));
    }

    @Ignore
    @Test
    public void atesta_que_logradouro_nao_contem_ce_cedilha() {
        Endereco endereco1 = new Endereco("Rua dos Pescadores", "12", "casa 2", "jd Miriam", "02039021");
        assertFalse("Logradouro não contém ç", endereco1.getLogradouro().contains("ç"));
    }

    @AfterClass
    public static void mensagem_final_dos_testes() {
        System.out.println("Fim dos testes da classe Endereço.");
    }

}
