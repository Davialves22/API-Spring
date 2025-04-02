package com.dev.spring.produtosapi.controller;

import com.dev.spring.produtosapi.model.Produto;
import com.dev.spring.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController //requisições REST
@RequestMapping("produtos")
public class ProdutoController {

    //classe gerenciada pelo Spring
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto Registrado no sistema:" + produto);

        //gerar id automaticamente
        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return produto; // retorna o objeto criado
    }

    //retornando o produto pelo id
    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id) {
        //primeira forma de se fazer
//        Optional<Produto> produto = produtoRepository.findById(id);
//        return produto.isPresent() ? produto.get() : null;

        //forma mais facil de se fazer
        return produtoRepository.findById(id).orElse(null);
    }
}