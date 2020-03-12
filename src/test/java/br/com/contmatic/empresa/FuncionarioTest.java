package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.contmatic.enums.EnumNomeSetor;
import br.com.six2six.fixturefactory.Fixture;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FuncionarioTest {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public Set<String> getErros(Funcionario funcionario) {
        Set<String> erros = new HashSet<>();
        for(ConstraintViolation<Funcionario> constraintViolation : validator.validate(funcionario)) {
            erros.add(constraintViolation.getMessageTemplate());
            System.out.println(constraintViolation.getMessageTemplate());
        }
        return erros;
    }

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

	Funcionario funcionarioTeste = Fixture.from(Funcionario.class).gimme("funcionarioValido");
	
	   @Test
	    public void deve_aceitar_id_valido() {
	        assertFalse(getErros(funcionarioTeste).contains("ID só pode conter números."));
	    }
	    
	    @Test
	    public void nao_deve_aceitar_id_invalido() {
	        funcionarioTeste.setId(null);
	        assertThat(getErros(funcionarioTeste), hasItem("ID não pode ser nulo."));
	    }
	    
	    @Test
	    public void nao_deve_aceitar_id_com_letras() {
	        funcionarioTeste.setId("a");
	        assertThat(getErros(funcionarioTeste), hasItem("ID só pode conter números."));
	    }
	    
	    @Test
	    public void nao_deve_aceitar_id_com_caracter_especial() {
	        funcionarioTeste.setId("@");
	        assertThat(getErros(funcionarioTeste), hasItem("ID só pode conter números."));
	    }
	    
	    @Test
	    public void nao_deve_aceitar_id_com_espaco() {
	        funcionarioTeste.setId("1 1");
	        assertThat(getErros(funcionarioTeste), hasItem("ID só pode conter números."));
	    }
	
	@Test
    public void deve_rejeitar_cpf_nulo() {
        funcionarioTeste.setCpf(null);
		Set<ConstraintViolation<Funcionario>> violations = validator.validate(funcionarioTeste);
        assertFalse(violations.isEmpty());
    }

	@Test
	public void deve_aceitar_cpf_valido() throws Exception {
		assertNotNull("Cpf deve ser válido", funcionarioTeste.getCpf());
	}

	@Test
	public void deve_aceitar_cpf_valido_digito_zero() {
		funcionarioTeste.setCpf("46776847070");
		assertNotNull("Cpf deve ser válido", funcionarioTeste.getCpf());
	}

	@Test
	public void deve_aceitar_cpf_valido_digito_zero_b() {
		funcionarioTeste.setCpf("82533451002");
		assertNotNull("Cpf deve ser válido", funcionarioTeste.getCpf());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido_digito_k() {
		funcionarioTeste.setCpf("65528642050");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido_digito_j() {
		funcionarioTeste.setCpf("65528642041");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_nulo() {
		funcionarioTeste.setCpf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_maior_11_digitos() {
		funcionarioTeste.setCpf("1234567890123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_menor_11_digitos() {
		funcionarioTeste.setCpf("1234567890");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_letra() {
		funcionarioTeste.setCpf("1234567890a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_caracter_especial() {
		funcionarioTeste.setCpf("1234567890@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_espaco() {
		funcionarioTeste.setCpf("123456 8901");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_com_todos_digitos_iguais() {
		funcionarioTeste.setCpf("11111111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_onze_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11111111112");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_dez_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11111111121");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_nove_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11111111211");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_oito_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11111112111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_sete_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11111121111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_seis_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11111211111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_cinco_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11112111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_quatro_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11121111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_tres_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("11211111111");
	}

	@Test(expected = IllegalArgumentException.class)
	public void digito_dois_diferente_nao_anula_a_priori() {
		funcionarioTeste.setCpf("12111111111");
	}

	@Test
	public void deve_aceitar_nome_valido_fixture() {
		Funcionario funcaTeste = Fixture.from(Funcionario.class).gimme("funcionarioValido");
		assertNotNull("Nome deve ser válido", funcaTeste.getNome());
	}

	@Test
	public void deve_aceitar_nome_valido() {
		funcionarioTeste.setNome("André");
		assertNotNull("Nome deve ser válido", funcionarioTeste.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_nulo() {
		funcionarioTeste.setNome(null);
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_data_nascimento_nula() throws ParseException {
		funcionarioTeste.setDataNascimento(null);
	}

	@Test
	public void deve_aceitar_data_nascimento_valida() throws ParseException {
		DateTime dateTime = new DateTime(1970, 01, 01, 00, 00, 00);
		funcionarioTeste.setDataNascimento(dateTime);
		assertNotNull(funcionarioTeste.getDataNascimento());
	}

	@Test
	public void deve_aceitar_estado_civil_valido() {
		assertNotNull(funcionarioTeste.getEstadoCivil());
	}

	@Test
	public void deve_aceitar_endereco_valido() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "casa 2", "02030040");
		funcionarioTeste.setEndereco(endereco1);
		assertNotNull("Endereço deve ser válido.", funcionarioTeste.getEndereco());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_nascimento_posterior_data_atual() {
		DateTime dataNascimento = new DateTime(2050, 01, 01, 00, 00, 00);
		funcionarioTeste.setDataNascimento(dataNascimento);
		assertTrue(funcionarioTeste.getDataNascimento().isAfterNow());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_nulo() {
		funcionarioTeste.setEndereco(null);
	}

	@Test
	public void deve_respeitar_o_get_set_endereco() {
		Endereco endereco1 = new Endereco("Rua 1", "408", "casa 2", "02030040");
		funcionarioTeste.setEndereco(endereco1);
		boolean a = funcionarioTeste.getEndereco() == endereco1;
		assertTrue("Get e Set Endereço deve funcionar.", a);
	}

	@Test
	public void m_deve_aceitar_data_contratacao() throws ParseException {
		DateTime dataContratacao = new DateTime(2008, 01, 01, 00, 00, 00);
		funcionarioTeste.setDataContratacao(dataContratacao);
		assertNotNull("Data Contratação deve ser válida", funcionarioTeste.getDataContratacao());
	}

	@Test(expected = NullPointerException.class)
	public void n_nao_deve_aceitar_data_contratacao_nula() throws ParseException {
		funcionarioTeste.setDataContratacao(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_contratacao_posterior_data_atual() {
		DateTime dataContratacao = new DateTime(2050, 01, 01, 00, 00, 00);
		funcionarioTeste.setDataContratacao(dataContratacao);
		assertTrue(funcionarioTeste.getDataContratacao().isAfterNow());
	}

	@Test
	public void deve_aceitar_setor_valido() {
		Setor setorTeste = new Setor(EnumNomeSetor.COMPRAS);
		funcionarioTeste.setSetor(setorTeste);
		assertNotNull("Deve aceitar setor válido", funcionarioTeste.getSetor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_setor_nulo() {
		funcionarioTeste.setSetor(null);
	}

	@Test
	public void deve_respeitar_o_get_set_setor() {
		funcionarioTeste.setSetor(setorTeste);
		boolean a = funcionarioTeste.getSetor() == setorTeste;
		assertTrue("Get e Set Setor deve funcionar.", a);
	}

	@Test
	public void deve_aceitar_cargo_valido() {
		funcionarioTeste.setCargo("Estagiário");
		assertNotNull("Deve aceitar cargo válido", funcionarioTeste.getCargo());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cargo_nulo() {
		funcionarioTeste.setCargo(null);
	}

	@Test
	public void deve_respeitar_o_get_set_cargo() {
		String cargo = "Estagiário";
		funcionarioTeste.setCargo(cargo);
		boolean a = funcionarioTeste.getCargo() == cargo;
		assertTrue("Get e Set Setor deve funcionar.", a);
	}

	@Test
	public void deve_aceitar_salario_valido() {
		funcionarioTeste.setSalario(100);
		assertNotNull("Deve aceitar salario válido", funcionarioTeste.getSalario());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_salario_zero() {
		funcionarioTeste.setSalario(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_salario_negativo() {
		funcionarioTeste.setSalario(-3);
	}

	@Test
	public void aceita_salario_positivo() {
		funcionarioTeste.setSalario(3);
		assertTrue("Aceita salário positivo.", funcionarioTeste.getSalario() == 3);
	}

	@Test
	public void deve_respeitar_o_get_set_salario() {
		double salario = 10;
		funcionarioTeste.setSalario(10);
		boolean a = funcionarioTeste.getSalario() == salario;
		assertTrue("Get e Set Setor deve funcionar.", a);
	}

	@Test
	public void equals_deve_ser_true_cpf_igual() {
		Funcionario funca2 = new Funcionario("65528642051");
		boolean a = funcionarioTeste.equals(funca2);
		assertTrue("Cpf igual representa Funcionário igual", a);
	}

	@Test
	public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
		assertNotEquals("Equals dá false se objetos forem de classes diferentes", funcionarioTeste, new String());
	}

	@Test
	public void equals_deve_ser_false_cnpj_diferente() {
		Funcionario funca2 = new Funcionario("02094485048");
		assertFalse("Cnpj diferente resulta em equals false", funcionarioTeste.equals(funca2));
	}

	@Test
	public void testar_hashcode_igual() {
		Funcionario funca2 = new Funcionario("65528642051");
		assertTrue(funcionarioTeste.equals(funca2));
		assertTrue(funcionarioTeste.hashCode() == funca2.hashCode());
	}

	@Test
	public void testar_hashcode_diferente() {
		Funcionario funca2 = new Funcionario("35819956893");
		assertFalse(funcionarioTeste.hashCode() == funca2.hashCode());
	}

	@Test
	public void to_string_deve_conter_nome() {
		funcionarioTeste.setNome("José");
		funcionarioTeste.setSetor(setorTeste);
		funcionarioTeste.setCargo("Estagiário");
		assertTrue("Confere se nome está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getNome()));
	}

	@Test
	public void to_string_deve_conter_cpf() {
		funcionarioTeste.setNome("José");
		funcionarioTeste.setSetor(setorTeste);
		funcionarioTeste.setCargo("Estagiário");
		assertTrue("Confere se Cpf está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getCpf()));
	}

	@Test
	public void to_string_deve_conter_setor() {
		funcionarioTeste.setNome("José");
		funcionarioTeste.setSetor(setorTeste);
		funcionarioTeste.setCargo("Estagiário");
		assertTrue("Confere se Setor está no toString", funcionarioTeste.toString().contains("Comercial"));
	}

	@Test
	public void to_string_deve_conter_cargo() {
		funcionarioTeste.setNome("José");
		funcionarioTeste.setSetor(setorTeste);
		funcionarioTeste.setCargo("Estagiário");
		assertTrue("Confere se Cargo está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getCargo()));
	}

	@Test
	public void to_string_dando_erro_por_nao_conter_nome_cpf_setor_cargo() {
		funcionarioTeste.setNome("André");
		funcionarioTeste.setSetor(setorTeste);
		funcionarioTeste.setCargo("Estagiário");
		String a = "Funcionário: José, 65528642051, Desenvolvimento, Estagiário";
		assertNotEquals(a, funcionarioTeste.toString());
	}

}
