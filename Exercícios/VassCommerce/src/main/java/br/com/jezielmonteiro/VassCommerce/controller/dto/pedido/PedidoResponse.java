package br.com.jezielmonteiro.VassCommerce.controller.dto.pedido;

import br.com.jezielmonteiro.VassCommerce.model.ClienteModel;

public class PedidoResponse {
    private Long id;
    private String dataCadastro;
    private double valorTotal;
    private ClienteModel cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
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
}