package br.com.jezielmonteiro.VassCommerce.model;

import br.com.jezielmonteiro.VassCommerce.controller.itempedido.ItemPedidoModelInterface;
import java.util.List;

public class ItemPedidoModel implements ItemPedidoModelInterface {
    private Long id;
    private Integer quantidade;
    private double valorUnitario;
    private PedidoModel pedido;

    public ItemPedidoModel(Long id, Integer quantidade, double valorUnitario, PedidoModel pedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    @Override
    public List<ItemPedidoModel> listarTodosItensPedidos() {
        return null;
    }
}