package com.gc26.fundamentos.revisaocodigo.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckoutService {

    private static final BigDecimal MINIMO_PARA_CASHBACK = new BigDecimal("100.00");
    private static final BigDecimal TAXA_CASHBACK = new BigDecimal("0.05");

    public StatusCompra fecharCompra(Cliente cliente, BigDecimal valorCompra, String cupom) {
        if (cliente == null || !cliente.isAtivo() || valorCompra == null || valorCompra.signum() <= 0) {
            return StatusCompra.RECUSADA;
        }

        BigDecimal total = valorCompra;
        if (cupom != null) {
            BigDecimal fator = fatorDoCupom(cupom);
            if (fator == null) {
                return StatusCompra.RECUSADA;
            }
            total = total.multiply(fator);
        }

        BigDecimal cashback = total.compareTo(MINIMO_PARA_CASHBACK) >= 0
                ? total.multiply(TAXA_CASHBACK).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        boolean cashbackComoDesconto = cliente.getNivel() == NivelFidelidade.OURO;
        if (cashbackComoDesconto) {
            total = total.subtract(cashback);
        }
        total = total.setScale(2, RoundingMode.HALF_UP);

        if (cliente.getSaldo().compareTo(total) < 0) {
            return StatusCompra.SALDO_INSUFICIENTE;
        }

        cliente.debitar(total);
        if (!cashbackComoDesconto) {
            cliente.creditar(cashback);
        }
        System.out.println("Compra aprovada para " + cliente.getNome() + ". Total pago: " + total);
        return StatusCompra.APROVADA;
    }

    private BigDecimal fatorDoCupom(String cupom) {
        return switch (cupom) {
            case "DESC10" -> new BigDecimal("0.90");
            case "DESC20" -> new BigDecimal("0.80");
            default -> null;
        };
    }
}
