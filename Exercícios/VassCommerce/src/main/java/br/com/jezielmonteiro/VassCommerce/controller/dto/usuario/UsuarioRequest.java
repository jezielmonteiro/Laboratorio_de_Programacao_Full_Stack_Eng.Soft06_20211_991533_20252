package br.com.jezielmonteiro.VassCommerce.controller.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank(message="O nome completo é obrigatório")
    @Size(max = 80, message = "O nome completo deve ter no máximo 80 caracteres")
    private String nomeCompleto;
    @NotBlank(message="O email é obrigatório")
    private String email;
    @NotBlank(message="A senha é obrigatória")
    private String senha;
    @NotBlank(message="A data da última atualização é obrigatória")
    private String dataUltimaAtualizacao;
    @NotBlank(message="A data do cadastro é obrigatória")
    private String dataCadastro;
    private String fotoUrl;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}