package com.gc26.injecao.pedidos.services;

import com.gc26.injecao.pedidos.entities.Pedido;

public class EmailNotificacaoService implements NotificacaoService {

    @Override
    public void notificar(Pedido pedido) {
        System.out.println("Notificando pedido " + pedido.numeroPedido() + " via email...");
    }
}
