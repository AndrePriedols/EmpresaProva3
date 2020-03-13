package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;

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
    
}
