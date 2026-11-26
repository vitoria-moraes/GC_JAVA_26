package com.gc26.fundamentos.revisaocodigo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gc26.fundamentos.revisaocodigo.transacao.CategoriaVip;
import com.gc26.fundamentos.revisaocodigo.transacao.Conta;
import com.gc26.fundamentos.revisaocodigo.transacao.MeioPagamento;
import com.gc26.fundamentos.revisaocodigo.transacao.ResultadoTransacao;
import com.gc26.fundamentos.revisaocodigo.transacao.Transacao;
import com.gc26.fundamentos.revisaocodigo.transacao.TransacaoService;

class TransacaoServiceTest {

    private static final LocalDateTime AGORA = LocalDateTime.of(2026, 11, 23, 12, 0);

    private final List<String> alertas = new ArrayList<>();
    private final List<String> emails = new ArrayList<>();
    private TransacaoService service;

    @BeforeEach
    void preparar() {
        Clock relogio = Clock.fixed(AGORA.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
        service = new TransacaoService(alertas::add, (email, corpo) -> emails.add(email), relogio);
    }

    private static Conta conta(String saldo, CategoriaVip categoria) {
        return new Conta("ACC-1", "cliente@email.com", "BR", categoria, new BigDecimal(saldo), true);
    }

    private static List<Transacao> historicoAntigo() {
        List<Transacao> historico = new ArrayList<>();
        historico.add(new Transacao(new BigDecimal("50.00"), AGORA.minusHours(5)));
        historico.add(new Transacao(new BigDecimal("30.00"), AGORA.minusHours(3)));
        return historico;
    }

    private static void assertSaldo(String esperado, Conta conta) {
        assertEquals(0, conta.getSaldo().compareTo(new BigDecimal(esperado)));
    }

    @Test
    void pixAprovadoDebitaEDevolveCashbackNoSaldo() {
        Conta conta = conta("1000.00", CategoriaVip.PADRAO);
        List<Transacao> historico = historicoAntigo();

        ResultadoTransacao resultado = service.processar(
                conta, new BigDecimal("100.00"), MeioPagamento.PIX, "BR", historico, true);

        assertEquals(ResultadoTransacao.APROVADA, resultado);
        assertSaldo("901.00", conta);
        assertEquals(3, historico.size());
        assertEquals(1, emails.size());
    }

    @Test
    void contaGoldTemCashbackComoDesconto() {
        Conta conta = conta("1000.00", CategoriaVip.GOLD);

        ResultadoTransacao resultado = service.processar(
                conta, new BigDecimal("100.00"), MeioPagamento.PIX, "BR", historicoAntigo(), false);

        assertEquals(ResultadoTransacao.APROVADA, resultado);
        assertSaldo("901.00", conta);
        assertTrue(emails.isEmpty());
    }

    @Test
    void creditoCobraTaxaDeCincoPorCento() {
        Conta conta = conta("1000.00", CategoriaVip.PADRAO);

        service.processar(conta, new BigDecimal("100.00"), MeioPagamento.CREDITO, "BR", new ArrayList<>(), false);

        assertSaldo("895.00", conta);
    }

    @Test
    void paisDiferenteBloqueiaPorFraude() {
        Conta conta = conta("1000.00", CategoriaVip.PADRAO);

        ResultadoTransacao resultado = service.processar(
                conta, new BigDecimal("100.00"), MeioPagamento.PIX, "US", historicoAntigo(), true);

        assertEquals(ResultadoTransacao.BLOQUEADA_POR_FRAUDE, resultado);
        assertTrue(conta.isBloqueada());
        assertEquals(1, alertas.size());
        assertSaldo("1000.00", conta);
    }

    @Test
    void maisDeTresTransacoesNaUltimaHoraBloqueiaPorFraude() {
        Conta conta = conta("1000.00", CategoriaVip.PADRAO);
        List<Transacao> historico = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            historico.add(new Transacao(new BigDecimal("10.00"), AGORA.minusMinutes(10)));
        }

        ResultadoTransacao resultado = service.processar(
                conta, new BigDecimal("10.00"), MeioPagamento.PIX, "BR", historico, false);

        assertEquals(ResultadoTransacao.BLOQUEADA_POR_FRAUDE, resultado);
        assertTrue(conta.isBloqueada());
    }

    @Test
    void saldoInsuficienteNaoAlteraSaldoNemHistorico() {
        Conta conta = conta("50.00", CategoriaVip.PADRAO);
        List<Transacao> historico = new ArrayList<>();

        ResultadoTransacao resultado = service.processar(
                conta, new BigDecimal("100.00"), MeioPagamento.PIX, "BR", historico, true);

        assertEquals(ResultadoTransacao.SALDO_INSUFICIENTE, resultado);
        assertSaldo("50.00", conta);
        assertTrue(historico.isEmpty());
    }

    @Test
    void dadosInvalidosOuContaBloqueadaSaoRecusados() {
        Conta conta = conta("1000.00", CategoriaVip.PADRAO);

        assertEquals(ResultadoTransacao.DADOS_INVALIDOS,
                service.processar(conta, BigDecimal.ZERO, MeioPagamento.PIX, "BR", new ArrayList<>(), false));
        assertEquals(ResultadoTransacao.DADOS_INVALIDOS,
                service.processar(null, BigDecimal.TEN, MeioPagamento.PIX, "BR", new ArrayList<>(), false));

        conta.bloquear();
        assertFalse(conta.getSaldo().signum() == 0);
        assertEquals(ResultadoTransacao.DADOS_INVALIDOS,
                service.processar(conta, BigDecimal.TEN, MeioPagamento.PIX, "BR", new ArrayList<>(), false));
    }

    @Test
    void contaSemPaisNaoQuebraEBloqueiaPorFraude() {
        Conta semPais = new Conta("ACC-2", "x@y.com", null, CategoriaVip.PADRAO, new BigDecimal("100.00"), true);

        ResultadoTransacao resultado = service.processar(
                semPais, BigDecimal.TEN, MeioPagamento.PIX, "BR", new ArrayList<>(), false);

        assertEquals(ResultadoTransacao.BLOQUEADA_POR_FRAUDE, resultado);
    }

    @Test
    void emailInvalidoNaoImpedeATransacaoMasNaoNotifica() {
        Conta conta = new Conta("ACC-3", "invalido", "BR", CategoriaVip.PADRAO, new BigDecimal("100.00"), true);

        ResultadoTransacao resultado = service.processar(
                conta, BigDecimal.TEN, MeioPagamento.PIX, "BR", new ArrayList<>(), true);

        assertEquals(ResultadoTransacao.APROVADA, resultado);
        assertTrue(emails.isEmpty());
    }
}
