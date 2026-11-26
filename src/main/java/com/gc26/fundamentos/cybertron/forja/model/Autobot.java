package com.gc26.fundamentos.cybertron.forja.model;

public class Autobot {

    private final String nome;
    private double energon;

    public Autobot(String nome, double energon) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O Autobot precisa de um nome.");
        }
        if (energon < 0) {
            throw new IllegalArgumentException("O Energon inicial não pode ser negativo.");
        }
        this.nome = nome;
        this.energon = energon;
    }

    public String getNome() {
        return nome;
    }

    public double getEnergon() {
        return energon;
    }

    public void adicionarEnergon(double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de Energon deve ser positiva.");
        }
        this.energon += quantidade;
    }
}
