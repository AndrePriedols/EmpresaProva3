package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

public class PedidoTest {

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public Set<String> getErros(Pedido pedido) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Pedido> constraintViolation : validator.validate(pedido)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}

	@BeforeClass
	public static void chama_template_loader() {
		new BaseTemplateLoader().load();
	}

	Pedido pedidoTeste = Fixture.from(Pedido.class).gimme("pedidoValido");

	@Test
	public void deve_aceitar_pedido_valido() {
		assertNotNull(pedidoTeste);
	}

    
    @Test
    public void deve_aceitar_id_valido() {
        assertFalse(getErros(pedidoTeste).contains("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_invalido() {
        pedidoTeste.setId(null);
        assertThat(getErros(pedidoTeste), hasItem("ID não pode ser nulo."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_letras() {
        pedidoTeste.setId("a");
        assertThat(getErros(pedidoTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_caracter_especial() {
        pedidoTeste.setId("@");
        assertThat(getErros(pedidoTeste), hasItem("ID só pode conter números."));
    }
    
    @Test
    public void nao_deve_aceitar_id_com_espaco() {
        pedidoTeste.setId("1 1");
        assertThat(getErros(pedidoTeste), hasItem("ID só pode conter números."));
    }

}
