package br.com.api.controller.categoria;

import br.com.api.controller.dto.categoria.CategoriaRequest;
import br.com.api.controller.dto.categoria.CategoriaResponse;
import br.com.api.mapper.CategoriaMapper;
import br.com.api.model.CategoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/categorias", consumes = "application/json", produces = "application/json")
public class CategoriaController {

    private final List<CategoriaModel> categorias;

    @Autowired
    private final CategoriaModelInterface model;

    public CategoriaController(CategoriaModelInterface model) {
        this.model = model;

        categorias = new ArrayList<>();
        categorias.add(new CategoriaModel(10L, "Periféricos"));
        categorias.add(new CategoriaModel(20L, "Monitores"));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listarCategorias(
            @RequestParam(name = "nome", required = false) String nome){

        if (nome == null || nome.isBlank()) {
            return ResponseEntity.ok(model.listarCategorias()); // 200 com a lista completa
        }

        List<CategoriaModel> filtradas = new ArrayList<>();
        for(CategoriaModel categoria : categorias) {
            if(categoria.getNome().toLowerCase().contains(nome.toLowerCase())) {
                filtradas.add(categoria);
            }
        }
        return ResponseEntity.ok(filtradas); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> obterPorId(@PathVariable Long id) {
        CategoriaModel encontrada = null;
        for(CategoriaModel categoria : categorias) {
            if(categoria.getId() == id) {
                encontrada = categoria;
            }
        }
        if (encontrada == null) {
            return ResponseEntity.notFound().build(); // 404 quando o id não existe
        }
        return ResponseEntity.ok(encontrada);         // 200 com JSON do livro
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CategoriaModel> create(@RequestBody CategoriaModel categoria) {
        categoria.setId((long) (categorias.size() + 1));
        categorias.add(categoria);
        return ResponseEntity.status(201).body(categoria);
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity <CategoriaResponse> create(@Valid @RequestBody CategoriaRequest body) {
        long newId = (long) (categorias.size() + 1);
        CategoriaModel categoryToSave = CategoriaMapper.toEntity(body, newId);
        categorias.add(categoryToSave);
        return ResponseEntity.ok(CategoriaMapper.toResponse(categoryToSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity <CategoriaResponse > get(@PathVariable Long id) {
        for (CategoriaModel categoria : categorias) {
            if (categoria.getId() == id) {
                return ResponseEntity.ok(CategoriaMapper.toResponse(categoria));
            }
        }
        return ResponseEntity.notFound().build();
    }
}