package com.gc26.fundamentos.barbie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GuardaRoupaTest {

    private GuardaRoupa guardaRoupa;

    @BeforeEach
    void preparar() {
        guardaRoupa = new GuardaRoupa(500.00);
    }

    @Test
    @DisplayName("Deve aplicar 10% de desconto em 200 reais e resultar em 180")
    void deveAplicarDescontoCorretamente() {
        assertEquals(180.00, guardaRoupa.precoComDesconto(200.00, 10), 0.01);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o desconto for negativo")
    void deveLancarExcecaoParaDescontoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> guardaRoupa.precoComDesconto(200.00, -10));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o desconto for acima de 100")
    void deveLancarExcecaoParaDescontoAcimaDeCem() {
        assertThrows(IllegalArgumentException.class, () -> guardaRoupa.precoComDesconto(200.00, 110));
    }

    @Test
    @DisplayName("Deve caber no orçamento quando o preço for menor")
    void deveCaberNoOrcamentoQuandoPrecoMenor() {
        assertTrue(guardaRoupa.cabeNoOrcamento(400.00));
    }

    @Test
    @DisplayName("Deve caber no orçamento quando o preço for exatamente igual")
    void deveCaberNoOrcamentoQuandoPrecoExato() {
        assertTrue(guardaRoupa.cabeNoOrcamento(500.00));
    }

    @Test
    @DisplayName("Não deve caber no orçamento quando o preço for maior")
    void naoDeveCaberNoOrcamentoQuandoPrecoMaior() {
        assertFalse(guardaRoupa.cabeNoOrcamento(500.01));
    }

    @Test
    @DisplayName("Não deve ser de gala abaixo de 200 reais")
    void naoDeveSerDeGalaAbaixoDeDuzentos() {
        assertFalse(guardaRoupa.ehDeGala(150.00));
    }

    @Test
    @DisplayName("Não deve ser de gala com exatamente 200 reais")
    void naoDeveSerDeGalaExatamenteDuzentos() {
        assertFalse(guardaRoupa.ehDeGala(200.00));
    }

    @Test
    @DisplayName("Deve ser de gala acima de 200 reais")
    void deveSerDeGalaAcimaDeDuzentos() {
        assertTrue(guardaRoupa.ehDeGala(250.00));
    }

    @Test
    @DisplayName("Deve atualizar o orçamento após duas compras")
    void deveAtualizarOrcamentoAposDuasCompras() {
        guardaRoupa.registrarCompra(150.00);
        guardaRoupa.registrarCompra(100.00);
        assertEquals(250.00, guardaRoupa.getOrcamento(), 0.01);
    }

    @Test
    @DisplayName("Deve lançar exceção ao comprar acima do orçamento")
    void deveLancarExcecaoAoComprarAcimaDoOrcamento() {
        assertThrows(IllegalArgumentException.class, () -> guardaRoupa.registrarCompra(600.00));
    }

    @Test
    @DisplayName("Não deve aceitar orçamento negativo")
    void naoDeveAceitarOrcamentoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new GuardaRoupa(-1));
    }

    @Test
    @DisplayName("Não deve aceitar preço negativo no desconto nem na compra")
    void naoDeveAceitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> guardaRoupa.precoComDesconto(-10, 10));
        assertThrows(IllegalArgumentException.class, () -> guardaRoupa.registrarCompra(-10));
    }

    @Test
    @DisplayName("Compra do valor exato do orçamento zera o saldo")
    void compraDoValorExatoZeraOOrcamento() {
        guardaRoupa.registrarCompra(500.00);
        assertEquals(0.0, guardaRoupa.getOrcamento(), 0.001);
    }
}
