package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.is;
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

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.contmatic.endereco.Endereco;
import br.com.six2six.fixturefactory.Fixture;
import telefone.EnumTipoTelefone;
import telefone.Telefone;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EmpresaTest {

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public Set<String> getErros(Empresa empresa) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Empresa> constraintViolation : validator.validate(empresa)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}

	@BeforeClass
	public static void chama_template_loader() {
		new BaseTemplateLoader().load();
	}

	Empresa empresaTeste = Fixture.from(Empresa.class).gimme("empresaValida");

	@Test
	public void a_deve_aceitar_empresa_valida() {
		assertTrue(getErros(empresaTeste).isEmpty());
	}

	@Test
	public void deve_aceitar_id_valido() {
		assertNotNull(empresaTeste.getId());
		assertFalse(getErros(empresaTeste).contains("ID não pode ser menor que 1."));
	}

	@Test
	public void deve_aceitar_id_nulo() {
		empresaTeste.setId(null);
		assertTrue(getErros(empresaTeste).isEmpty());
	}

	@Test
	public void nao_deve_aceitar_id_menor_1() {
		empresaTeste.setId(0);
		assertThat(getErros(empresaTeste), hasItem("ID não pode ser menor que 1."));
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		empresaTeste.setCnpj(null);
		assertThat(getErros(empresaTeste), hasItem("CNPJ não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cnpj_vazio() {
		empresaTeste.setCnpj("");
		assertThat(getErros(empresaTeste), hasItem("CNPJ não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cnpj_espacos() {
		empresaTeste.setCnpj("   ");
		assertThat(getErros(empresaTeste), hasItem("CNPJ não pode ser nulo."));
	}

	@Test
	public void c_nao_deve_aceitar_cnpj_menor_14_caracteres() {
		empresaTeste.setCnpj("1234567890123");
		assertThat(getErros(empresaTeste), hasItem("CNPJ em formato inválido."));
	}

	@Test
	public void d_nao_deve_aceitar_cnpj_maior_14_caracteres() {
		empresaTeste.setCnpj("123456789012345");
		assertThat(getErros(empresaTeste), hasItem("CNPJ em formato inválido."));
	}

	@Test
	public void f_nao_deve_aceitar_cnpj_com_letras() {
		empresaTeste.setCnpj("1234567890123f");
		assertThat(getErros(empresaTeste), hasItem("CNPJ em formato inválido."));
	}

	@Test
	public void g_nao_deve_aceitar_cnpj_com_caracter_especial() {
		empresaTeste.setCnpj("1234567890123@");
		assertThat(getErros(empresaTeste), hasItem("CNPJ em formato inválido."));
	}

	@Test
	public void h_nao_deve_aceitar_caracter_com_espaco() {
		empresaTeste.setCnpj("123456789 1234");
		assertThat(getErros(empresaTeste), hasItem("CNPJ em formato inválido."));
	}

	@Test
	public void deve_respeitar_o_get_set_cnpj() {
		Empresa empresaTeste = new Empresa("12345678901234");
		assertTrue("Get e Set Cnpj deve funcionar.", empresaTeste.getCnpj().equals("12345678901234"));
	}

	@Test
	public void j_deve_aceitar_razao_social_valida() {
		empresaTeste.setRazaoSocial("Lero Lero.SA");
		assertFalse(getErros(empresaTeste).contains("Razão Social com caracteres inválidos."));
	}

	@Test
	public void k_nao_deve_aceitar_razao_social_nula() {
		empresaTeste.setRazaoSocial(null);
		assertThat(getErros(empresaTeste), hasItem("Razão Social não pode ser nula."));
	}

	@Test
	public void k_nao_deve_aceitar_razao_social_espacos() {
		empresaTeste.setRazaoSocial("     ");
		assertThat(getErros(empresaTeste), hasItem("Razão Social não pode ser nula."));
	}

	@Test
	public void k_nao_deve_aceitar_razao_social_vazia() {
		empresaTeste.setRazaoSocial("");
		assertThat(getErros(empresaTeste), hasItem("Razão Social não pode ser nula."));
	}

	@Test
	public void deve_respeitar_o_get_set_razao_social() {
		empresaTeste.setRazaoSocial("Oliveiras");
        assertTrue("Get e Set Cnpj deve funcionar.", empresaTeste.getRazaoSocial().equals("Oliveiras"));
	}

	@Test
	public void m_deve_aceitar_data_abertura_valida() {
		DateTime dataAbertura = new DateTime(1970, 01, 01, 00, 00, 00);
		empresaTeste.setDataAbertura(dataAbertura);
		assertTrue(getErros(empresaTeste).isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void n_nao_deve_aceitar_data_abertura_nula() {
		Empresa empresaTesteData = new Empresa("02290861000187");
		empresaTesteData.setDataAbertura(null);
		assertThat(getErros(empresaTesteData), hasItem("Data Abertura não pode ser nula."));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_abertura_posterior_data_atual() {
		empresaTeste.setDataAbertura(new DateTime(2050, 01, 01, 00, 00, 00));
		assertTrue(empresaTeste.getDataAbertura().isAfterNow());
	}

	@Test
	public void u_deve_aceitar_capital_social_valido_() {
		empresaTeste.setCapitalSocial(1);
		assertTrue(!getErros(empresaTeste).contains("Capital Social deve ser maior ou igual a 1."));
	}

	@Test
	public void v_nao_deve_aceitar_capital_social_zero() {
		empresaTeste.setCapitalSocial(0);
		assertThat(getErros(empresaTeste), hasItem("Capital Social deve ser maior ou igual a 1."));
	}

	@Test
	public void w_nao_deve_aceitar_capital_social_negativo() {
		empresaTeste.setCapitalSocial(-1);
		assertThat(getErros(empresaTeste), hasItem("Capital Social deve ser maior ou igual a 1."));
	}

	@Test
	public void x_deve_respeitar_o_get_set_capital_social() {
		empresaTeste.setCapitalSocial(1000);
		assertTrue("Get e Set Cnpj deve funcionar.", empresaTeste.getCapitalSocial() == 1000);
	}

	@Test
	public void y_deve_aceitar_endereco_valido() {
		empresaTeste.setEndereco(new Endereco("Rua 1", "408", "Ap 2", "02030040"));
		assertFalse(getErros(empresaTeste).contains("Endereço não pode ser nulo."));
	}

	@Test
	public void z_nao_deve_aceitar_endereco_nulo() {
		empresaTeste.setEndereco(null);
		assertThat(getErros(empresaTeste), hasItem("Endereço não pode ser nulo."));
	}

	@Test(timeout = 100)
	public void z00_deve_respeitar_o_get_set_endereco() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "Ap 2", "02030040");
		empresaTeste.setEndereco(endereco1);
		boolean a = empresaTeste.getEndereco() == endereco1;
		assertTrue("Get e Set Endereço deve funcionar.", a);
	}

	@Test
	public void deve_aceitar_email_valido() {
		empresaTeste.setEmail("anitta.suellen@gmail.com");
		assertTrue(getErros(empresaTeste).isEmpty());
	}

	@Test
	public void nao_deve_aceitar_email_empresa_nulo() {
		empresaTeste.setEmail(null);
		assertThat(getErros(empresaTeste), hasItem("Email não pode ser nulo ou vazio."));
	}

	@Test
	public void nao_deve_aceitar_email_vazio() {
		empresaTeste.setEmail("");
		assertThat(getErros(empresaTeste), hasItem("Email não pode ser nulo ou vazio."));
	}

	@Test
	public void nao_deve_aceitar_email_espacos() {
		empresaTeste.setEmail("   ");
		assertThat(getErros(empresaTeste), hasItem("Email não pode ser nulo ou vazio."));
	}

	@Test
	public void nao_deve_aceitar_email_mais_de_uma_arroba() {
		empresaTeste.setEmail("@geovanemacuser@apple.com");
		assertThat(getErros(empresaTeste), hasItem("Email em formato inválido."));
	}

	@Test
	public void nao_deve_aceitar_email_iniciando_arroba() {
		empresaTeste.setEmail("ge@ovanemacuser@apple.com");
		assertThat(getErros(empresaTeste), hasItem("Email em formato inválido."));
	}

	@Test
	public void nao_deve_aceitar_email_iniciando_ponto() {
		empresaTeste.setEmail(".geovanemacuser@apple.com");
		assertThat(getErros(empresaTeste), hasItem("Email em formato inválido."));
	}

	@Test
	public void nao_deve_aceitar_email_terminando_arroba() {
		empresaTeste.setEmail("geovanemacuser@apple.com@");
		assertThat(getErros(empresaTeste), hasItem("Email em formato inválido."));
	}

	@Test
	public void nao_deve_aceitar_email_terminando_ponto() {
		empresaTeste.setEmail("geovanemacuser@apple.com.");
		assertThat(getErros(empresaTeste), hasItem("Email em formato inválido."));
	}

	@Test
	public void deve_aceitar_url_valida() {
		assertFalse(getErros(empresaTeste).contains("URL do Site inválida."));
	}

	@Test
	public void nao_deve_aceitar_url_invalida() {
		empresaTeste.setWebsite("www.site_errado.com");
		assertThat(getErros(empresaTeste), hasItem("URL do Site inválida."));
	}

	@Test
	public void deve_respeitar_o_get_set_email_empresa() {
		empresaTeste.setEmail("geovanemacuser@apple.com");
		assertTrue("Get e Set Email deve funcionar.", empresaTeste.getEmail().equals("geovanemacuser@apple.com"));
	}

	@Test
	public void deve_aceitar_telefone_empresa_valido() {
		Telefone telefoneTeste = new Telefone();
		empresaTeste.setTelefone(telefoneTeste);
		assertFalse(getErros(empresaTeste).contains("Telefone não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_telefone_empresae_nulo() {
		empresaTeste.setTelefone(null);
		assertThat(getErros(empresaTeste), hasItem("Telefone não pode ser nulo."));
	}

	@Test
	public void get_set_telefone_deve_funcionar() {
		Telefone telefoneTeste = new Telefone(EnumTipoTelefone.CELULAR, "11", "934508765");
		empresaTeste.setTelefone(new Telefone(EnumTipoTelefone.CELULAR, "11", "934508765"));
		assertTrue(empresaTeste.getTelefone().equals(telefoneTeste));
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
		assertTrue("Confere se Razão Social está no toString",
				empresaTeste.toString().contains(empresaTeste.getRazaoSocial()));
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

	@Test
	public void z11_get_set_website() {
		String website = "http://www.andrepriedols.com";
		empresaTeste.setWebsite(website);
		assertEquals(website, empresaTeste.getWebsite());
	}

	@Test
	public void testa_get_set_data_abertura() {
		DateTime dataAbertura = new DateTime("2010-04-02");
		empresaTeste.setDataAbertura(dataAbertura);
		assertEquals(dataAbertura, empresaTeste.getDataAbertura());
	}

}
