package com.gc26.spring.apirest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gc26.spring.apirest.entity.Pokemon;

@Service
public class PokemonService {

    private final RestClient client;

    public PokemonService(RestClient.Builder builder) {
        this.client = builder.baseUrl("https://pokeapi.co/api/v2").build();
    }

    public Pokemon buscarPorNome(String nome) {
        return client.get()
                .uri("/pokemon/{nome}", nome.toLowerCase())
                .retrieve()
                .body(Pokemon.class);
    }
}
