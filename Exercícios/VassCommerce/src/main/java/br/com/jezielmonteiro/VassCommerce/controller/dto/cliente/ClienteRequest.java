package br.com.jezielmonteiro.VassCommerce.controller.dto.cliente;

import br.com.jezielmonteiro.VassCommerce.controller.dto.usuario.UsuarioRequest;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequest extends UsuarioRequest {
    private String fotoUrlCliente;
    @NotBlank(message = "A data de nascimento é obrigatória")
    private String dataNascimento;
    @NotBlank(message = "O CPF é obrigatório")
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