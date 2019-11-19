package br.com.contmatic.empresa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class PedidoTest {
    
    Pedido pedidoTeste = new Pedido("1");
    
    @Test
    public void deve_aceitar_pedido_valido() {
        assertNotNull(pedidoTeste);
    }
    
    @Test(expected = NullPointerException.class)
    public void nao_deve_aceitar_id_pedido_nulo() {
        pedidoTeste.setIdPedido(null);
    }
     
}
