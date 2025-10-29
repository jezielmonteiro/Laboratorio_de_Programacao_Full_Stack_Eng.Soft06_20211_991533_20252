package br.com.jezielmonteiro.VassCommerce.model;

import br.com.jezielmonteiro.VassCommerce.controller.pedido.PedidoModelInterface;
import java.util.List;

public class PedidoModel implements PedidoModelInterface {
    private Long id;
    private String dataCadastro;
    private double valorTotal;
    private ClienteModel cliente;

    public PedidoModel(Long id, String dataCadastro, double valorTotal, ClienteModel cliente) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    public PedidoModel() {}

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    @Override
    public List<PedidoModel> listarTodosPedidos() {
        return null;
    }
}