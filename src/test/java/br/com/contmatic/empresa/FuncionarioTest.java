package br.com.contmatic.empresa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.utilidades.BaseTemplateLoader;
import br.com.contmatic.enums.EnumNomeSetor;
import br.com.contmatic.enums.EnumTipoEstadoCivil;
import br.com.contmatic.enums.EnumTipoTelefone;
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

    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_cpf_nulo() {
        funcionarioTeste.setCpf(null);
    }

    @Test
    public void deve_aceitar_cpf_valido_digito_zero() {
        funcionarioTeste.setCpf("46776847070");
        assertFalse(getErros(funcionarioTeste).contains("CPF em formato inválido."));
    }

    @Test
    public void deve_aceitar_cpf_valido_digito_zero_b() {
        funcionarioTeste.setCpf("82533451002");
        assertFalse(getErros(funcionarioTeste).contains("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_invalido_digito_k() {
        funcionarioTeste.setCpf("65528642050");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_invalido_digito_j() {
        funcionarioTeste.setCpf("65528642041");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_maior_11_digitos() {
        funcionarioTeste.setCpf("1234567890123");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_menor_11_digitos() {
        funcionarioTeste.setCpf("1234567");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_letra() {
        funcionarioTeste.setCpf("1234567890a");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_caracter_especial() {
        funcionarioTeste.setCpf("1234567890@");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_cpf_espaco() {
        funcionarioTeste.setCpf("123456 8901");
        assertThat(getErros(funcionarioTeste), hasItem("CPF em formato inválido."));
    }

    @Test(expected = AssertionError.class)
    public void nao_deve_aceitar_cpf_com_todos_digitos_iguais() {
        funcionarioTeste.setCpf("11111111111");
    }

    @Test
    public void deve_aceitar_nome_conforme_regex() {
        funcionarioTeste.setNome("André");
        assertFalse(getErros(funcionarioTeste).contains("Nome com caracteres inválidos."));
    }

    @Test
    public void deve_aceitar_nome_extensao_correta() {
        funcionarioTeste.setNome("André");
        assertFalse(getErros(funcionarioTeste).contains("Nome deve ter entre 70 e 2 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_nome_extensao_incorreta() {
        funcionarioTeste.setNome("A");
        assertThat(getErros(funcionarioTeste), hasItem("Nome deve ter entre 70 e 2 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_nome_funcionario_nulo() {
        funcionarioTeste.setNome(null);
        assertThat(getErros(funcionarioTeste), hasItem("Nome não pode ser nulo."));
    }

    @Test
    public void m_deve_aceitar_data_nascimento_valida() {
        funcionarioTeste.setDataNascimento(new DateTime(1970, 01, 01, 00, 00, 00));
        assertFalse(getErros(funcionarioTeste).contains("Data Nascimento não pode ser nula."));
    }

    @Test(expected = NullPointerException.class)
    public void n_nao_deve_aceitar_data_nascimento_nula() {
        funcionarioTeste.setDataNascimento(null);
        assertThat(getErros(funcionarioTeste), hasItem("Data Nascimento não pode ser nula."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_nascimento_posterior_data_atual() {
        funcionarioTeste.setDataNascimento(new DateTime(2050, 01, 01, 00, 00, 00));
        assertTrue(funcionarioTeste.getDataNascimento().isAfterNow());
    }

    @Test
    public void deve_aceitar_estado_civil_valido() {
        funcionarioTeste.setEstadoCivil(EnumTipoEstadoCivil.CASADO);
        assertFalse(getErros(funcionarioTeste).contains("Estado Civil não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_estado_civil_nulo() {
        funcionarioTeste.setEstadoCivil(null);
        assertThat(getErros(funcionarioTeste), hasItem("Estado Civil não pode ser nulo."));
    }

    @Test
    public void deve_aceitar_telefone_funcionario_valido() {
        Telefone telefoneTeste = new Telefone();
        funcionarioTeste.setTelefone(telefoneTeste);
        assertFalse(getErros(funcionarioTeste).contains("Telefone não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_telefone_funcionario_nulo() {
        funcionarioTeste.setTelefone(null);
        assertThat(getErros(funcionarioTeste), hasItem("Telefone não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_telefone_funcionario() {
        Telefone telefoneTeste = new Telefone("1", EnumTipoTelefone.CELULAR, "11", "934508765");
        funcionarioTeste.setTelefone(telefoneTeste);
        assertTrue(funcionarioTeste.getTelefone().equals(telefoneTeste));
    }

    @Test
    public void deve_aceitar_endereco_funcionario_valido() {
        Endereco enderecoTeste = new Endereco("Rua 1", "12", "casa 1", "02039020");
        funcionarioTeste.setEndereco(enderecoTeste);
        assertFalse(getErros(funcionarioTeste).contains("Endereço não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_endereco_funcionario_nulo() {
        funcionarioTeste.setEndereco(null);
        assertThat(getErros(funcionarioTeste), hasItem("Endereço não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_endereco_funcionario() {
        Endereco enderecoTeste = new Endereco("Rua 1", "12", "casa 1", "02039020");
        funcionarioTeste.setEndereco(enderecoTeste);
        assertTrue(funcionarioTeste.getEndereco().equals(enderecoTeste));
    }

    @Test
    public void deve_aceitar_email_valido() {
        funcionarioTeste.setEmail("anitta.suellen@gmail.com");
        assertTrue(getErros(funcionarioTeste).isEmpty());
    }

    @Test
    public void nao_deve_aceitar_email_cliente_nulo() {
        funcionarioTeste.setEmail(null);
        assertThat(getErros(funcionarioTeste), hasItem("Email não pode ser nulo ou vazio."));
    }

    @Test
    public void nao_deve_aceitar_email_mais_de_uma_arroba() {
        funcionarioTeste.setEmail("@geovanemacuser@apple.com");
        assertThat(getErros(funcionarioTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_iniciando_arroba() {
        funcionarioTeste.setEmail("ge@ovanemacuser@apple.com");
        assertThat(getErros(funcionarioTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_iniciando_ponto() {
        funcionarioTeste.setEmail(".geovanemacuser@apple.com");
        assertThat(getErros(funcionarioTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_terminando_arroba() {
        funcionarioTeste.setEmail("geovanemacuser@apple.com@");
        assertThat(getErros(funcionarioTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void nao_deve_aceitar_email_terminando_ponto() {
        funcionarioTeste.setEmail("geovanemacuser@apple.com.");
        assertThat(getErros(funcionarioTeste), hasItem("Email em formato inválido."));
    }

    @Test
    public void deve_respeitar_o_get_set_email_cliente() {
        funcionarioTeste.setEmail("geovanemacuser@apple.com");
        assertTrue("Get e Set Email deve funcionar.", funcionarioTeste.getEmail().equals("geovanemacuser@apple.com"));
    }

    @Test
    public void m_deve_aceitar_data_contratacao_valida() {
        funcionarioTeste.setDataContratacao(new DateTime(1970, 01, 01, 00, 00, 00));
        assertFalse(getErros(funcionarioTeste).contains("Data Contratação não pode ser nula."));
    }

    @Test(expected = NullPointerException.class)
    public void n_nao_deve_aceitar_data_contratacao_nula() {
        funcionarioTeste.setDataContratacao(null);
        assertThat(getErros(funcionarioTeste), hasItem("Data Contratação não pode ser nula."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_contratacao_posterior_data_atual() {
        funcionarioTeste.setDataContratacao(new DateTime(2050, 01, 01, 00, 00, 00));
        assertTrue(funcionarioTeste.getDataContratacao().isAfterNow());
    }

    @Test
    public void deve_aceitar_setor_funcionario_valido() {
        funcionarioTeste.setSetor(new Setor());
        assertFalse(getErros(funcionarioTeste).contains("Setor não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_setor_funcionario_nulo() {
        funcionarioTeste.setSetor(null);
        assertThat(getErros(funcionarioTeste), hasItem("Setor não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_setor_funcionario() {
        Setor setorTeste = new Setor(EnumNomeSetor.DESENVOLVIMENTO);
        funcionarioTeste.setSetor(setorTeste);
        assertTrue(funcionarioTeste.getSetor().equals(setorTeste));
    }

    @Test
    public void deve_aceitar_cargo_funcionario_valido() {
        funcionarioTeste.setCargo("Estagiário");
        assertFalse(getErros(funcionarioTeste).contains("Cargo não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_cargo_funcionario_nulo() {
        funcionarioTeste.setCargo(null);
        assertThat(getErros(funcionarioTeste), hasItem("Cargo não pode ser nulo."));
    }

    @Test
    public void deve_respeitar_o_get_set_cargo_funcionario() {
        funcionarioTeste.setCargo("Estagiário");
        assertTrue(funcionarioTeste.getCargo().equals("Estagiário"));
    }

    @Test
    public void deve_aceitar_salario_valido_valor_positivo() {
        funcionarioTeste.setSalario(1);
        assertFalse(getErros(funcionarioTeste).contains("Salário deve ser igual ou maior a 1."));
    }

    @Test
    public void nao_deve_aceitar_salario_menor_que_1() {
        funcionarioTeste.setSalario(0.9);
        assertThat(getErros(funcionarioTeste), hasItem("Salário deve ser igual ou maior a 1."));
    }

    @Test
    public void deve_respeitar_o_get_set_salario() {
        double salario = 10;
        funcionarioTeste.setSalario(10);
        boolean a = funcionarioTeste.getSalario() == salario;
        assertTrue("Get e Set Setor deve funcionar.", a);
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
        funcionarioTeste = funca2;
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
        assertTrue("Confere se nome está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getNome()));
    }

    @Test
    public void to_string_deve_conter_cpf() {
        assertTrue("Confere se Cpf está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getCpf()));
    }

    @Test
    public void to_string_deve_conter_setor() {
        assertTrue("Confere se Setor está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getSetor().toString()));
    }

    @Test
    public void to_string_deve_conter_cargo() {
        assertTrue("Confere se Cargo está no toString", funcionarioTeste.toString().contains(funcionarioTeste.getCargo()));
    }

    @Test
    public void to_string_dando_erro_por_nao_conter_campos() {
        funcionarioTeste.setNome("André");
        funcionarioTeste.setCargo("Estagiário");
        String a = "Funcionário: José, 65528642051, Desenvolvimento, Estagiário";
        assertNotEquals(a, funcionarioTeste.toString());
    }

}
