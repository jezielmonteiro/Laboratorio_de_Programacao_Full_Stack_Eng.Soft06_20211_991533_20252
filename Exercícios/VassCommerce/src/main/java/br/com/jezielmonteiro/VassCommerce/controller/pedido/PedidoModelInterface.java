package br.com.jezielmonteiro.VassCommerce.controller.pedido;

import br.com.jezielmonteiro.VassCommerce.model.PedidoModel;
import java.util.List;

public interface PedidoModelInterface {
    List<PedidoModel> listarTodosPedidos();
}