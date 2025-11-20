package br.com.api.mapper;

import br.com.api.controller.dto.categoria.CategoriaRequest;
import br.com.api.controller.dto.categoria.CategoriaResponse;
import br.com.api.model.CategoriaModel;

public final class CategoriaMapper {

    private CategoriaMapper() {}

    public static CategoriaModel toEntity(CategoriaRequest req, Long newId) {
        CategoriaModel c = new CategoriaModel();
        c.setId(newId);
        c.setNome(req.getNome());
        return c;
    }

    public static CategoriaResponse toResponse(CategoriaModel c) {
        CategoriaResponse r = new CategoriaResponse();
        r.setId(c.getId());
        r.setNome(c.getNome());
        return r;
    }
}