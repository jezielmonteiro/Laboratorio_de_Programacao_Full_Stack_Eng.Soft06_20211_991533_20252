package br.com.jezielmonteiro.VassCommerce.controller.cliente;

import br.com.jezielmonteiro.VassCommerce.model.ClienteModel;
import java.util.List;

public interface ClienteModelInterface {
    List<ClienteModel> listarTodosClientes();
}