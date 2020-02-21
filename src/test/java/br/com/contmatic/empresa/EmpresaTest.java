package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EmpresaTest {

    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }
    
    Empresa empresaTeste = Fixture.from(Empresa.class).gimme("empresaValida");

	@Test
	public void a_deve_aceitar_empresa_valida() {
		assertNotNull(empresaTeste);
	}

	@Test(expected = IllegalArgumentException.class)
	public void b_nao_deve_aceitar_cnpj_nulo() {
		new Empresa(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void c_nao_deve_aceitar_cnpj_menor_14_caracteres() {
		new Empresa("1234567890123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void d_nao_deve_aceitar_cnpj_maior_14_caracteres() {
		new Empresa("123456789012345");
	}

	@Test(expected = IllegalArgumentException.class)
	public void f_nao_deve_aceitar_cnpj_com_letras() {
		new Empresa("1234567890123f");
	}

	@Test(expected = IllegalArgumentException.class)
	public void g_nao_deve_aceitar_cnpj_com_caracter_especial() {
		new Empresa("1234567890123@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void h_nao_deve_aceitar_caracter_com_espaco() {
		new Empresa("123456789 1234");
	}

	@Test
	public void deve_respeitar_o_get_set_cnpj() {
	    Empresa empresaTeste = new Empresa("12345678901234");
		assertTrue("Get e Set Cnpj deve funcionar.", empresaTeste.getCnpj().equals("12345678901234"));
	}

	@Test
	public void j_deve_aceitar_razao_social_valida() {
		empresaTeste.setRazaoSocial("Lero Lero");
		assertNotNull("Razão Social deve ser válida", empresaTeste.getRazaoSocial());
	}

	@Test(expected = IllegalArgumentException.class)
	public void k_nao_deve_aceitar_razao_social_nula() {
		empresaTeste.setRazaoSocial(null);
	}

	@Test
	public void deve_respeitar_o_get_set_razao_social() {
		empresaTeste.setRazaoSocial("Oliveiras");
		assertTrue("Get e Set Cnpj deve funcionar.", empresaTeste.getRazaoSocial().equals("Oliveiras"));
	}

	@Test
	public void m_deve_aceitar_data_abertura_valida() throws ParseException {
		DateTime dataAbertura = new DateTime(1970, 01, 01, 00, 00, 00);
	    empresaTeste.setDataAbertura(dataAbertura);
		assertNotNull("Data abertura deve ser válida", empresaTeste.getDataAbertura());
	}

	@Test(expected = NullPointerException.class)
	public void n_nao_deve_aceitar_data_abertura_nula() throws ParseException {
		empresaTeste.setDataAbertura(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_posterior_data_atual() {
	    DateTime dataAbertura = new DateTime(2050, 01, 01, 00, 00, 00);
        empresaTeste.setDataAbertura(dataAbertura);
        assertTrue(empresaTeste.getDataAbertura().isAfterNow());
    }
	
	@Test
	public void u_deve_aceitar_capital_social_valido_() {
		empresaTeste.setCapitalSocial(1);
		assertNotNull("Capital Social deve ser válido", empresaTeste.getCapitalSocial());
	}

	@Test(expected = IllegalArgumentException.class)
	public void v_nao_deve_aceitar_capital_social_zero() {
		empresaTeste.setCapitalSocial(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void w_nao_deve_aceitar_capital_social_negativo() {
		empresaTeste.setCapitalSocial(-1);
	}

	@Test
	public void x_deve_respeitar_o_get_set_capital_social() {
		empresaTeste.setCapitalSocial(1000);
		assertTrue("Get e Set Cnpj deve funcionar.", empresaTeste.getCapitalSocial() == 1000);
	}

	@Test
	public void y_deve_aceitar_endereco_valido() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "Ap 2", "02030040");
		empresaTeste.setEndereco(endereco1);
		assertNotNull("Endereço deve ser válido.", empresaTeste.getEndereco());
	}

	@Test(expected = NullPointerException.class)
	public void z_nao_deve_aceitar_endereco_nulo() {
		empresaTeste.setEndereco(null);
	}

	@Test(timeout = 100)
	public void z00_deve_respeitar_o_get_set_endereco() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "Ap 2", "02030040");
		empresaTeste.setEndereco(endereco1);
		boolean a = empresaTeste.getEndereco() == endereco1;
		assertTrue("Get e Set Endereço deve funcionar.", a);
	}

	@Test
	public void z04_equals_deve_ser_true_cnpj_igual() {
		Empresa empresaTeste2 = new Empresa("12345678901234");
		Empresa empresaTeste = new Empresa("12345678901234");
		boolean a = empresaTeste.equals(empresaTeste2);
		assertTrue("Cnpj igual representa Empresa igual", a);
	}

	@Test
	public void z05_equals_da_false_se_objetos_forem_de_classes_diferentes() {
		assertNotEquals("Equals dá false se objetos forem de classes diferentes", empresaTeste, new String());
	}

	@Test
	public void z06_equals_deve_ser_false_cnpj_diferente() {
		Empresa empresa2 = new Empresa("12345678901232");
		assertNotEquals("Cnpj diferente representa Empresa diferente", empresaTeste, empresa2);
	}

	@Test
	public void z07_testar_hashcode_igual() {
		Empresa empresaTeste2 = new Empresa("12345678901234");
		Empresa empresaTeste = new Empresa("12345678901234");
		assertTrue(empresaTeste.hashCode() == empresaTeste2.hashCode());
	}

	@Test
	public void z07_testar_hashcode_diferente() {
		Empresa empresa2 = new Empresa("12345678901235");
		assertFalse(empresaTeste.hashCode() == empresa2.hashCode());
	}

	@Test
	public void z08_to_string_deve_conter_razao_social() {
		empresaTeste.setRazaoSocial("Lero Lero");
		assertTrue("Confere se Razão Social está no toString", empresaTeste.toString().contains(empresaTeste.getRazaoSocial()));
	}

	@Test
	public void z09_to_string_deve_conter_cnpj() {
		empresaTeste.setRazaoSocial("Lero Lero");
		assertTrue("Confere se Cnpj está no toString", empresaTeste.toString().contains(empresaTeste.getCnpj()));
	}

	@Test
	public void z10_atesta_que_cnpj_do_construtor_e_recebido() {
	    Empresa empresaTeste = new Empresa("12345678901234");
		assertThat(empresaTeste.getCnpj(), is("12345678901234"));
	}

}