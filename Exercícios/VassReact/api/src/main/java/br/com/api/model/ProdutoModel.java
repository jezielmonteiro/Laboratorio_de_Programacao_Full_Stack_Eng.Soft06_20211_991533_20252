package br.com.api.model;

import br.com.api.controller.produto.ProdutoModelInterface;
import java.util.List;

public class ProdutoModel implements ProdutoModelInterface {
    private Long id;
    private String nome;
    private double preco;
    private CategoriaModel categoria;

    public ProdutoModel(Long id, String nome, double preco, CategoriaModel categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public ProdutoModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public List<ProdutoModel> listarProdutos() {
        return null;
    }
}