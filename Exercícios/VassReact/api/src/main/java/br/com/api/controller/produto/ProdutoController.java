package br.com.api.controller.produto;

import br.com.api.controller.dto.produto.ProdutoRequest;
import br.com.api.controller.dto.produto.ProdutoResponse;
import br.com.api.mapper.ProdutoMapper;
import br.com.api.model.CategoriaModel;
import br.com.api.model.ProdutoModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/produtos", consumes = "application/json", produces = "application/json")
public class ProdutoController {

    private final List<ProdutoModel> produtos;
    private List<CategoriaModel> categorias = new ArrayList<>();

    @Autowired
    private final ProdutoModelInterface model;

    public ProdutoController(ProdutoModelInterface model) {
        this.model = model;

        produtos = new ArrayList<>();
        produtos.add(new ProdutoModel(101L, "Teclado Mecânico", 150.90, categorias.get(9)));
        produtos.add(new ProdutoModel(102L, "Mouse Óptico", 89.50, categorias.get(19)));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos(
            @RequestParam(name = "nome", required = false) String nome){

        if (nome == null || nome.isBlank()) {
            return ResponseEntity.ok(model.listarProdutos()); // 200 com a lista completa
        }

        List<ProdutoModel> filtrados = new ArrayList<>();
        for(ProdutoModel produto : produtos) {
            if(produto.getNome().toLowerCase().contains(nome.toLowerCase())) {
                filtrados.add(produto);
            }
        }
        return ResponseEntity.ok(filtrados); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> obterPorId(@PathVariable Long id) {
        ProdutoModel encontrado = null;
        for(ProdutoModel produto : produtos) {
            if(produto.getId() == id) {
                encontrado = produto;
            }
        }
        if (encontrado == null) {
            return ResponseEntity.notFound().build(); // 404 quando o id não existe
        }
        return ResponseEntity.ok(encontrado);         // 200 com JSON do livro
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProdutoModel> create(@RequestBody ProdutoModel produto) {
        produto.setId((long) (produtos.size() + 1));
        produtos.add(produto);
        return ResponseEntity.status(201).body(produto);
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity <ProdutoResponse> create(@Valid @RequestBody ProdutoRequest body) {
        long newId = (long) (produtos.size() + 1);
        ProdutoModel productToSave = ProdutoMapper.toEntity(body, newId);
        produtos.add(productToSave);
        return ResponseEntity.ok(ProdutoMapper.toResponse(productToSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity <ProdutoResponse > get(@PathVariable Long id) {
        for (ProdutoModel produto : produtos) {
            if (produto.getId() == id) {
                return ResponseEntity.ok(ProdutoMapper.toResponse(produto));
            }
        }
        return ResponseEntity.notFound().build();
    }
}