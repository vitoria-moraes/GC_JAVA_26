package com.gc26.spring.pokemoncrud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gc26.spring.pokemoncrud.exception.PokemonNaoEncontradoException;
import com.gc26.spring.pokemoncrud.model.Pokemon;
import com.gc26.spring.pokemoncrud.repository.PokemonRepository;
import com.gc26.spring.pokemoncrud.service.PokemonService;

class PokemonServiceTest {

    private PokemonService service;

    @BeforeEach
    void preparar() {
        service = new PokemonService(new PokemonRepository());
    }

    private static Pokemon dados(String nome, String tipo) {
        Pokemon pokemon = new Pokemon();
        pokemon.setNome(nome);
        pokemon.setTipo(tipo);
        return pokemon;
    }

    @Test
    void comecaComQuatroPokemonsELimitaEm50() {
        assertEquals(4, service.listar(50).size());
        assertEquals(4, service.listar(1000).size());
        assertEquals(2, service.listar(2).size());
        assertEquals(0, service.listar(-5).size());
    }

    @Test
    void buscaPorIdExistenteEInexistente() {
        assertEquals("Pikachu", service.buscar(1L).getNome());
        assertThrows(PokemonNaoEncontradoException.class, () -> service.buscar(999L));
    }

    @Test
    void criarGeraNovoIdEAparaOsEspacos() {
        Pokemon criado = service.criar(dados("  Eevee ", " Normal "));

        assertEquals(5L, criado.getId().longValue());
        assertEquals("Eevee", criado.getNome());
        assertEquals("Normal", criado.getTipo());
        assertEquals(5, service.listar(50).size());
    }

    @Test
    void criarERejeitaDadosEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> service.criar(dados("", "Fogo")));
        assertThrows(IllegalArgumentException.class, () -> service.criar(dados("Vulpix", null)));
        assertThrows(IllegalArgumentException.class, () -> service.criar(null));
    }

    @Test
    void atualizarAlteraNomeETipoEValidaAntesDeBuscar() {
        Pokemon atualizado = service.atualizar(2L, dados("Charmeleon", "Fogo"));

        assertEquals("Charmeleon", service.buscar(2L).getNome());
        assertEquals(2L, atualizado.getId().longValue());
        assertThrows(IllegalArgumentException.class, () -> service.atualizar(2L, dados(" ", "Fogo")));
        assertThrows(PokemonNaoEncontradoException.class, () -> service.atualizar(999L, dados("X", "Y")));
    }

    @Test
    void removerApagaEDepoisNaoEncontra() {
        service.remover(4L);

        assertThrows(PokemonNaoEncontradoException.class, () -> service.buscar(4L));
        assertThrows(PokemonNaoEncontradoException.class, () -> service.remover(4L));
    }
}
