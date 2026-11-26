package com.gc26.fundamentos.oldsteve.oo;

public abstract class Picareta {

    private final String material;
    private final int forca;
    private final int limiteMaximo;
    private int durabilidade;

    protected Picareta(String material, int durabilidade, int forca, int limiteMaximo) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("A picareta precisa de um material.");
        }
        if (durabilidade <= 0 || forca <= 0 || limiteMaximo < durabilidade) {
            throw new IllegalArgumentException("Durabilidade, força e limite precisam ser válidos.");
        }
        this.material = material;
        this.durabilidade = durabilidade;
        this.forca = forca;
        this.limiteMaximo = limiteMaximo;

        System.out.println("\n=== FABRICANDO PICARETA ===");
        System.out.println("Material: " + material);
        System.out.println("Durabilidade: " + durabilidade);
        System.out.println("Força: " + forca);
        System.out.println("Picareta de " + material + " fabricada com sucesso!");
    }

    public int minerar(int blocos) {
        if (blocos < 0) {
            throw new IllegalArgumentException("A quantidade de blocos não pode ser negativa.");
        }
        System.out.println("\n=== MINERANDO COM PICARETA DE " + material.toUpperCase() + " ===");
        System.out.println("Durabilidade atual: " + durabilidade);
        System.out.println("Força: " + forca);
        System.out.println("Blocos a minerar: " + blocos);

        if (durabilidade <= 0) {
            System.out.println("Picareta quebrada!");
            return 0;
        }

        int blocosMinerados = Math.min(blocos, durabilidade);
        durabilidade -= blocosMinerados;

        if (blocosMinerados < blocos) {
            System.out.println("Picareta quebrou no bloco " + (blocosMinerados + 1));
        }
        System.out.println("\nMinerados " + blocosMinerados + " blocos");
        System.out.println("Durabilidade restante: " + durabilidade);

        if (durabilidade == 0) {
            System.out.println("A picareta de " + material + " quebrou completamente!");
        }
        return blocosMinerados;
    }

    public void reparar(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de reparo não pode ser negativa.");
        }
        System.out.println("\n=== REPARANDO PICARETA DE " + material.toUpperCase() + " ===");
        System.out.println("Durabilidade atual: " + durabilidade);
        System.out.println("Quantidade a reparar: " + quantidade);

        if (durabilidade + quantidade > limiteMaximo) {
            System.out.println("Reparo limitado a " + limiteMaximo);
            durabilidade = limiteMaximo;
        } else {
            durabilidade += quantidade;
        }

        System.out.println("Durabilidade nova: " + durabilidade);
    }

    public String getMaterial() {
        return material;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public int getForca() {
        return forca;
    }

    @Override
    public String toString() {
        return "Picareta de " + material + " (D: " + durabilidade + ", F: " + forca + ")";
    }
}
