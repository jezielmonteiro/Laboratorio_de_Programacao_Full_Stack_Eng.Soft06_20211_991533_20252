package br.com.jezielmonteiro.VassCommerce.controller.produto;

import br.com.jezielmonteiro.VassCommerce.controller.dto.produto.ProdutoRequest;
import br.com.jezielmonteiro.VassCommerce.controller.dto.produto.ProdutoResponse;
import br.com.jezielmonteiro.VassCommerce.mapper.ProdutoMapper;
import br.com.jezielmonteiro.VassCommerce.model.ProdutoModel;
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

    @Autowired
    private final ProdutoModelInterface model;

    public ProdutoController(ProdutoModelInterface model) {
        this.model = model;

        produtos = new ArrayList<>();
        produtos.add(new ProdutoModel(1L, "Produto 1", "Descrição do Produto 1", "img/produto1.png", "01/10/2025", "02/10/2025", 499.99));
        produtos.add(new ProdutoModel(2L, "Produto 2", "Descrição do Produto 2", "img/produto2.png", "01/10/2025", "02/10/2025", 199.99));
        produtos.add(new ProdutoModel(3L, "Produto 3", "Descrição do Produto 3", "img/produto3.png", "01/10/2025", "02/10/2025", 99.99));
        produtos.add(new ProdutoModel(4L, "Produto 4", "Descrição do Produto 4", "img/produto4.png", "01/10/2025", "02/10/2025", 699.99));
        produtos.add(new ProdutoModel(5L, "Produto 5", "Descrição do Produto 5", "img/produto5.png", "01/10/2025", "02/10/2025", 59.99));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos(
            @RequestParam(name = "nome", required = false) String nome){

        if (nome == null || nome.isBlank()) {
            return ResponseEntity.ok(model.listarTodosProdutos()); // 200 com a lista completa
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