package br.com.contmatic.empresa;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

public class FornecedorTest {

    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }

    Fornecedor fornecedorTeste = Fixture.from(Fornecedor.class).gimme("fornecedorValido");

    @Test
    public void deve_aceitar_fornecedor_valida() {
        assertNotNull(fornecedorTeste);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_nulo() {
        fornecedorTeste.setCnpj(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_menor_14_caracteres() {
        new Fornecedor("1234567890123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_maior_14_caracteres() {
        new Fornecedor("123456789012345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_letras() {
        new Fornecedor("1234567890123f");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_caracter_especial() {
        new Fornecedor("1234567890123@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_caracter_com_espaco() {
        new Fornecedor("123456789 1234");
    }

    @Test
    public void deve_respeitar_o_get_set_cnpj() {
        Fornecedor fornecedorTeste = new Fornecedor("12345678901234");
        assertTrue("Get e Set Cnpj deve funcionar.", fornecedorTeste.getCnpj().equals("12345678901234"));
    }

    @Test
    public void deve_aceitar_razao_social_valida() {
        fornecedorTeste.setRazaoSocial("Lero Lero");
        assertNotNull("Razão Social deve ser válida", fornecedorTeste.getRazaoSocial());
    }

    @Test(expected = IllegalArgumentException.class)
    public void k_nao_deve_aceitar_razao_social_nula() {
        fornecedorTeste.setRazaoSocial(null);
    }

    @Test
    public void deve_respeitar_o_get_set_razao_social() {
        fornecedorTeste.setRazaoSocial("Oliveiras");
        assertTrue("Get e Set Cnpj deve funcionar.", fornecedorTeste.getRazaoSocial().equals("Oliveiras"));
    }

    @Test
    public void deve_aceitar_email_fornecedor_valido() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com");
        assertNotNull(fornecedorTeste.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_fornecedor_nulo() {
        fornecedorTeste.setEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_fornecedor_iniciando_arroba() {
        fornecedorTeste.setEmail("@geovanemacuser@apple.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_fornecedor_iniciando_ponto() {
        fornecedorTeste.setEmail(".geovanemacuser@apple.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_fornecedor_terminando_arroba() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_fornecedor_terminando_ponto() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com.");
    }

    @Test
    public void deve_respeitar_o_get_set_email_fornecedor() {
        fornecedorTeste.setEmail("geovanemacuser@apple.com");
        assertTrue("Get e Set Cnpj deve funcionar.", fornecedorTeste.getEmail().equals("geovanemacuser@apple.com"));
    }

    @Test
    public void deve_aceitar_endereco_fornecedor_valido() {
        Endereco enderecoFornecedorTeste = new Endereco("Rua 1", "408", "Ap 2", "Jd Ângela", "02030040");
        fornecedorTeste.setEndereco(enderecoFornecedorTeste);
        assertNotNull("Endereço deve ser válido.", fornecedorTeste.getEndereco());
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_endereco_fornecedor_nulo() {
        fornecedorTeste.setEndereco(null);
    }

    @Test
    public void deve_respeitar_o_get_set_endereco_fornecedor() {
        Endereco enderecoTeste = new Endereco("Rua 1", "408", "Ap 2", "Jd Ângela", "02030040");
        Endereco enderecoTesteComparacao = new Endereco("Rua 1", "408", "Ap 2", "Jd Ângela", "02030040");
        fornecedorTeste.setEndereco(enderecoTeste);
        assertTrue("Get e Set Cnpj deve funcionar.", fornecedorTeste.getEndereco().equals(enderecoTesteComparacao));
    }

    @Test
    public void deve_aceitar_pedidos_fornecedor_valido() {
        Pedido pedido1 = new Pedido("1");
        Pedido pedido2 = new Pedido("2");
        Pedido[] pedidos = { pedido1, pedido2 };
        fornecedorTeste.setPedidos(pedidos);
        assertNotNull("Pedidos Fornecedor deve ser válido.", fornecedorTeste.getPedidos());
    }

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_pedidos_fornecedor_nulo() {
        fornecedorTeste.setPedidos(null);
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
