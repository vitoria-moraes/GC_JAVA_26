package com.gc26.fundamentos.revisaocodigo.checkout;

import java.math.BigDecimal;

public class CheckoutApp {

    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService();
        Cliente cliente = new Cliente("Ana Silva", new BigDecimal("500.00"), NivelFidelidade.PADRAO, true);

        System.out.println("Saldo antes: " + cliente.getSaldo());
        StatusCompra status = checkout.fecharCompra(cliente, new BigDecimal("200.00"), "DESC10");

        System.out.println("Status retornado: " + status);
        System.out.println("Saldo depois: " + cliente.getSaldo());
    }
}
