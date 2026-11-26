package com.gc26.fundamentos.revisaocodigo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gc26.fundamentos.revisaocodigo.checkout.CheckoutService;
import com.gc26.fundamentos.revisaocodigo.checkout.Cliente;
import com.gc26.fundamentos.revisaocodigo.checkout.NivelFidelidade;
import com.gc26.fundamentos.revisaocodigo.checkout.StatusCompra;

class CheckoutServiceTest {

    private CheckoutService checkout;

    @BeforeEach
    void preparar() {
        checkout = new CheckoutService();
    }

    private static BigDecimal valor(String texto) {
        return new BigDecimal(texto);
    }

    private static void assertSaldo(String esperado, Cliente cliente) {
        assertEquals(0, cliente.getSaldo().compareTo(valor(esperado)));
    }

    @Test
    void clientePadraoPagaComCupomERecebeCashbackNoSaldo() {
        Cliente cliente = new Cliente("Ana", valor("500.00"), NivelFidelidade.PADRAO, true);

        StatusCompra status = checkout.fecharCompra(cliente, valor("200.00"), "DESC10");

        assertEquals(StatusCompra.APROVADA, status);
        assertSaldo("329.00", cliente);
    }

    @Test
    void clienteOuroTemCashbackComoDescontoNaHora() {
        Cliente cliente = new Cliente("Bia", valor("500.00"), NivelFidelidade.OURO, true);

        StatusCompra status = checkout.fecharCompra(cliente, valor("200.00"), "DESC10");

        assertEquals(StatusCompra.APROVADA, status);
        assertSaldo("329.00", cliente);
    }

    @Test
    void compraAbaixoDeCemNaoGeraCashback() {
        Cliente cliente = new Cliente("Caio", valor("500.00"), null, true);

        StatusCompra status = checkout.fecharCompra(cliente, valor("50.00"), null);

        assertEquals(StatusCompra.APROVADA, status);
        assertSaldo("450.00", cliente);
    }

    @Test
    void saldoInsuficienteNaoAlteraOSaldo() {
        Cliente cliente = new Cliente("Duda", valor("50.00"), NivelFidelidade.PADRAO, true);

        StatusCompra status = checkout.fecharCompra(cliente, valor("200.00"), null);

        assertEquals(StatusCompra.SALDO_INSUFICIENTE, status);
        assertSaldo("50.00", cliente);
    }

    @Test
    void cupomInvalidoRecusaACompra() {
        Cliente cliente = new Cliente("Edu", valor("500.00"), NivelFidelidade.PADRAO, true);

        assertEquals(StatusCompra.RECUSADA, checkout.fecharCompra(cliente, valor("100.00"), "FREE"));
        assertSaldo("500.00", cliente);
    }

    @Test
    void clienteInativoOuDadosInvalidosRecusam() {
        Cliente inativo = new Cliente("Fê", valor("500.00"), NivelFidelidade.PADRAO, false);
        Cliente ativo = new Cliente("Gui", valor("500.00"), NivelFidelidade.PADRAO, true);

        assertEquals(StatusCompra.RECUSADA, checkout.fecharCompra(inativo, valor("100.00"), null));
        assertEquals(StatusCompra.RECUSADA, checkout.fecharCompra(ativo, valor("0.00"), null));
        assertEquals(StatusCompra.RECUSADA, checkout.fecharCompra(ativo, null, null));
        assertEquals(StatusCompra.RECUSADA, checkout.fecharCompra(null, valor("10.00"), null));
    }

    @Test
    void clienteExigeSaldo() {
        assertThrows(NullPointerException.class, () -> new Cliente("Hugo", null, NivelFidelidade.PADRAO, true));
    }
}
