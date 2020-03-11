package br.com.contmatic.empresa;

import javax.annotation.Nonnegative;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

import br.com.contmatic.utils.Regex;

public class Pedido {

    private static final double VALOR_PEDIDO_ZERO = 0;

    @NotNull(message = "ID Pedido não pode ser nulo.")
    @javax.validation.constraints.Pattern(regexp=Regex.REGEX_ID)
    private String id;

    @NotNull(message = "Data Pedido não pode ser nula.")
    @Size(max = 8, min = 8)
    @javax.validation.constraints.Pattern(regexp="[0-9]", message="Data aceita apenas números.")
    private DateTime dataPedido;

    @Future(message = "Data Previsão deve ser data futura.")
    @Size(max = 8, min = 8)
    @javax.validation.constraints.Pattern(regexp="[0-9]", message="Data aceita apenas números.")
    private DateTime previsaoEntrega;

    @NotNull(message = "Data Entrega não pode ser nula.")
    @Size(max = 8, min = 8)
    @javax.validation.constraints.Pattern(regexp="[0-9]", message="Data aceita apenas números.")
    private DateTime dataEntrega;

    @Nonnegative
    private double valor;

    public Pedido() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
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
        if (valor <= VALOR_PEDIDO_ZERO) {
            throw new IllegalArgumentException("Valor deve ser maior que " + VALOR_PEDIDO_ZERO + ".");
        }
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
