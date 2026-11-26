package com.gc26.spring.pokemoncrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gc26.spring.pokemoncrud.exception.PokemonNaoEncontradoException;
import com.gc26.spring.pokemoncrud.model.Pokemon;
import com.gc26.spring.pokemoncrud.repository.PokemonRepository;

@Service
public class PokemonService {

    private static final int LIMITE_MAXIMO = 50;

    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<Pokemon> listar(int limite) {
        return repository.listar(Math.max(0, Math.min(limite, LIMITE_MAXIMO)));
    }

    public Pokemon buscar(Long id) {
        return repository.buscar(id).orElseThrow(() -> new PokemonNaoEncontradoException(id));
    }

    public Pokemon criar(Pokemon dados) {
        validar(dados);
        return repository.criar(dados.getNome().trim(), dados.getTipo().trim());
    }

    public Pokemon atualizar(Long id, Pokemon dados) {
        validar(dados);
        Pokemon pokemon = buscar(id);
        pokemon.setNome(dados.getNome().trim());
        pokemon.setTipo(dados.getTipo().trim());
        return pokemon;
    }

    public void remover(Long id) {
        if (!repository.remover(id)) {
            throw new PokemonNaoEncontradoException(id);
        }
    }

    private void validar(Pokemon dados) {
        if (dados == null || dados.getNome() == null || dados.getNome().isBlank()
                || dados.getTipo() == null || dados.getTipo().isBlank()) {
            throw new IllegalArgumentException("Nome e tipo são obrigatórios.");
        }
    }
}
