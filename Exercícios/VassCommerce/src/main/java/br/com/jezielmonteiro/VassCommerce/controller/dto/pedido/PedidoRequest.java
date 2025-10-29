package br.com.jezielmonteiro.VassCommerce.controller.dto.pedido;

import br.com.jezielmonteiro.VassCommerce.model.ClienteModel;
import jakarta.validation.constraints.NotBlank;

public class PedidoRequest {
    @NotBlank(message = "A data do cadastro é obrigatória")
    private String dataCadastro;
    @NotBlank(message = "O valor total é obrigatório")
    private double valorTotal;
    @NotBlank(message = "O cliente é obrigatório")
    private ClienteModel cliente;

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