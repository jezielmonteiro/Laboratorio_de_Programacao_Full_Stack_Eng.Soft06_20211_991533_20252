package br.com.api.mapper;

import br.com.api.controller.dto.produto.ProdutoRequest;
import br.com.api.controller.dto.produto.ProdutoResponse;
import br.com.api.model.ProdutoModel;

public class ProdutoMapper {

    private ProdutoMapper() {}

    public static ProdutoModel toEntity(ProdutoRequest req, Long newId) {
        ProdutoModel p = new ProdutoModel();
        p.setId(newId);
        p.setNome(req.getNome());
        p.setPreco(req.getPreco());
        p.setCategoria(req.getCategoria());
        return p;
    }

    public static ProdutoResponse toResponse(ProdutoModel p) {
        ProdutoResponse r = new ProdutoResponse();
        r.setId(p.getId());
        r.setNome(p.getNome());
        r.setPreco(p.getPreco());
        r.setCategoria(p.getCategoria());
        return r;
    }
}