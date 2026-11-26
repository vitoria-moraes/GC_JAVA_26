package com.gc26.fundamentos.revisaocodigo.transacao;

import java.math.BigDecimal;

public enum MeioPagamento {
    CREDITO("1.05"),
    DEBITO("1.02"),
    PIX("1.00");

    private final BigDecimal fator;

    MeioPagamento(String fator) {
        this.fator = new BigDecimal(fator);
    }

    public BigDecimal aplicarTaxa(BigDecimal valor) {
        return valor.multiply(fator);
    }
}
