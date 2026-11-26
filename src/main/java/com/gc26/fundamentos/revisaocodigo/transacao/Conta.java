package com.gc26.fundamentos.revisaocodigo.transacao;

import java.math.BigDecimal;
import java.util.Objects;

public class Conta {

    private final String id;
    private final String email;
    private final String pais;
    private final CategoriaVip categoria;
    private final boolean ativa;
    private BigDecimal saldo;
    private boolean bloqueada;

    public Conta(String id, String email, String pais, CategoriaVip categoria, BigDecimal saldo, boolean ativa) {
        this.id = id;
        this.email = email;
        this.pais = pais;
        this.categoria = categoria == null ? CategoriaVip.PADRAO : categoria;
        this.saldo = Objects.requireNonNull(saldo, "O saldo é obrigatório.");
        this.ativa = ativa;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPais() {
        return pais;
    }

    public CategoriaVip getCategoria() {
        return categoria;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void bloquear() {
        bloqueada = true;
    }

    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }
}
