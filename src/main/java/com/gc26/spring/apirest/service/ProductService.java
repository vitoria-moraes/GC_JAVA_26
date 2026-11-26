package com.gc26.spring.apirest.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gc26.spring.apirest.entity.Product;

@Service
public class ProductService {

    private final RestClient client;

    public ProductService(RestClient.Builder builder) {
        this.client = builder.baseUrl("https://dummyjson.com").build();
    }

    public Product adicionar(Product product) {
        return client.post()
                .uri("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }
}
