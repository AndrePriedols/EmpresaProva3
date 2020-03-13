package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Setor;
import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.contmatic.enums.EnumNomeSetor;
import br.com.six2six.fixturefactory.Fixture;

public class SetorTest {
	
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public Set<String> getErros(Setor setor) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Setor> constraintViolation : validator.validate(setor)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }   
    
    Setor setorTeste = Fixture.from(Setor.class).gimme("setorValido");

    EnumNomeSetor nomeSetorTeste = EnumNomeSetor.COMERCIAL;

	@Test
	public void deve_aceitar_nome_setor_valido() {
		assertNotNull(setorTeste);
	}
	
    
    @Test
    public void deve_aceitar_id_valido() {
        assertFalse(getErros(setorTeste).contains("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_invalido() {
        setorTeste.setId(null);
        assertThat(getErros(setorTeste), hasItem("ID não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_letras() {
        setorTeste.setId("a");
        assertThat(getErros(setorTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_caracter_especial() {
        setorTeste.setId("@");
        assertThat(getErros(setorTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_espaco() {
        setorTeste.setId("1 1");
        assertThat(getErros(setorTeste), hasItem("ID só pode conter números."));
    }


	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_setor_nulo() {
		new Setor(null);
	}

	@Test
	public void deve_respeitar_o_get_set_nome_setor() {
	    Setor setorTeste = new Setor(nomeSetorTeste);
	    assertTrue("Get e Set Nome Setor deve funcionar.", setorTeste.getNome().equals("Comercial"));
	}

	@Test
	public void deve_aceitar_ramal_valido() {
		setorTeste.setRamal("01");
		assertNotNull(setorTeste.getRamal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_ramal_nulo() {
		setorTeste.setRamal(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_ramal_com_letras() throws ParseException {
		setorTeste.setRamal("2a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_ramal_com_espaco() throws ParseException {
		setorTeste.setRamal("2 a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_ramal_com_caracter_especial() throws ParseException {
		setorTeste.setRamal("2#");
	}

	@Test
	public void deve_respeitar_o_get_set_ramal() {
		setorTeste.setRamal("02");
		assertTrue("Get e Set Cnpj deve funcionar.", setorTeste.getRamal().equals("02"));
	}

	@Test
	public void deve_aceitar_responsavel_valido() {
		setorTeste.setResponsavel("Antônio");
		assertNotNull(setorTeste.getResponsavel());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_responsavel_nulo() {
		setorTeste.setResponsavel(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_responsavel_com_numeros() throws ParseException {
		setorTeste.setResponsavel("4 André");
	}

	@Test
	public void deve_respeitar_o_get_set_responsavel() {
		setorTeste.setResponsavel("Jorge");
		assertTrue("Get e Set Cnpj deve funcionar.", setorTeste.getResponsavel().equals("Jorge"));
	}

	@Test
	public void equals_deve_ser_true_nome_setor_igual() {
		Setor setorTeste2 = new Setor(nomeSetorTeste);
		Setor setorTeste = new Setor(nomeSetorTeste);
		boolean a = setorTeste.equals(setorTeste2);
		assertTrue("Nome igual representa Setor igual", a);
	}

	@Test
	public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
		assertNotEquals("Equals dá false se objetos forem de classes diferentes", setorTeste, new String());
	}

	@Test
	public void equals_deve_ser_false_nome_setor_diferente() {
		Setor setor2 = new Setor(EnumNomeSetor.COMPRAS);
		assertNotEquals("Cpf diferente representa Cliente diferente", setorTeste, setor2);
	}

	@Test
	public void testar_hashcode_igual() {
		Setor setorTeste2 = new Setor(nomeSetorTeste);
		Setor setorTeste = new Setor(nomeSetorTeste);
		assertTrue(setorTeste.hashCode() == setorTeste2.hashCode());
	}

	@Test
	public void testar_hashcode_diferente() {
		Setor setorTeste2 = new Setor(EnumNomeSetor.DESENVOLVIMENTO);
		assertFalse(setorTeste.hashCode() == setorTeste2.hashCode());
	}

	@Test
	public void to_string_deve_conter_nome_setor() {
		setorTeste.setNome(nomeSetorTeste);
		assertTrue("Confere se Nome Setor está no toString", setorTeste.toString().contains(nomeSetorTeste.toString()));
	}

	@Test
	public void to_string_deve_conter_ramal_setor() {
		setorTeste.setRamal("93");
		assertTrue("Confere se Ramal está no toString", setorTeste.toString().contains(setorTeste.getRamal()));
	}

	@Test
	public void to_string_deve_conter_responsavel_setor() {
		setorTeste.setResponsavel("Marcela");
		assertTrue("Confere se Responsável Setor está no toString",
				setorTeste.toString().contains(setorTeste.getResponsavel()));
	}

}