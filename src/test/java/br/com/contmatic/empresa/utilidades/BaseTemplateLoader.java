package br.com.contmatic.empresa.utilidades;

import org.joda.time.DateTime;

import br.com.contmatic.cliente.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EnumTipoEndereco;
import br.com.contmatic.endereco.EnumTipoLogradouro;
import br.com.contmatic.endereco.EnumUF;
import br.com.contmatic.enumEstadoCivil.EnumTipoEstadoCivil;
import br.com.contmatic.fornecedor.Fornecedor;
import br.com.contmatic.funcionario.Funcionario;
import br.com.contmatic.pedido.Pedido;
import br.com.contmatic.setor.EnumNomeSetor;
import br.com.contmatic.setor.Setor;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import telefone.EnumTipoTelefone;
import telefone.Telefone;

public class BaseTemplateLoader implements br.com.six2six.fixturefactory.loader.TemplateLoader {

	@Override
	public void load() {

		Fixture.of(Endereco.class).addTemplate("enderecoValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("tipoEndereco", random(EnumTipoEndereco.COMERCIAL, EnumTipoEndereco.CORRESPONDENCIA));
				add("tipoLogradouro", random(EnumTipoLogradouro.ALAMEDA, EnumTipoLogradouro.CAMPO));
				add("logradouro", random("Marquês", "Antônio"));
				add("numero", random("23", "34"));
				add("complemento", random("casa 2", "apartamento 5"));
				add("bairro", random("Jardim Carlu", "Jardim Itália"));
				add("cidade", random("São Paulo", "Vinhedo"));
				add("uf", random(EnumUF.AC, EnumUF.BA));
				add("cep", random("02423030", "02039020"));
			}
		});

		Fixture.of(Telefone.class).addTemplate("telefoneValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("tipoTelefone", random(EnumTipoTelefone.CELULAR, EnumTipoTelefone.RECADO));
				add("ddd", random("11", "45"));
				add("numeroTelefone", random("92345786", "154689758"));
			}
		});

		Endereco endereco = Fixture.from(Endereco.class).gimme("enderecoValido");
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefoneValido");

		Fixture.of(Cliente.class).addTemplate("clienteValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("cpf", random("19963288081", "12519409002"));
				add("nome", random("Anitta", "Ludmila"));
				add("endereco", (endereco));
				add("telefone", (telefone));
				add("email", random("anitta@universalmusic.com", "ludmila@bmg.com"));
			}
		});

		Fixture.of(Empresa.class).addTemplate("empresaValida", new Rule() {
			{
				add("id", random("1", "4"));
				add("cnpj", random("76225927000191", "62199190000175"));
				add("razaoSocial", random("Serviços Ltda", "Assessoria Ltda."));
				add("dataAbertura", random(new DateTime()));
				add("capitalSocial", random(Double.class, range(1, 30000)));
				add("endereco", (endereco));
				add("email", random("asc@lerolero.com.br", "olar@tiroliro.com.ch"));
				add("telefone", (telefone));
				add("website", random("http://www.lerolero.com.br", "http://www.tiroliro.com.ch"));
			}
		});

		Fixture.of(Pedido.class).addTemplate("pedidoValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("dataPedido",
						random(new DateTime(1970, 01, 01, 00, 00, 00), new DateTime(1990, 01, 01, 00, 00, 00)));
				add("dataEntrega",
						random(new DateTime(2070, 01, 01, 00, 00, 00), new DateTime(2050, 01, 01, 00, 00, 00)));
				add("valor", random(Double.class, range(1, 10000)));
			}
		});

		Fixture.of(Fornecedor.class).addTemplate("fornecedorValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("cnpj", random("37601087000162", "01083704000138"));
				add("razaoSocial", random("Serviços Ltda", "Assessoria Ltda."));
				add("email", random("anitta@universalmusic.com", "ludmila@bmg.com"));
				add("endereco", (endereco));
				add("telefone", (telefone));
				add("website", random("http://www.fornecedorlegal.com.br", "http://www.outrofornecedorlegal.com.ar"));
			}
		});

		Fixture.of(Setor.class).addTemplate("setorValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("nome", random(EnumNomeSetor.COMERCIAL, EnumNomeSetor.DESENVOLVIMENTO));
				add("ramal", random("12", "45"));
				add("responsavel", random("Antônia", "Gertrudes"));
			}
		});

		Setor setor = Fixture.from(Setor.class).gimme("setorValido");

		Fixture.of(Funcionario.class).addTemplate("funcionarioValido", new Rule() {
			{
				add("id", random("1", "4"));
				add("cpf", random("35819956893", "66356679034"));
				add("nome", random("André", "José"));
				add("dataNascimento", random(new DateTime()));
				add("dataContratacao", random(new DateTime()));
				add("estadoCivil", random(EnumTipoEstadoCivil.CASADO, EnumTipoEstadoCivil.SEPARADO));
				add("endereco", (endereco));
				add("telefone", (telefone));
				add("setor", random(setor));
				add("cargo", random("Estagiário", "Gerente"));
				add("email", random("anitta@universalmusic.com", "ludmila@bmg.com"));
				add("salario", random(Double.class, range(100, 500)));
			}
		});
	}

}
