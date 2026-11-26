package com.gc26.spring.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gc26.spring.apirest.entity.Pokemon;
import com.gc26.spring.apirest.service.PokemonService;

@RestController
@RequestMapping("/pokeApi")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/pokemon/{nome}")
    public Pokemon quemEEssePokemon(@PathVariable String nome) {
        return service.buscarPorNome(nome);
    }
}
