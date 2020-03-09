package br.com.contmatic.empresa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.contmatic.enums.EnumNomeSetor;
import br.com.six2six.fixturefactory.Fixture;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FuncionarioTest {

	private Validator validator;

	Setor setorTeste = new Setor(EnumNomeSetor.COMPRAS);

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeClass
	public static void chama_template_loader() {
		new BaseTemplateLoader().load();
	}

	Funcionario funca1 = Fixture.from(Funcionario.class).gimme("funcionarioValido");
	
	@Test
    public void deve_rejeitar_cpf_nulo() {
        funca1.setCpf(null);
		Set<ConstraintViolation<Funcionario>> violations = validator.validate(funca1);
        assertFalse(violations.isEmpty());
    }

	@Test
	public void deve_aceitar_cpf_valido() throws Exception {
		assertNotNull("Cpf deve ser válido", funca1.getCpf());
	}

	@Test
	public void deve_aceitar_cpf_valido_digito_zero() {
		funca1.setCpf("46776847070");
		assertNotNull("Cpf deve ser válido", funca1.getCpf());
	}

	@Test
	public void deve_aceitar_cpf_valido_digito_zero_b() {
		funca1.setCpf("82533451002");
		assertNotNull("Cpf deve ser válido", funca1.getCpf());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido_digito_k() {
		funca1.setCpf("65528642050");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido_digito_j() {
		funca1.setCpf("65528642041");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_nulo() {
		funca1.setCpf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_maior_11_digitos() {
		funca1.setCpf("1234567890123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_menor_11_digitos() {
		funca1.setCpf("1234567890");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_letra() {
		funca1.setCpf("1234567890a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_caracter_especial() {
		funca1.setCpf("1234567890@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_espaco() {
		funca1.setCpf("123456 8901");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_todos_digitos_iguais() {
		funca1.setCpf("11111111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_onze_diferente_nao_anula_a_priori() {
		funca1.setCpf("11111111112");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_dez_diferente_nao_anula_a_priori() {
		funca1.setCpf("11111111121");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_nove_diferente_nao_anula_a_priori() {
		funca1.setCpf("11111111211");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_oito_diferente_nao_anula_a_priori() {
		funca1.setCpf("11111112111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_sete_diferente_nao_anula_a_priori() {
		funca1.setCpf("11111121111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_seis_diferente_nao_anula_a_priori() {
		funca1.setCpf("11111211111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_cinco_diferente_nao_anula_a_priori() {
		funca1.setCpf("11112111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_quatro_diferente_nao_anula_a_priori() {
		funca1.setCpf("11121111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_tres_diferente_nao_anula_a_priori() {
		funca1.setCpf("11211111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_dois_diferente_nao_anula_a_priori() {
		funca1.setCpf("12111111111");
	}

	@Test
	public void deve_aceitar_nome_valido_fixture() {
		Funcionario funcaTeste = Fixture.from(Funcionario.class).gimme("funcionarioValido");
		assertNotNull("Nome deve ser válido", funcaTeste.getNome());
	}

	@Test
	public void deve_aceitar_nome_valido() {
		funca1.setNome("André");
		assertNotNull("Nome deve ser válido", funca1.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_nulo() {
		funca1.setNome(null);
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_data_nascimento_nula() throws ParseException {
		funca1.setDataNascimento(null);
	}

	@Test
	public void deve_aceitar_data_nascimento_valida() throws ParseException {
		DateTime dateTime = new DateTime(1970, 01, 01, 00, 00, 00);
		funca1.setDataNascimento(dateTime);
		assertNotNull(funca1.getDataNascimento());
	}

	@Test
	public void deve_aceitar_estado_civil_valido() {
		assertNotNull(funca1.getEstadoCivil());
	}

	@Test
	public void deve_aceitar_endereco_valido() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "casa 2", "02030040");
		funca1.setEndereco(endereco1);
		assertNotNull("Endereço deve ser válido.", funca1.getEndereco());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_nascimento_posterior_data_atual() {
		DateTime dataNascimento = new DateTime(2050, 01, 01, 00, 00, 00);
		funca1.setDataNascimento(dataNascimento);
		assertTrue(funca1.getDataNascimento().isAfterNow());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_nulo() {
		funca1.setEndereco(null);
	}

	@Test
	public void deve_respeitar_o_get_set_endereco() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "casa 2", "02030040");
		funca1.setEndereco(endereco1);
		boolean a = funca1.getEndereco() == endereco1;
		assertTrue("Get e Set Endereço deve funcionar.", a);
	}

	@Test
	public void m_deve_aceitar_data_contratacao() throws ParseException {
		DateTime dataContratacao = new DateTime(2008, 01, 01, 00, 00, 00);
		funca1.setDataContratacao(dataContratacao);
		assertNotNull("Data Contratação deve ser válida", funca1.getDataContratacao());
	}

	@Test(expected = NullPointerException.class)
	public void n_nao_deve_aceitar_data_contratacao_nula() throws ParseException {
		funca1.setDataContratacao(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_contratacao_posterior_data_atual() {
		DateTime dataContratacao = new DateTime(2050, 01, 01, 00, 00, 00);
		funca1.setDataContratacao(dataContratacao);
		assertTrue(funca1.getDataContratacao().isAfterNow());
	}

	@Test
	public void deve_aceitar_setor_valido() {
		Setor setorTeste = new Setor(EnumNomeSetor.COMPRAS);
		funca1.setSetor(setorTeste);
		assertNotNull("Deve aceitar setor válido", funca1.getSetor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_setor_nulo() {
		funca1.setSetor(null);
	}

	@Test
	public void deve_respeitar_o_get_set_setor() {
		funca1.setSetor(setorTeste);
		boolean a = funca1.getSetor() == setorTeste;
		assertTrue("Get e Set Setor deve funcionar.", a);
	}

	@Test
	public void deve_aceitar_cargo_valido() {
		funca1.setCargo("Estagiário");
		assertNotNull("Deve aceitar cargo válido", funca1.getCargo());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cargo_nulo() {
		funca1.setCargo(null);
	}

	@Test
	public void deve_respeitar_o_get_set_cargo() {
		String cargo = "Estagiário";
		funca1.setCargo(cargo);
		boolean a = funca1.getCargo() == cargo;
		assertTrue("Get e Set Setor deve funcionar.", a);
	}

	@Test
	public void deve_aceitar_salario_valido() {
		funca1.setSalario(100);
		assertNotNull("Deve aceitar salario válido", funca1.getSalario());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_salario_zero() {
		funca1.setSalario(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_salario_negativo() {
		funca1.setSalario(-3);
	}

	@Test
	public void aceita_salario_positivo() {
		funca1.setSalario(3);
		assertTrue("Aceita salário positivo.", funca1.getSalario() == 3);
	}

	@Test
	public void deve_respeitar_o_get_set_salario() {
		double salario = 10;
		funca1.setSalario(10);
		boolean a = funca1.getSalario() == salario;
		assertTrue("Get e Set Setor deve funcionar.", a);
	}

	@Test
	public void equals_deve_ser_true_cpf_igual() {
		Funcionario funca2 = new Funcionario("65528642051");
		boolean a = funca1.equals(funca2);
		assertTrue("Cpf igual representa Funcionário igual", a);
	}

	@Test
	public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
		assertNotEquals("Equals dá false se objetos forem de classes diferentes", funca1, new String());
	}

	@Test
	public void equals_deve_ser_false_cnpj_diferente() {
		Funcionario funca2 = new Funcionario("02094485048");
		assertFalse("Cnpj diferente resulta em equals false", funca1.equals(funca2));
	}

	@Test
	public void testar_hashcode_igual() {
		Funcionario funca2 = new Funcionario("65528642051");
		assertTrue(funca1.equals(funca2));
		assertTrue(funca1.hashCode() == funca2.hashCode());
	}

	@Test
	public void testar_hashcode_diferente() {
		Funcionario funca2 = new Funcionario("35819956893");
		assertFalse(funca1.hashCode() == funca2.hashCode());
	}

	@Test
	public void to_string_deve_conter_nome() {
		funca1.setNome("José");
		funca1.setSetor(setorTeste);
		funca1.setCargo("Estagiário");
		assertTrue("Confere se nome está no toString", funca1.toString().contains(funca1.getNome()));
	}

	@Test
	public void to_string_deve_conter_cpf() {
		funca1.setNome("José");
		funca1.setSetor(setorTeste);
		funca1.setCargo("Estagiário");
		assertTrue("Confere se Cpf está no toString", funca1.toString().contains(funca1.getCpf()));
	}

	@Test
	public void to_string_deve_conter_setor() {
		funca1.setNome("José");
		funca1.setSetor(setorTeste);
		funca1.setCargo("Estagiário");
		assertTrue("Confere se Setor está no toString", funca1.toString().contains("Comercial"));
	}

	@Test
	public void to_string_deve_conter_cargo() {
		funca1.setNome("José");
		funca1.setSetor(setorTeste);
		funca1.setCargo("Estagiário");
		assertTrue("Confere se Cargo está no toString", funca1.toString().contains(funca1.getCargo()));
	}

	@Test
	public void to_string_dando_erro_por_nao_conter_nome_cpf_setor_cargo() {
		funca1.setNome("André");
		funca1.setSetor(setorTeste);
		funca1.setCargo("Estagiário");
		String a = "Funcionário: José, 65528642051, Desenvolvimento, Estagiário";
		assertNotEquals(a, funca1.toString());
	}

}
