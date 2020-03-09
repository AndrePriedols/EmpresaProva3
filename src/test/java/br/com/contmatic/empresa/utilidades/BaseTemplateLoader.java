package br.com.contmatic.empresa.utilidades;

import org.joda.time.DateTime;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Pedido;
import br.com.contmatic.empresa.Setor;
import br.com.contmatic.empresa.Telefone;
import br.com.contmatic.enums.EnumNomeSetor;
import br.com.contmatic.enums.EnumTipoEndereco;
import br.com.contmatic.enums.EnumTipoLogradouro;
import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.contmatic.enums.EnumUF;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class BaseTemplateLoader implements br.com.six2six.fixturefactory.loader.TemplateLoader {

	@Override
    public void load() {
			
		Fixture.of(Endereco.class).addTemplate("enderecoValido", new Rule() {
			{
				add("tipoEndereco", random(EnumTipoEndereco.COMERCIAL, EnumTipoEndereco.CORRESPONDENCIA));
				add("tipoLogradouro", random(EnumTipoLogradouro.ALAMEDA, EnumTipoLogradouro.CAMPO));
				add("logradouro", random("Marquês", "Antônio"));
				add("numero", random("23", "34"));
				add("complemento", random("casa 2", "apartamento 5"));
				add("bairro", random("Jardim Carlu", "Jardim Itália"));
				add("cidade", random("São Paulo", "Vinhedo"));
				add("UF", random(EnumUF.AC, EnumUF.BA));
				add("cep", random("02423030", "02039020"));
			}
		});
		
		Fixture.of(Telefone.class).addTemplate("telefoneValido", new Rule() {
			{
				add("tipoTelefone", random(EnumTipoTelefone.CELULAR, EnumTipoTelefone.RECADO));
				add("ddd", random("11", "45"));
				add("numeroTelefone", random("92345786", "154689758"));
			}
		});
		
		Endereco endereco = Fixture.from(Endereco.class).gimme("enderecoValido");
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefoneValido");

        Fixture.of(Cliente.class).addTemplate("clienteValido", new Rule() {
            {
                add("cpf", random("19963288081", "12519409002"));
                add("nome", random("Anitta", "Ludmila"));
                add("endereco", (endereco));
                add("endereco", (telefone));
                add("email", random("anitta@universalmusic.com", "ludmila@bmg.com"));
            }
        });
   
        Fixture.of(Empresa.class).addTemplate("empresaValida", new Rule() {
            {
                add("cnpj", random("76225927000191", "62199190000175"));
                add("razaoSocial", random("Serviços Ltda", "Assessoria Ltda."));
                add("dataAbertura", random(new DateTime()));
                add("capitalSocial", random(Double.class, range(1, 30000)));
                add("endereco", (endereco));
                add("endereco", (telefone));
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

        Fixture.of(Fornecedor.class).addTemplate("fornecedorValido", new Rule() {
            {
                add("cnpj", random("12345678901234", "12345678904321"));
                add("razaoSocial", random("Serviços Ltda", "Assessoria Ltda."));
                add("email", random("anitta@universalmusic.com", "ludmila@bmg.com"));
                add("endereco", (endereco));
                add("endereco", (telefone));
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
        
        Setor setor = Fixture.from(Setor.class).gimme("setorValido");
        
        Fixture.of(Funcionario.class).addTemplate("funcionarioValido", new Rule() {
            {
                add("cpf", random("35819956893", "66356679034"));
                add("nome", random("André", "José"));
                add("dataNascimento", random(new DateTime()));
                add("endereco", (endereco));
                add("endereco", (telefone));
                add("setor", random(setor));
                add("cargo", random("Estagiário", "Gerente"));
                add("salario", random(Double.class, range(100, 500)));
            }
        });
    }

}
