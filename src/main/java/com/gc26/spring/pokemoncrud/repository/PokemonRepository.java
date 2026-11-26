package com.gc26.spring.pokemoncrud.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.gc26.spring.pokemoncrud.model.Pokemon;

@Repository
public class PokemonRepository {

    private final List<Pokemon> pokemons = new CopyOnWriteArrayList<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    public PokemonRepository() {
        adicionar("Pikachu", "Elétrico");
        adicionar("Charmander", "Fogo");
        adicionar("Bulbasaur", "Planta");
        adicionar("Squirtle", "Água");
    }

    public List<Pokemon> listar(int limite) {
        return pokemons.stream().limit(limite).toList();
    }

    public Optional<Pokemon> buscar(Long id) {
        return pokemons.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Pokemon criar(String nome, String tipo) {
        return adicionar(nome, tipo);
    }

    public boolean remover(Long id) {
        return pokemons.removeIf(p -> p.getId().equals(id));
    }

    private Pokemon adicionar(String nome, String tipo) {
        Pokemon pokemon = new Pokemon(proximoId.getAndIncrement(), nome, tipo);
        pokemons.add(pokemon);
        return pokemon;
    }
}
