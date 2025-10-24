package br.com.jezielmonteiro.VassCommerce.controller.dto.cliente;

import br.com.jezielmonteiro.VassCommerce.controller.dto.usuario.UsuarioResponse;

public class ClienteResponse extends UsuarioResponse {
    private String fotoUrlCliente;
    private String dataNascimento;
    private String cpf;

    public String getFotoUrlCliente() {
        return fotoUrlCliente;
    }

    public void setFotoUrlCliente(String fotoUrlCliente) {
        this.fotoUrlCliente = fotoUrlCliente;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}