package com.gc26.fundamentos.cybertron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gc26.fundamentos.cybertron.autobot.Autobot;
import com.gc26.fundamentos.cybertron.autobot.GerenciadorAutobots;

class GerenciadorAutobotsTest {

    private GerenciadorAutobots gerenciador;

    @BeforeEach
    void preparar() {
        gerenciador = new GerenciadorAutobots();
        gerenciador.adicionar(new Autobot("Optimus Prime", 850.0, 10));
        gerenciador.adicionar(new Autobot("Bumblebee", 320.0, 5));
        gerenciador.adicionar(new Autobot("Ratchet", 180.0, 7));
    }

    @Test
    void somaOEnergonDeTodosOsAutobots() {
        assertEquals(1350.0, gerenciador.calcularEnergonTotal(), 0.001);
    }

    @Test
    void buscaPeloConteudoDoNomeEMesmoComStringNova() {
        Autobot encontrado = gerenciador.buscar(new String("Bumblebee"));

        assertEquals("Bumblebee", encontrado.getNome());
    }

    @Test
    void buscaRetornaNuloQuandoNaoExisteOuNomeNulo() {
        assertNull(gerenciador.buscar("Megatron"));
        assertNull(gerenciador.buscar(null));
    }

    @Test
    void repararAcrescentaCemDeEnergon() {
        Autobot ratchet = gerenciador.buscar("Ratchet");

        gerenciador.reparar(ratchet);

        assertEquals(280.0, ratchet.getEnergon(), 0.001);
    }

    @Test
    void classificaPelasFaixasDeEnergon() {
        assertEquals("ELITE", gerenciador.classificar(new Autobot("A", 500.1, 1)));
        assertEquals("PADRAO", gerenciador.classificar(new Autobot("B", 500.0, 1)));
        assertEquals("PADRAO", gerenciador.classificar(new Autobot("C", 200.1, 1)));
        assertEquals("RESERVA", gerenciador.classificar(new Autobot("D", 200.0, 1)));
    }

    @Test
    void naoAceitaAutobotNulo() {
        assertThrows(IllegalArgumentException.class, () -> gerenciador.adicionar(null));
        assertThrows(IllegalArgumentException.class, () -> gerenciador.reparar(null));
        assertThrows(IllegalArgumentException.class, () -> gerenciador.classificar(null));
    }

    @Test
    void naoAceitaEnergonNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Autobot("X", -1.0, 1));
        Autobot ratchet = gerenciador.buscar("Ratchet");
        assertThrows(IllegalArgumentException.class, () -> ratchet.setEnergon(-5.0));
    }
}
