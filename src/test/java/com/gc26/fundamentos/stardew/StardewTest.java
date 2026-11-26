package com.gc26.fundamentos.stardew;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StardewTest {

    @Test
    void vacaProduzLeiteEFazMuu() {
        Animal vaca = new VacaMae("Mimosa", 7, "Sem filhotes");

        assertEquals("Muuu!", vaca.emitirSom());
        assertEquals("Você coletou leite fresco de Mimosa!", vaca.coletarProduto());
        assertEquals("[Vaca] Nome: Mimosa | Idade: 7 ano(s) | Status: Sem filhotes", vaca.detalhes());
    }

    @Test
    void bezerroHerdaOSomMasNaoProduzLeite() {
        Animal bezerro = new Bezerro("Malhado", 0, "Crescendo");

        assertEquals("Muuu!", bezerro.emitirSom());
        assertTrue(bezerro.coletarProduto().contains("não produz leite"));
        assertTrue(bezerro.detalhes().startsWith("[Bezerro]"));
    }

    @Test
    void galinhaBotaOvoEPintinhoAindaNao() {
        Animal galinha = new GalinhaMae("Giselda", 1, "Botando ovos");
        Animal pintinho = new Pintinho("Piu", 0, "Crescendo");

        assertEquals("Você coletou um ovo de Giselda!", galinha.coletarProduto());
        assertEquals("Có-có!", pintinho.emitirSom());
        assertTrue(pintinho.coletarProduto().contains("não bota ovos"));
    }

    @Test
    void naoAceitaNomeVazioNemIdadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> new VacaMae(" ", 1, "ok"));
        assertThrows(IllegalArgumentException.class, () -> new GalinhaMae(null, 1, "ok"));
        assertThrows(IllegalArgumentException.class, () -> new Pintinho("Piu", -1, "ok"));
    }
}
