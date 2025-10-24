package br.com.jezielmonteiro.VassCommerce.mapper;

import br.com.jezielmonteiro.VassCommerce.controller.dto.usuario.UsuarioRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.usuario.UsuarioResponse;
import br.com.jezielmonteiro.VassCommerce.model.UsuarioModel;

public final class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioModel toEntity(UsuarioRequest req, Long newId) {
        UsuarioModel u = new UsuarioModel();
        u.setId(newId);
        u.setNomeCompleto(req.getNomeCompleto());
        u.setEmail(req.getEmail());
        u.setSenha(req.getSenha());
        u.setDataUltimaAtualizacao((req.getDataUltimaAtualizacao()));
        u.setDataCadastro(req.getDataCadastro());
        u.setFotoUrl(req.getFotoUrl());
        return u;
    }

    public static UsuarioResponse toResponse(UsuarioModel u) {
        UsuarioResponse r = new UsuarioResponse();
        r.setId(u.getId());
        r.setNomeCompleto(u.getNomeCompleto());
        r.setEmail(u.getEmail());
        r.setSenha(u.getSenha());
        r.setDataUltimaAtualizacao((u.getDataUltimaAtualizacao()));
        r.setDataCadastro(u.getDataCadastro());
        r.setFotoUrl(u.getFotoUrl());
        return r;
    }
}