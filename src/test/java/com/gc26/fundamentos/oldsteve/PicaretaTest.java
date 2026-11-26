package com.gc26.fundamentos.oldsteve;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.gc26.fundamentos.oldsteve.oo.Picareta;
import com.gc26.fundamentos.oldsteve.oo.PicaretaFerro;
import com.gc26.fundamentos.oldsteve.oo.PicaretaMadeira;
import com.gc26.fundamentos.oldsteve.procedural.PicaretaProcedural;

class PicaretaTest {

    @Test
    void mineraGastaUmaDurabilidadePorBloco() {
        Picareta ferro = new PicaretaFerro();

        int minerados = ferro.minerar(30);

        assertEquals(30, minerados);
        assertEquals(70, ferro.getDurabilidade());
    }

    @Test
    void picaretaQuebraQuandoAcabaADurabilidade() {
        Picareta ferro = new PicaretaFerro();

        int minerados = ferro.minerar(150);

        assertEquals(100, minerados);
        assertEquals(0, ferro.getDurabilidade());
        assertEquals(0, ferro.minerar(10));
    }

    @Test
    void reparoNaoPassaDoLimiteMaximo() {
        Picareta madeira = new PicaretaMadeira();
        madeira.minerar(10);

        madeira.reparar(50);

        assertEquals(50, madeira.getDurabilidade());
    }

    @Test
    void reparoSomaQuandoCabeNoLimite() {
        Picareta ferro = new PicaretaFerro();
        ferro.minerar(50);

        ferro.reparar(20);

        assertEquals(70, ferro.getDurabilidade());
    }

    @Test
    void versaoProceduralMineraDaMesmaForma() {
        assertEquals(70, PicaretaProcedural.minerar("ferro", 100, 12, 30));
        assertEquals(0, PicaretaProcedural.minerar("ouro", 30, 15, 50));
    }

    @Test
    void versaoProceduralRespeitaLimiteDoMaterial() {
        assertEquals(100, PicaretaProcedural.repararPicareta("ferro", 70, 50));
        assertEquals(30, PicaretaProcedural.limiteMaximo("OURO"));
        assertEquals(60, PicaretaProcedural.limiteMaximo("diamante"));
    }

    @Test
    void versaoProceduralRecusaPicaretaInvalida() {
        assertNull(PicaretaProcedural.fabricarPicareta("ferro", 0, 10));
    }

    @Test
    void naoAceitaBlocosOuReparoNegativos() {
        Picareta ferro = new PicaretaFerro();

        assertThrows(IllegalArgumentException.class, () -> ferro.minerar(-1));
        assertThrows(IllegalArgumentException.class, () -> ferro.reparar(-1));
        assertThrows(IllegalArgumentException.class, () -> PicaretaProcedural.minerar("ferro", 10, 1, -1));
        assertThrows(IllegalArgumentException.class, () -> PicaretaProcedural.repararPicareta("ferro", 10, -1));
    }

    @Test
    void naoCriaPicaretaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new Picareta("", 10, 1, 10) { });
        assertThrows(IllegalArgumentException.class, () -> new Picareta("ouro", 0, 1, 10) { });
        assertThrows(IllegalArgumentException.class, () -> new Picareta("ouro", 20, 1, 10) { });
    }
}
