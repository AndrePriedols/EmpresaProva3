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

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.contmatic.setor.EnumNomeSetor;
import br.com.contmatic.setor.Setor;
import br.com.six2six.fixturefactory.Fixture;

public class SetorTest {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public Set<String> getErros(Setor setor) {
        Set<String> erros = new HashSet<>();
        for(ConstraintViolation<Setor> constraintViolation : validator.validate(setor)) {
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

    @Test
    public void deve_aceitar_setor_valido() {
        assertTrue(getErros(setorTeste).isEmpty());
    }

    @Test
    public void deve_aceitar_id_valido() {
        assertNotNull(setorTeste.getId());
        assertFalse(getErros(setorTeste).contains("ID não pode ser menor que 1."));
    }

    @Test
    public void deve_aceitar_id_nulo() {
        setorTeste.setId(null);
        assertTrue(getErros(setorTeste).isEmpty());
    }

    @Test
    public void nao_deve_aceitar_id_menor_1() {
        setorTeste.setId(0);
        assertThat(getErros(setorTeste), hasItem("ID não pode ser menor que 1."));
    }

    @Test
    public void nao_deve_aceitar_nome_nulo() {
        setorTeste.setNome(null);
        assertThat(getErros(setorTeste), hasItem("Nome Setor não pode ser nulo."));
    }

    @Test
    public void deve_aceitar_nome_valido() {
        assertFalse(getErros(setorTeste).contains("Nome Setor não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_nome_setor() {
        setorTeste.setNome(EnumNomeSetor.COMERCIAL);
        assertTrue("Get e Set Nome Setor deve funcionar.", setorTeste.getNome().toString().equals("COMERCIAL"));
    }

    @Test
    public void deve_aceitar_ramal_valido() {
        assertNotNull(setorTeste.getRamal());
        assertFalse(getErros(setorTeste).contains("Ramal só pode conter números."));
    }

    @Test
    public void nao_deve_aceitar_ramal_nulo() {
        setorTeste.setRamal(null);
        assertThat(getErros(setorTeste), hasItem("Ramal não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_ramal_vazio() {
        setorTeste.setRamal("");
        assertThat(getErros(setorTeste), hasItem("Ramal não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_ramal_espacos() {
        setorTeste.setRamal("  ");
        assertThat(getErros(setorTeste), hasItem("Ramal não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_ramal_com_letras() throws ParseException {
        setorTeste.setRamal("2a");
        assertThat(getErros(setorTeste), hasItem("Ramal só pode conter números."));
    }

    @Test
    public void nao_deve_aceitar_ramal_com_espaco() throws ParseException {
        setorTeste.setRamal("2 a");
        assertThat(getErros(setorTeste), hasItem("Ramal só pode conter números."));
    }

    @Test
    public void nao_deve_aceitar_ramal_com_caracter_especial() throws ParseException {
        setorTeste.setRamal("2#");
        assertThat(getErros(setorTeste), hasItem("Ramal só pode conter números."));
    }

    @Test
    public void deve_respeitar_o_get_set_ramal() {
        setorTeste.setRamal("02");
        assertTrue("Get e Set Cnpj deve funcionar.", setorTeste.getRamal().equals("02"));
    }

    @Test
    public void deve_aceitar_responsavel_conforme_regex() {
        setorTeste.setResponsavel("André");
        assertFalse(getErros(setorTeste).contains("Responsável com caracteres inválidos."));
    }

    @Test
    public void deve_aceitar_responsavel_extensao_correta() {
        setorTeste.setResponsavel("André");
        assertFalse(getErros(setorTeste).contains("Responsável deve ter entre 70 e 2 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_responsavel_extensao_incorreta() {
        setorTeste.setResponsavel("A");
        assertThat(getErros(setorTeste), hasItem("Responsável deve ter entre 70 e 2 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_responsavel_nulo() {
        setorTeste.setResponsavel(null);
        assertThat(getErros(setorTeste), hasItem("Responsável não pode ser nulo ou vazio."));
    }

    @Test
    public void nao_deve_aceitar_responsavel_vazio() {
        setorTeste.setResponsavel("");
        assertThat(getErros(setorTeste), hasItem("Responsável não pode ser nulo ou vazio."));
    }

    @Test
    public void nao_deve_aceitar_responsavel_espacos() {
        setorTeste.setResponsavel("   ");
        assertThat(getErros(setorTeste), hasItem("Responsável não pode ser nulo ou vazio."));
    }

    @Test
    public void equals_da_false_se_objetos_forem_de_classes_diferentes() {
        assertNotEquals("Equals dá false se objetos forem de classes diferentes", setorTeste, new String());
    }

    @Test
    public void equals_deve_ser_false_nome_setor_diferente() {
        setorTeste.setNome(EnumNomeSetor.COMERCIAL);
        Setor setor2 = new Setor(EnumNomeSetor.COMPRAS);
        assertNotEquals("Cpf diferente representa Cliente diferente", setorTeste, setor2);
    }

    @Test
    public void testar_hashcode_diferente() {
        setorTeste.setNome(EnumNomeSetor.COMERCIAL);
        Setor setorTeste2 = new Setor(EnumNomeSetor.DESENVOLVIMENTO);
        assertFalse(setorTeste.hashCode() == setorTeste2.hashCode());
    }

    @Test
    public void to_string_deve_conter_nome_setor() {
        setorTeste.setNome(EnumNomeSetor.COMERCIAL);
        assertTrue("Confere se Nome Setor está no toString", setorTeste.toString().contains(setorTeste.getNome().toString()));
    }

    @Test
    public void to_string_deve_conter_ramal_setor() {
        setorTeste.setRamal("93");
        assertTrue("Confere se Ramal está no toString", setorTeste.toString().contains(setorTeste.getRamal()));
    }

    @Test
    public void to_string_deve_conter_responsavel_setor() {
        setorTeste.setResponsavel("Marcela");
        assertTrue("Confere se Responsável Setor está no toString", setorTeste.toString().contains(setorTeste.getResponsavel()));
    }

}
