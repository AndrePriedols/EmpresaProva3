package br.com.contmatic.pedido;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

public class Pedido {

    @Min(value = 1, message = "ID não pode ser menor que 1.")
    private Integer id;

    @NotNull(message = "Data Pedido não pode ser nula.")
    private DateTime dataPedido;

    @NotNull(message = "Data Entrega não pode ser nula.")
    @Future(message = "Data Entrega deve ser data futura.")
    private DateTime dataEntrega;

    @Min(value = 0, message = "Valor deve ser positivo ou zero.")
    private double valor;

    public Pedido() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDataPedido(DateTime dataPedido) {
        impedeDataPedidoPosteriorDataAtual(dataPedido);
        this.dataPedido = dataPedido;
    }

    private void impedeDataPedidoPosteriorDataAtual(DateTime dataPedido) {
        Preconditions.checkArgument(dataPedido.isBeforeNow(), "Data pedido não pode ser posteior à atual.");
    }

    public DateTime getDataPedido() {
        return this.dataPedido;
    }

    public void setDataEntrega(DateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public DateTime getDataEntrega() {
        return this.dataEntrega;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
