package br.com.api.controller.auth.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull(message = "Campo username é obrigatório para autenticação")
    private String username;
    @NotNull(message = "Campo password é obrigatório para autenticação")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}