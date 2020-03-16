package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.six2six.fixturefactory.Fixture;

public class TelefoneTest {
	
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public Set<String> getErros(Telefone telefone) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Telefone> constraintViolation : validator.validate(telefone)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
    @BeforeClass
    public static void chama_template_loader() {
        new BaseTemplateLoader().load();
    }   
    
    Telefone telefoneTeste = Fixture.from(Telefone.class).gimme("telefoneValido");
    
    @Test
    public void deve_aceitar_id_valido() {
        assertFalse(getErros(telefoneTeste).contains("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_invalido() {
        telefoneTeste.setId(null);
        assertThat(getErros(telefoneTeste), hasItem("ID não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_letras() {
        telefoneTeste.setId("a");
        assertThat(getErros(telefoneTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_caracter_especial() {
        telefoneTeste.setId("@");
        assertThat(getErros(telefoneTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_espaco() {
        telefoneTeste.setId("1 1");
        assertThat(getErros(telefoneTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
	public void nao_deve_aceitar_tipo_telefone_nulo() {
    	telefoneTeste.setTipoTelefone(null);
        assertThat(getErros(telefoneTeste), hasItem("Tipo telefone não pode ser nulo."));
	}
	
	@Test
	public void deve_aceitar_nome_valido() {
        assertFalse(getErros(telefoneTeste).contains("Tipo telefone não pode ser nulo."));
	}

	@Test
	public void deve_respeitar_o_get_set_nome_setor() {
		telefoneTeste.setTipoTelefone(EnumTipoTelefone.CELULAR);
		assertTrue("Get e Set Tipo Telefone deve funcionar.", telefoneTeste.getTipoTelefone().toString().equals("CELULAR"));
	}
	
	@Test
    public void deve_aceitar_ddd_quantidade_caracteres_correta() {
        assertFalse(getErros(telefoneTeste).contains("DDD no máximo com 3 dígitos."));
    }
    
    @Test
    public void nao_deve_aceitar_ddd_quantidade_caracteres_incorreta() {
        telefoneTeste.setDdd("9999");
        assertThat(getErros(telefoneTeste), hasItem("DDD no máximo com 3 dígitos."));
    }
    
    @Test
    public void deve_aceitar_ddd_apenas_caracteres_numericos() {
        assertFalse(getErros(telefoneTeste).contains("DDD aceita apenas números."));
    }
    
    @Test
    public void nao_deve_aceitar_ddd_nulo() {
        telefoneTeste.setDdd(null);
        assertThat(getErros(telefoneTeste), hasItem("DDD não pode ser deixado em branco."));
    }
    
    @Test
    public void nao_deve_aceitar_ddd_com_letras() {
        telefoneTeste.setDdd("9a9c99");
        assertThat(getErros(telefoneTeste), hasItem("DDD aceita apenas números."));
    }

    @Test
    public void nao_deve_aceitar_ddd_com_caracteres_especiais() {
        telefoneTeste.setDdd("12@");
        assertThat(getErros(telefoneTeste), hasItem("DDD aceita apenas números."));
    }

    @Test
    public void nao_deve_aceitar_ddd_com_espaco() {
        telefoneTeste.setDdd("12 3");
        assertThat(getErros(telefoneTeste), hasItem("DDD aceita apenas números."));
    }

    @Test
    public void deve_respeitar_o_get_set_ddd() {
        String ddd = "12";
        telefoneTeste.setDdd("12");
        assertEquals(telefoneTeste.getDdd(), ddd);
    }    
    
	@Test
    public void deve_aceitar_telefone_quantidade_caracteres_correta() {
        assertFalse(getErros(telefoneTeste).contains("Telefone entre 8 e 9 dígitos."));
    }
    
    @Test
    public void nao_deve_aceitar_telefone_quantidade_caracteres_incorreta() {
        telefoneTeste.setNumeroTelefone("9999");
        assertThat(getErros(telefoneTeste), hasItem("Telefone entre 8 e 9 dígitos."));
    }
    
    @Test
    public void deve_aceitar_telefone_apenas_caracteres_numericos() {
        assertFalse(getErros(telefoneTeste).contains("Número Telefone apenas números."));
    }
    
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        telefoneTeste.setNumeroTelefone(null);
        assertThat(getErros(telefoneTeste), hasItem("Telefone não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_telefone_com_letras() {
        telefoneTeste.setNumeroTelefone("9a9c99");
        assertThat(getErros(telefoneTeste), hasItem("Número Telefone apenas números."));
    }

    @Test
    public void nao_deve_aceitar_telefone_com_caracteres_especiais() {
        telefoneTeste.setNumeroTelefone("12@");
        assertThat(getErros(telefoneTeste), hasItem("Número Telefone apenas números."));
    }

    @Test
    public void nao_deve_aceitar_telefone_com_espaco() {
        telefoneTeste.setNumeroTelefone("12 3");
        assertThat(getErros(telefoneTeste), hasItem("Número Telefone apenas números."));
    }

    @Test
    public void deve_respeitar_o_get_set_telefone() {
        String telefone = "98765432";
        telefoneTeste.setNumeroTelefone("98765432");
        assertEquals(telefoneTeste.getNumeroTelefone(), telefone);
    }
    
}
