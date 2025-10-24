package br.com.jezielmonteiro.VassCommerce.controller.cliente;

import br.com.jezielmonteiro.VassCommerce.controller.dto.cliente.ClienteRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.cliente.ClienteResponse;
import br.com.jezielmonteiro.VassCommerce.mapper.ClienteMapper;
import br.com.jezielmonteiro.VassCommerce.model.ClienteModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes", consumes = "application/json", produces = "application/json")
public class ClienteController {

    private final List<ClienteModel> clientes;

    @Autowired
    private final ClienteModelInterface model;

    public ClienteController(ClienteModelInterface model) {
        this.model = model;

        clientes = new ArrayList<>();
        clientes.add(new ClienteModel(1L, "Usuário 1", "usuario1@gmail.com", "usuario1#123", "02/10/2025", "01/10/2025", "img/usuario1.png", "img/cliente1.png", "21/03/1987", "123.456.789-10"));
        clientes.add(new ClienteModel(2L, "Usuário 2", "usuario2@gmail.com", "usuario2#123", "02/10/2025", "01/10/2025", "img/usuario2.png", "img/cliente2.png", "04/07/1993", "111.213.141-51"));
        clientes.add(new ClienteModel(3L, "Usuário 3", "usuario3@gmail.com", "usuario3#123", "02/10/2025", "01/10/2025", "img/usuario3.png", "img/cliente3.png", "11/09/2001", "617.181.920-21"));
        clientes.add(new ClienteModel(4L, "Usuário 4", "usuario4@gmail.com", "usuario4#123", "02/10/2025", "01/10/2025", "img/usuario4.png", "img/cliente4.png", "13/08/1998", "222.324.252-62"));
        clientes.add(new ClienteModel(5L, "Usuário 5", "usuario5@gmail.com", "usuario5#123", "02/10/2025", "01/10/2025", "img/usuario5.png", "img/cliente5.png", "09/09/1999", "728.293.031-32"));
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listarClientes(
            @RequestParam(name = "nomeCompleto", required = false) String nomeCompleto){

        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            return ResponseEntity.ok(model.listarTodosClientes()); // 200 com a lista completa
        }

        List<ClienteModel> filtrados = new ArrayList<>();
        for(ClienteModel cliente : clientes) {
            if(cliente.getNomeCompleto().toLowerCase().contains(nomeCompleto.toLowerCase())) {
                filtrados.add(cliente);
            }
        }
        return ResponseEntity.ok(filtrados); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> obterPorId(@PathVariable Long id) {
        ClienteModel encontrado = null;
        for(ClienteModel cliente : clientes) {
            if(cliente.getId() == id) {
                encontrado = cliente;
            }
        }
        if (encontrado == null) {
            return ResponseEntity.notFound().build(); // 404 quando o id não existe
        }
        return ResponseEntity.ok(encontrado);         // 200 com JSON do livro
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel cliente) {
        cliente.setId((long) (clientes.size() + 1));
        clientes.add(cliente);
        return ResponseEntity.status(201).body(cliente);
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity <ClienteResponse> create(@Valid @RequestBody ClienteRequest body) {
        long newId = (long) (clientes.size() + 1);
        ClienteModel customerToSave = ClienteMapper.toEntity(body, newId);
        clientes.add(customerToSave);
        return ResponseEntity.body(ClienteMapper.toResponse(customerToSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity <ClienteResponse > get(@PathVariable Long id) {
        for (ClienteModel cliente : clientes) {
            if (cliente.getId() == id) {
                return ResponseEntity.ok(ClienteMapper.toResponse(cliente));
            }
        }
        return ResponseEntity.notFound().build();
    }
}