package br.com.api.model;

import br.com.api.controller.categoria.CategoriaModelInterface;
import java.util.List;

public class CategoriaModel implements CategoriaModelInterface {
    private Long id;
    private String nome;

    public CategoriaModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaModel() {}

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

    @Override
    public List<CategoriaModel> listarCategorias() {
        return null;
    }
}