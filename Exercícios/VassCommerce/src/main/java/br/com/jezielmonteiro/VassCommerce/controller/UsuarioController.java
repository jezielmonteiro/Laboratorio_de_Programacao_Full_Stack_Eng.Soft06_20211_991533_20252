package br.com.jezielmonteiro.VassCommerce.controller;

import br.com.jezielmonteiro.VassCommerce.controller.dto.UsuarioRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.UsuarioResponse;
import br.com.jezielmonteiro.VassCommerce.mapper.UsuarioMapper;
import br.com.jezielmonteiro.VassCommerce.model.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios", consumes = "application/json", produces = "application/json")
public class UsuarioController {

    private final List<UsuarioModel> usuarios;

    @Autowired
    private final UsuarioModelInterface model;

    public UsuarioController(UsuarioModelInterface model) {
        this.model = model;

        usuarios = new ArrayList<>();
        usuarios.add(new UsuarioModel(1L, "Usuário 1", "usuario1@gmail.com", "usuario1#123", "02/10/2025", "01/10/2025", "img/usuario1.png"));
        usuarios.add(new UsuarioModel(2L, "Usuário 2", "usuario2@gmail.com", "usuario2#123", "02/10/2025", "01/10/2025", "img/usuario2.png"));
        usuarios.add(new UsuarioModel(3L, "Usuário 3", "usuario3@gmail.com", "usuario3#123", "02/10/2025", "01/10/2025", "img/usuario3.png"));
        usuarios.add(new UsuarioModel(4L, "Usuário 4", "usuario4@gmail.com", "usuario4#123", "02/10/2025", "01/10/2025", "img/usuario4.png"));
        usuarios.add(new UsuarioModel(5L, "Usuário 5", "usuario5@gmail.com", "usuario5#123", "02/10/2025", "01/10/2025", "img/usuario5.png"));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(
            @RequestParam(name = "nomeCompleto", required = false) String nomeCompleto){

        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            return ResponseEntity.ok(model.listarTodos()); // 200 com a lista completa
        }

        List<UsuarioModel> filtrados = new ArrayList<>();
        for(UsuarioModel usuario : usuarios) {
            if(usuario.getNomeCompleto().toLowerCase().contains(nomeCompleto.toLowerCase())) {
                filtrados.add(usuario);
            }
        }
        return ResponseEntity.ok(filtrados); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> obterPorId(@PathVariable Long id) {
        UsuarioModel encontrado = null;
        for(UsuarioModel usuario : usuarios) {
            if(usuario.getId() == id) {
                encontrado = usuario;
            }
        }
        if (encontrado == null) {
            return ResponseEntity.notFound().build(); // 404 quando o id não existe
        }
        return ResponseEntity.ok(encontrado);         // 200 com JSON do livro
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UsuarioModel> create(@RequestBody UsuarioModel usuario) {
        usuario.setId((long) (usuarios.size() + 1));
        usuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity <UsuarioResponse> create(@Valid @RequestBody UsuarioRequest body) {
        long newId = (long) (usuarios.size() + 1);
        UsuarioModel userToSave = UsuarioMapper.toEntity(body, newId);
        usuarios.add(userToSave);
        return ResponseEntity.body(UsuarioMapper.toResponse(userToSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity <UsuarioResponse > get(@PathVariable Long id) {
        for (UsuarioModel usuario : usuarios) {
            if (usuario.getId() == id) {
                return ResponseEntity.ok(UsuarioMapper.toResponse(usuario));
            }
        }
        return ResponseEntity.notFound().build();
    }
}