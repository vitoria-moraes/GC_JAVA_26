package com.gc26.spring.apirest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gc26.spring.apirest.entity.Product;
import com.gc26.spring.apirest.service.ProductService;

@RestController
@RequestMapping("/productApi")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/product/add/{nome}")
    public Product criarProduto(@PathVariable String nome) {
        return service.adicionar(new Product(nome));
    }
}
