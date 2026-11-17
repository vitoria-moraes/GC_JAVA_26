package org.example.atividadebarbieguardaroupas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuardaRoupaTest {

    private GuardaRoupa guardaRoupa;

    @BeforeEach
    void preparar() {
        guardaRoupa = new GuardaRoupa(500.00);
    }

    //TESTES DE DESCONTO

    @Test
    @DisplayName("Deve aplicar 10% de desconto em 200 reais e resultar em 180")
    void deveAplicarDescontoCorretamente() {
        double resultado = guardaRoupa.precoComDesconto(200.00, 10);
        assertEquals(180.00, resultado, 0.01);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o desconto for negativo")
    void deveLancarExcecaoParaDescontoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            guardaRoupa.precoComDesconto(200.00, -10);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando o desconto for acima de 100")
    void deveLancarExcecaoParaDescontoAcimaDeCem() {
        assertThrows(IllegalArgumentException.class, () -> {
            guardaRoupa.precoComDesconto(200.00, 110);
        });
    }

    // TESTES DE ORÇAMENTO

    @Test
    @DisplayName("Deve retornar true quando o preço estiver abaixo do orçamento")
    void deveCaberNoOrcamentoQuandoPrecoMenor() {
        assertTrue(guardaRoupa.cabeNoOrcamento(400.00));
    }

    @Test
    @DisplayName("Deve retornar true quando o preço for exatamente igual ao orçamento")
    void deveCaberNoOrcamentoQuandoPrecoExato() {
        assertTrue(guardaRoupa.cabeNoOrcamento(500.00));
    }

    //TESTES DE LOOK DE GALA

    @Test
    @DisplayName("Deve retornar false para look abaixo de 200 reais")
    void naoDeveSerDeGalaAbaixoDeDuzentos() {
        assertFalse(guardaRoupa.ehDeGala(150.00));
    }

    @Test
    @DisplayName("Deve retornar false para look de exatamente 200 reais")
    void naoDeveSerDeGalaExatamenteDuzentos() {
        assertFalse(guardaRoupa.ehDeGala(200.00));
    }

    @Test
    @DisplayName("Deve retornar true para look acima de 200 reais")
    void deveSerDeGalaAcimaDeDuzentos() {
        assertTrue(guardaRoupa.ehDeGala(250.00));
    }

    // DESAFIO EXTRA

    @Test
    @DisplayName("Deve atualizar o orçamento corretamente após registrar duas compras")
    void deveAtualizarOrcamentoAposDuasCompras() {
        guardaRoupa.registrarCompra(150.00);
        guardaRoupa.registrarCompra(100.00);
        assertEquals(250.00, guardaRoupa.getOrcamento(), 0.01);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar registrar compra acima do orçamento")
    void deveLancarExcecaoAoComprarAcimaDoOrcamento() {
        // o código enviado, registrarCompra também não bloqueia compra maior que o saldo!
        assertThrows(IllegalArgumentException.class, () -> {
            guardaRoupa.registrarCompra(600.00);
        });
    }
}