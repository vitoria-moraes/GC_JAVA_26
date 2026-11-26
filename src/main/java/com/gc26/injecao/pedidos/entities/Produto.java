package com.gc26.injecao.pedidos.entities;

public record Produto(String nome, String categoria) {

    public Produto {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O produto precisa de um nome.");
        }
    }
}
