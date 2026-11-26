package com.gc26.fundamentos.revisaocodigo.checkout;

import java.math.BigDecimal;
import java.util.Objects;

public class Cliente {

    private final String nome;
    private final NivelFidelidade nivel;
    private final boolean ativo;
    private BigDecimal saldo;

    public Cliente(String nome, BigDecimal saldo, NivelFidelidade nivel, boolean ativo) {
        this.nome = nome;
        this.saldo = Objects.requireNonNull(saldo, "O saldo é obrigatório.");
        this.nivel = nivel == null ? NivelFidelidade.PADRAO : nivel;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public NivelFidelidade getNivel() {
        return nivel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }
}
