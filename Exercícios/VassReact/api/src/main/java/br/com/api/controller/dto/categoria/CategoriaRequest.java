package br.com.api.controller.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}