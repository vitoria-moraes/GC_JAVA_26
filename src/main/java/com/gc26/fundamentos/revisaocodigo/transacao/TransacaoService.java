package com.gc26.fundamentos.revisaocodigo.transacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TransacaoService {

    private static final int LIMITE_TRANSACOES_POR_HORA = 3;
    private static final int MINIMO_HISTORICO_PARA_CASHBACK = 2;
    private static final BigDecimal TAXA_CASHBACK = new BigDecimal("0.01");

    private final Auditoria auditoria;
    private final Notificador notificador;
    private final Clock relogio;

    public TransacaoService(Auditoria auditoria, Notificador notificador, Clock relogio) {
        this.auditoria = auditoria;
        this.notificador = notificador;
        this.relogio = relogio;
    }

    public ResultadoTransacao processar(Conta conta, BigDecimal valor, MeioPagamento meio,
                                        String paisOrigem, List<Transacao> historico, boolean notificar) {
        if (conta == null || valor == null || valor.signum() <= 0 || meio == null || historico == null
                || !conta.isAtiva() || conta.isBloqueada()) {
            return ResultadoTransacao.DADOS_INVALIDOS;
        }

        LocalDateTime agora = LocalDateTime.now(relogio);
        if (transacoesNaUltimaHora(historico, agora) > LIMITE_TRANSACOES_POR_HORA
                || !Objects.equals(conta.getPais(), paisOrigem)) {
            conta.bloquear();
            auditoria.registrar("ALERTA: conta " + conta.getId() + " bloqueada por fraude!");
            return ResultadoTransacao.BLOQUEADA_POR_FRAUDE;
        }

        BigDecimal total = meio.aplicarTaxa(valor);

        BigDecimal cashback = historico.size() >= MINIMO_HISTORICO_PARA_CASHBACK
                ? total.multiply(TAXA_CASHBACK).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        boolean cashbackComoDesconto = conta.getCategoria() == CategoriaVip.GOLD;
        if (cashbackComoDesconto) {
            total = total.subtract(cashback);
        }
        total = total.setScale(2, RoundingMode.HALF_UP);

        if (conta.getSaldo().compareTo(total) < 0) {
            return ResultadoTransacao.SALDO_INSUFICIENTE;
        }

        conta.debitar(total);
        if (!cashbackComoDesconto) {
            conta.creditar(cashback);
        }
        historico.add(new Transacao(valor, agora));

        if (notificar && emailValido(conta.getEmail())) {
            notificador.enviarEmail(conta.getEmail(), "Transação de " + total + " aprovada.");
        }
        return ResultadoTransacao.APROVADA;
    }

    private long transacoesNaUltimaHora(List<Transacao> historico, LocalDateTime agora) {
        LocalDateTime limite = agora.minusHours(1);
        return historico.stream().filter(t -> t.data().isAfter(limite)).count();
    }

    private boolean emailValido(String email) {
        return email != null && email.contains("@");
    }
}
