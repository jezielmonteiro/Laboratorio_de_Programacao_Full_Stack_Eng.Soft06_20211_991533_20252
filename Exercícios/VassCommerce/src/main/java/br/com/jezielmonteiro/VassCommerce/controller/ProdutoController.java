package br.com.jezielmonteiro.VassCommerce.controller;

import br.com.jezielmonteiro.VassCommerce.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/produtos", consumes = "application/json", produces = "application/json")
public class ProdutoController {

    private final List<Produto> produtos;

    public ProdutoController() {
        produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto 1", "Descrição do Produto 1", "img/produto1.png", "01/10/2025", "02/10/2025", 499.99));
        produtos.add(new Produto(2L, "Produto 2", "Descrição do Produto 2", "img/produto2.png", "01/10/2025", "02/10/2025", 199.99));
        produtos.add(new Produto(3L, "Produto 3", "Descrição do Produto 3", "img/produto3.png", "01/10/2025", "02/10/2025", 99.99));
        produtos.add(new Produto(4L, "Produto 4", "Descrição do Produto 4", "img/produto4.png", "01/10/2025", "02/10/2025", 699.99));
        produtos.add(new Produto(5L, "Produto 5", "Descrição do Produto 5", "img/produto5.png", "01/10/2025", "02/10/2025", 59.99));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(
            @RequestParam(name = "nome", required = false) String nome){

        if (nome == null || nome.isBlank()) {
            return ResponseEntity.ok(produtos); // 200 com a lista completa
        }

        List<Produto> filtrados = new ArrayList<>();
        for(Produto produto : produtos) {
            if(produto.getNome().toLowerCase().contains(nome.toLowerCase())) {
                filtrados.add(produto);
            }
        }

        return ResponseEntity.ok(filtrados); // 200 com a lista filtrada (pode estar vazia)
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterPorId(@PathVariable Long id) {
        Produto encontrado = null;
        for(Produto produto : produtos) {
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
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        produto.setId((long) (produtos.size() + 1));
        produtos.add(produto);
        return ResponseEntity.status(201).body(produto);
    }
}