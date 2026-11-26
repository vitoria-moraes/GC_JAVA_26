package com.gc26.injecao.pedidos.entities;

public record Pedido(long numeroPedido, Produto produto, int preco) {

    public Pedido {
        if (numeroPedido <= 0) {
            throw new IllegalArgumentException("O número do pedido deve ser positivo.");
        }
        if (produto == null) {
            throw new IllegalArgumentException("O pedido precisa de um produto.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
    }
}
