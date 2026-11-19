package org.cybertron.autobot;

/**
 * Representa uma unidade Autobot registrada na base de operações de Cybertron,
 * mantendo o controle encapsulado de sua reserva de Energon e nível de combate.
 */
public class Autobot {
    private String nome;
    private double energon;
    private int nivel;

    /**
     * Cria uma nova instância de Autobot com seus atributos operacionais iniciais.
     *
     * @param nome    identificação única do Autobot na base
     * @param energon quantidade inicial de energia disponível (não deve ser negativa)
     * @param nivel   nível de experiência e capacidade de combate
     */
    public Autobot(String nome, double energon, int nivel) {
        this.nome = nome;
        this.energon = energon;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public double getEnergon() {
        return energon;
    }

    /**
     * Atualiza a reserva de Energon do Autobot garantindo que o saldo não fique negativo.
     *
     * @param energon novo valor de Energon da unidade
     * @throws IllegalArgumentException caso o valor informado seja menor que zero
     */
    public void setEnergon(double energon) {
        if (energon < 0) {
            throw new IllegalArgumentException("O Energon do Autobot não pode ser negativo.");
        }
        this.energon = energon;
    }

    public int getNivel() {
        return nivel;
    }
}