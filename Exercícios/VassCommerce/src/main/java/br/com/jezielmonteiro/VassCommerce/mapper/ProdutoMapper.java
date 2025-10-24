package br.com.jezielmonteiro.VassCommerce.mapper;

import br.com.jezielmonteiro.VassCommerce.controller.dto.produto.ProdutoRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.produto.ProdutoResponse;
import br.com.jezielmonteiro.VassCommerce.model.ProdutoModel;

public final class ProdutoMapper {

    private ProdutoMapper() {}

    public static ProdutoModel toEntity(ProdutoRequest req, Long newId) {
        ProdutoModel p = new ProdutoModel();
        p.setId(newId);
        p.setNome(req.getNome());
        p.setDescricao(req.getDescricao());
        p.setFotoUrl(req.getFotoUrl());
        p.setDataCadastro(req.getDataCadastro());
        p.setDataUltimaAtualizacao(req.getDataUltimaAtualizacao());
        p.setValorUnitario(req.getValorUnitario());
        return p;
    }

    public static ProdutoResponse toResponse(ProdutoModel p) {
        ProdutoResponse r = new ProdutoResponse();
        r.setId(p.getId());
        r.setNome(p.getNome());
        r.setDescricao(p.getDescricao());
        r.setFotoUrl(p.getFotoUrl());
        r.setDataCadastro(p.getDataCadastro());
        r.setDataUltimaAtualizacao(p.getDataUltimaAtualizacao());
        r.setValorUnitario(p.getValorUnitario());
        return r;
    }
}