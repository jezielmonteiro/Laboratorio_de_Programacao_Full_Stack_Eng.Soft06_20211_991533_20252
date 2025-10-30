package br.com.jezielmonteiro.VassCommerce.controller.pedido;

import br.com.jezielmonteiro.VassCommerce.controller.dto.pedido.PedidoRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.pedido.PedidoResponse;
import br.com.jezielmonteiro.VassCommerce.mapper.PedidoMapper;
import br.com.jezielmonteiro.VassCommerce.model.ClienteModel;
import br.com.jezielmonteiro.VassCommerce.model.ItemPedidoModel;
import br.com.jezielmonteiro.VassCommerce.model.PedidoModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos", consumes = "application/json", produces = "application/json")
public class PedidoController {

    private final List<PedidoModel> pedidos;
    private List<ClienteModel> clientes = new ArrayList<>();

    @Autowired
    private final PedidoModelInterface model;

    public PedidoController(PedidoModelInterface model) {
        this.model = model;

        pedidos = new ArrayList<>();
        pedidos.add(new PedidoModel(1L, "01/10/2025", 499.99, clientes.get(0)));
        pedidos.add(new PedidoModel(2L, "01/10/2025", 199.99, clientes.get(1)));
        pedidos.add(new PedidoModel(3L, "01/10/2025", 99.99, clientes.get(2)));
        pedidos.add(new PedidoModel(4L, "01/10/2025", 699.99, clientes.get(3)));
        pedidos.add(new PedidoModel(5L, "01/10/2025", 59.99, clientes.get(4)));

        PedidoModel pedido = pedidos.get(0);
        pedido.getItens().add(new ItemPedidoModel(1L, 1, 499.99, pedido));
        PedidoModel pedido2 = pedidos.get(1);
        pedido2.getItens().add(new ItemPedidoModel(2L, 1, 199.99, pedido2));
        PedidoModel pedido3 = pedidos.get(2);
        pedido3.getItens().add(new ItemPedidoModel(3L, 1, 99.99, pedido3));
        PedidoModel pedido4 = pedidos.get(3);
        pedido4.getItens().add(new ItemPedidoModel(4L, 1, 699.99, pedido4));
        PedidoModel pedido5 = pedidos.get(4);
        pedido5.getItens().add(new ItemPedidoModel(5L, 1, 59.99, pedido5));
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> listarPedidos(
            @RequestParam(name = "dataCadastro", required = false) String dataCadastro){

        if (dataCadastro == null || dataCadastro.isBlank()) {
            return ResponseEntity.ok(model.listarTodosPedidos()); // 200 com a lista completa
        }

        List<PedidoModel> filtrados = new ArrayList<>();
        for(PedidoModel pedido : pedidos) {
            if(pedido.getDataCadastro().toLowerCase().contains(dataCadastro.toLowerCase())) {
                filtrados.add(pedido);
            }
        }
        return ResponseEntity.ok(filtrados); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> obterPorId(@PathVariable Long id) {
        PedidoModel encontrado = null;
        for(PedidoModel pedido : pedidos) {
            if(pedido.getId() == id) {
                encontrado = pedido;
            }
        }
        if (encontrado == null) {
            return ResponseEntity.notFound().build(); // 404 quando o id não existe
        }
        return ResponseEntity.ok(encontrado);         // 200 com JSON do livro
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PedidoModel> create(@RequestBody PedidoModel pedido) {
        pedido.setId((long) (pedidos.size() + 1));
        pedidos.add(pedido);
        return ResponseEntity.status(201).body(pedido);
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity <PedidoResponse> create(@Valid @RequestBody PedidoRequest body) {
        long newId = (long) (pedidos.size() + 1);
        PedidoModel orderToSave = PedidoMapper.toEntity(body, newId);
        pedidos.add(orderToSave);
        return ResponseEntity.ok(PedidoMapper.toResponse(orderToSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity <PedidoResponse > get(@PathVariable Long id) {
        for (PedidoModel pedido : pedidos) {
            if (pedido.getId() == id) {
                return ResponseEntity.ok(PedidoMapper.toResponse(pedido));
            }
        }
        return ResponseEntity.notFound().build();
    }
}