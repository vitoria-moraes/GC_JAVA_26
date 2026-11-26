package com.gc26.spring.pokemoncrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gc26.spring.pokemoncrud.model.Pokemon;
import com.gc26.spring.pokemoncrud.service.PokemonService;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pokemon> listarTodos(@RequestParam(defaultValue = "50") int limite) {
        return service.listar(limite);
    }

    @GetMapping("/{id}")
    public Pokemon buscarPorId(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon criar(@RequestBody Pokemon pokemon) {
        return service.criar(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon atualizar(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return service.atualizar(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
