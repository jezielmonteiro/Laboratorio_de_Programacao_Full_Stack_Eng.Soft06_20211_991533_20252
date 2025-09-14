package br.com.jezielmonteiro.VassCommerce;

import java.util.Date;

public class Cliente extends Usuario {
    private String fotoUrl;
    private Date dataNascimento;
    private String cpf;

    public Cliente(Long id, String nomeCompleto, String email, String senha, Date dataUltimaAtualizacao, Date dataCadastro, String fotoUrl, String fotoUrl1, Date dataNascimento, String cpf) {
        super(id, nomeCompleto, email, senha, dataUltimaAtualizacao, dataCadastro, fotoUrl);
        this.fotoUrl = fotoUrl1;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
