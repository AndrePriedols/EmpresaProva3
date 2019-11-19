package br.com.contmatic.empresa;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

public class Pedido {
    
    private static final double VALOR_PEDIDO_ZERO = 0;

    private String idPedido;
    
    private DateTime dataPedido;
    
    private DateTime dataEntrega;
    
    private double valor;
    
    public Pedido (String idPedido) {
        setIdPedido(idPedido);
    }
    
    public void setIdPedido(String idPedido) {
        impedeIdPedidoNulo(idPedido);
        permiteApenasCaracteresNumericosNoIdPedido(idPedido);
        this.idPedido = idPedido;
    }

    private void permiteApenasCaracteresNumericosNoIdPedido(String idPedido) {
        Preconditions.checkArgument(StringUtils.isNumeric(idPedido), "Apenas números no ID Pedido");
    }

    private void impedeIdPedidoNulo(String idPedido) {
        Preconditions.checkNotNull(idPedido, "ID do Pedido não pode ser nulo");
    }
    
    public String getIdPedido() {
        return this.idPedido;
    }
    
    public void setDataPedido(DateTime dataPedido) {
        impedeDataPedidoNula(dataPedido);
        impedeDataPedidoPosteriorDataAtual(dataPedido);
        this.dataPedido = dataPedido;
    }

    private void impedeDataPedidoPosteriorDataAtual(DateTime dataPedido) {
        Preconditions.checkArgument(dataPedido.isBeforeNow(), "Data pedido não pode ser posteior à atual.");
    }

    private void impedeDataPedidoNula(DateTime dataPedido) {
        Preconditions.checkNotNull(dataPedido, "Data pedido não pode ser nula");
    }
    
    public DateTime getDataPedido() {
        return this.dataPedido;
    }
    
    public void setDataEntrega(DateTime dataEntrega) {
        impedeDataEntregaNula(dataEntrega);
        impedeDataEntregaPosteriorDataAtual(dataEntrega);
        this.dataEntrega = dataEntrega;
    }

    private void impedeDataEntregaPosteriorDataAtual(DateTime dataEntrega) {
        Preconditions.checkArgument(dataEntrega.isBeforeNow(), "Data Entrega não pode ser posteior à atual.");
    }

    private void impedeDataEntregaNula(DateTime dataEntrega) {
        Preconditions.checkNotNull(dataEntrega, "Data Entrega não pode ser nula");
    }
    
    public DateTime getDataEntrega() {
        return this.dataEntrega;
    }
    
    public void setValor(double valor) {
        verificaValorPositivo(valor);
        this.valor = valor;
    }

    private void verificaValorPositivo(double valor) {
        if (valor<= VALOR_PEDIDO_ZERO) {
            throw new IllegalArgumentException("Valor deve ser maior que " + VALOR_PEDIDO_ZERO + ".");
        }
    }

    public double getValor() {
        return this.valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (idPedido == null) {
            if (other.idPedido != null)
                return false;
        } else if (!idPedido.equals(other.idPedido))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", valor=" + valor + "]";
    }  

 }
