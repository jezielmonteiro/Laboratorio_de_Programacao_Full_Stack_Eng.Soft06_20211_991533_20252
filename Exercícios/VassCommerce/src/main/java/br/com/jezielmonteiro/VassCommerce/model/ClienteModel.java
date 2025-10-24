package br.com.jezielmonteiro.VassCommerce.model;

import br.com.jezielmonteiro.VassCommerce.controller.cliente.ClienteModelInterface;
import java.util.List;

public class ClienteModel extends UsuarioModel implements ClienteModelInterface {
    private String fotoUrlCliente;
    private String dataNascimento;
    private String cpf;

    public ClienteModel(Long id, String nomeCompleto, String email, String senha, String dataUltimaAtualizacao, String dataCadastro, String fotoUrl, String fotoUrlCliente, String dataNascimento, String cpf) {
        super(id, nomeCompleto, email, senha, dataUltimaAtualizacao, dataCadastro, fotoUrl);
        this.fotoUrlCliente = fotoUrlCliente;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public ClienteModel() {}

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

    @Override
    public List<ClienteModel> listarTodosClientes() {
        return null;
    }
}