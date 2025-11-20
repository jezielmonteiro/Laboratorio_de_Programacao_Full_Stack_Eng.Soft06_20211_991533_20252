package br.com.jezielmonteiro.VassCommerce.mapper;

import br.com.jezielmonteiro.VassCommerce.controller.dto.pedido.PedidoRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.pedido.PedidoResponse;
import br.com.jezielmonteiro.VassCommerce.model.PedidoModel;

public final class PedidoMapper {

    private PedidoMapper() {}

    public static PedidoModel toEntity(PedidoRequest req, Long newId) {
        PedidoModel p = new PedidoModel();
        p.setId(newId);
        p.setDataCadastro(req.getDataCadastro());
        p.setValorTotal(req.getValorTotal());
        p.setCliente(req.getCliente());
        return p;
    }

    public static PedidoResponse toResponse(PedidoModel p) {
        PedidoResponse r = new PedidoResponse();
        r.setId(p.getId());
        r.setDataCadastro(p.getDataCadastro());
        r.setValorTotal(p.getValorTotal());
        r.setCliente(p.getCliente());
        return r;
    }
}