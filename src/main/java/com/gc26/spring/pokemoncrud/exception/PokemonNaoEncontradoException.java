package com.gc26.spring.pokemoncrud.exception;

public class PokemonNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PokemonNaoEncontradoException(Long id) {
        super("Pokémon " + id + " não encontrado");
    }
}
