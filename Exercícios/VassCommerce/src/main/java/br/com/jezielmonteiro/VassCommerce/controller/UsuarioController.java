package br.com.jezielmonteiro.VassCommerce.controller;

import br.com.jezielmonteiro.VassCommerce.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios", consumes = "application/json", produces = "application/json")
public class UsuarioController {

    private final List<Usuario> usuarios;

    public UsuarioController() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "Usuário 1", "usuario1@gmail.com", "usuario1#123", "02/10/2025", "01/10/2025", "img/usuario1.png"));
        usuarios.add(new Usuario(2L, "Usuário 2", "usuario2@gmail.com", "usuario2#123", "02/10/2025", "01/10/2025", "img/usuario2.png"));
        usuarios.add(new Usuario(3L, "Usuário 3", "usuario3@gmail.com", "usuario3#123", "02/10/2025", "01/10/2025", "img/usuario3.png"));
        usuarios.add(new Usuario(4L, "Usuário 4", "usuario4@gmail.com", "usuario4#123", "02/10/2025", "01/10/2025", "img/usuario4.png"));
        usuarios.add(new Usuario(5L, "Usuário 5", "usuario5@gmail.com", "usuario5#123", "02/10/2025", "01/10/2025", "img/usuario5.png"));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(
            @RequestParam(name = "nomeCompleto", required = false) String nomeCompleto){

        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            return ResponseEntity.ok(usuarios); // 200 com a lista completa
        }

        List<Usuario> filtrados = new ArrayList<>();
        for(Usuario usuario : usuarios) {
            if(usuario.getNomeCompleto().toLowerCase().contains(nomeCompleto.toLowerCase())) {
                filtrados.add(usuario);
            }
        }

        return ResponseEntity.ok(filtrados); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable Long id) {
        Usuario encontrado = null;
        for(Usuario usuario : usuarios) {
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
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        usuario.setId((long) (usuarios.size() + 1));
        usuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }
}