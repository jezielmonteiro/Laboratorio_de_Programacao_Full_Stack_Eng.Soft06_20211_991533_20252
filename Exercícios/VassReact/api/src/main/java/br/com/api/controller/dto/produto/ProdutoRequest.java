package br.com.api.controller.dto.produto;

import br.com.api.model.CategoriaModel;
import jakarta.validation.constraints.NotBlank;

public class ProdutoRequest {
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
    @NotBlank(message = "O preço do produto é obrigatório")
    private double preco;
    @NotBlank(message = "A categoria do produto é obrigatória")
    private CategoriaModel categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }
}