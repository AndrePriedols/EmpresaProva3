package br.com.contmatic.empresa.utilidades;

import org.joda.time.DateTime;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Pedido;
import br.com.contmatic.empresa.Setor;
import br.com.contmatic.enums.EnumNomeSetor;
import br.com.contmatic.enums.EnumTipoLogradouro;
import br.com.contmatic.enums.EnumUF;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class TemplateLoader implements br.com.six2six.fixturefactory.loader.TemplateLoader {

    public void load() {

        Fixture.of(Endereco.class).addTemplate("enderecoValido", new Rule() {
            {
                add("tipoLogradouro", random(EnumTipoLogradouro.AVENIDA, EnumTipoLogradouro.PRACA));
                add("logradouro", random("Rua Antonio", "Rua José"));
                add("numero", random("10", "39"));
                add("complemento", random("ap 23", "casa 2"));
                add("bairro", random("Jd. Angela", "Vila Mariana"));
                add("cidade", random("São Paulo", "Macapá"));
                add("UF", random(EnumUF.SP, EnumUF.AP));
                add("cep", random("02039020", "030492030"));
            }
        });

        Fixture.of(Cliente.class).addTemplate("clienteValido", new Rule() {
            {
                add("cpf", random("19963288081", "12519409002"));
                add("nome", random("Anitta", "Ludmila"));
                add("endereco", (Fixture.from(Endereco.class).gimme("enderecoValido")));
                add("emailCliente", random("anitta@universalmusic.com", "ludmila@bmg.com"));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("funcionarioValido", new Rule() {
            {
                add("cpf", random("35819956893", "66356679034"));
                add("nome", random("André", "José"));
                add("dataNascimento", random(new DateTime()));
                add("endereco", (Fixture.from(Endereco.class).gimme("enderecoValido")));
                add("setor", random(EnumNomeSetor.FINANCEIRO, EnumNomeSetor.RECURSOS_HUMANOS));
                add("cargo", random("Estagiário", "Gerente"));
                add("salario", random(Double.class, range(100, 500)));
            }
        });

        final Funcionario[] funcas = {};

        Fixture.of(Empresa.class).addTemplate("empresaValida", new Rule() {
            {
                add("cnpj", random("76225927000191", "62199190000175"));
                add("razaoSocial", random("Serviços Ltda", "Assessoria Ltda."));
                add("dataAbertura", random(new DateTime()));
                add("capitalSocial", random(Double.class, range(1, 30000)));
                add("endereco", (Fixture.from(Endereco.class).gimme("enderecoValido")));
                add("listaFuncionarios", (funcas));
            }
        });

        Fixture.of(Pedido.class).addTemplate("pedidoValido", new Rule() {
            {
                add("idPedido", random("1", "4"));
                add("dataPedido", random(new DateTime()));
                add("dataEntrega", random(new DateTime()));
                add("valor", random(Double.class, range(1, 10000)));
            }
        });

        final Pedido[] pedidos = {};

        Fixture.of(Fornecedor.class).addTemplate("fornecedorValido", new Rule() {
            {
                add("cnpj", random("12345678901234", "12345678904321"));
                add("razaoSocial", random("Serviços Ltda", "Assessoria Ltda."));
                add("email", random("anitta@universalmusic.com", "ludmila@bmg.com"));
                add("endereco", (Fixture.from(Endereco.class).gimme("enderecoValido")));
                add("pedidos", (pedidos));
            }
        });

        Fixture.of(Setor.class).addTemplate("setorValido", new Rule() {
            {
                add("nome", random(EnumNomeSetor.COMERCIAL, EnumNomeSetor.DESENVOLVIMENTO));
                add("ramal", random("12", "45"));
                add("responsavel", random("Antônia", "Gertrudes"));
                add("quantidadeFuncionarios", random(1, 30));
            }
        });
    }

}
