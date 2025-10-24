package br.com.jezielmonteiro.VassCommerce.mapper;

import br.com.jezielmonteiro.VassCommerce.controller.dto.cliente.ClienteRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.cliente.ClienteResponse;
import br.com.jezielmonteiro.VassCommerce.model.ClienteModel;

public final class ClienteMapper {

    private ClienteMapper() {}

    public static ClienteModel toEntity(ClienteRequest req, Long newId) {
        ClienteModel c = new ClienteModel();
        c.setId(newId);
        c.setNomeCompleto(req.getNomeCompleto());
        c.setEmail(req.getEmail());
        c.setSenha(req.getSenha());
        c.setDataUltimaAtualizacao((req.getDataUltimaAtualizacao()));
        c.setDataCadastro(req.getDataCadastro());
        c.setFotoUrl(req.getFotoUrl());
        c.setFotoUrlCliente(req.getFotoUrlCliente());
        c.setDataNascimento(req.getDataNascimento());
        c.setCpf(req.getCpf());
        return c;
    }

    public static ClienteResponse toResponse(ClienteModel c) {
        ClienteResponse r = new ClienteResponse();
        r.setId(c.getId());
        r.setNomeCompleto(c.getNomeCompleto());
        r.setEmail(c.getEmail());
        r.setSenha(c.getSenha());
        r.setDataUltimaAtualizacao((c.getDataUltimaAtualizacao()));
        r.setDataCadastro(c.getDataCadastro());
        r.setFotoUrl(c.getFotoUrl());
        r.setFotoUrlCliente(c.getFotoUrlCliente());
        r.setDataNascimento(c.getDataNascimento());
        r.setCpf(c.getCpf());
        return r;
    }
}