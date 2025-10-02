package br.com.jezielmonteiro.VassCommerce;

public class Cliente extends Usuario {
    private String fotoUrl;
    private String dataNascimento;
    private String cpf;

    public Cliente(Long id, String nomeCompleto, String email, String senha, String dataUltimaAtualizacao, String dataCadastro, String fotoUrl, String fotoUrl1, String dataNascimento, String cpf) {
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