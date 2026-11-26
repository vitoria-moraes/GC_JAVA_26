package com.gc26.fundamentos.oldsteve.procedural;

public class PicaretaProcedural {

    private static final int LIMITE_PADRAO = 60;

    public static String fabricarPicareta(String material, int durabilidade, int forca) {
        System.out.println("\n=== FABRICANDO PICARETA ===");
        System.out.println("Material: " + material);
        System.out.println("Durabilidade: " + durabilidade);
        System.out.println("Força: " + forca);

        if (durabilidade <= 0 || forca <= 0) {
            System.out.println("Picareta inválida!");
            return null;
        }

        System.out.println("Picareta de " + material + " fabricada com sucesso!");
        return descrever(material, durabilidade, forca);
    }

    public static int minerar(String material, int durabilidade, int forca, int blocos) {
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
        int durabilidadeRestante = durabilidade - blocosMinerados;

        if (blocosMinerados < blocos) {
            System.out.println("Picareta quebrou no bloco " + (blocosMinerados + 1));
        }
        System.out.println("\nMinerados " + blocosMinerados + " blocos");
        System.out.println("Durabilidade restante: " + durabilidadeRestante);

        if (durabilidadeRestante == 0) {
            System.out.println("A picareta de " + material + " quebrou completamente!");
        }
        return durabilidadeRestante;
    }

    public static int repararPicareta(String material, int durabilidadeAtual, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de reparo não pode ser negativa.");
        }
        System.out.println("\n=== REPARANDO PICARETA DE " + material.toUpperCase() + " ===");
        System.out.println("Durabilidade atual: " + durabilidadeAtual);
        System.out.println("Quantidade a reparar: " + quantidade);

        int limite = limiteMaximo(material);
        int durabilidadeNova = Math.min(durabilidadeAtual + quantidade, limite);
        if (durabilidadeAtual + quantidade > limite) {
            System.out.println("Reparo limitado a " + limite);
        }

        System.out.println("Durabilidade nova: " + durabilidadeNova);
        return durabilidadeNova;
    }

    public static int limiteMaximo(String material) {
        return switch (material.toLowerCase()) {
            case "madeira" -> 50;
            case "pedra" -> 70;
            case "ferro" -> 100;
            case "ouro" -> 30;
            default -> LIMITE_PADRAO;
        };
    }

    private static String descrever(String material, int durabilidade, int forca) {
        return "Picareta de " + material + " (D: " + durabilidade + ", F: " + forca + ")";
    }

    public static void main(String[] args) {
        System.out.println("VELHO STEVE - FÁBRICA DE PICARETAS MANUAL");

        String p1 = fabricarPicareta("madeira", 50, 5);
        String p2 = fabricarPicareta("pedra", 70, 8);
        String p3 = fabricarPicareta("ferro", 100, 12);
        int ferroDurabilidade = 100;

        System.out.println("\nPicaretas fabricadas:");
        System.out.println("  - " + p1);
        System.out.println("  - " + p2);
        System.out.println("  - " + p3);

        System.out.println("\nUsando a picareta de ferro para minerar...");
        ferroDurabilidade = minerar("ferro", ferroDurabilidade, 12, 30);
        ferroDurabilidade = minerar("ferro", ferroDurabilidade, 12, 20);

        System.out.println("\nCriando picareta de ouro...");
        String p4 = fabricarPicareta("ouro", 30, 15);

        System.out.println("\nReparando a picareta de ferro...");
        ferroDurabilidade = repararPicareta("ferro", ferroDurabilidade, 50);

        System.out.println("\nEstado final das picaretas:");
        System.out.println("  - " + p1);
        System.out.println("  - " + p2);
        System.out.println("  - " + p3 + " (durabilidade: " + ferroDurabilidade + ")");
        System.out.println("  - " + p4);
    }
}
