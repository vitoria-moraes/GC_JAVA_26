package com.gc26.fundamentos.stardew;

import java.util.List;
import java.util.Scanner;

public class StardewApp {

    private static final int SAIR = 3;

    public static void main(String[] args) {
        List<Animal> curral = List.of(
                new VacaMae("Mimosa", 7, "Sem filhotes"),
                new GalinhaMae("Giselda", 1, "Botando ovos"),
                new Bezerro("Malhado", 0, "Crescendo"),
                new Pintinho("Piu", 0, "Crescendo"));

        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            while (opcao != SAIR) {
                exibirMenu();
                if (!scanner.hasNext()) {
                    break;
                }
                if (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Digite um número válido.");
                    continue;
                }
                opcao = scanner.nextInt();
                executar(opcao, curral);
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Ver animais do curral");
        System.out.println("2. Coletar produtos");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void executar(int opcao, List<Animal> curral) {
        System.out.println();
        switch (opcao) {
            case 1 -> {
                System.out.println("ANIMAIS NO CURRAL:");
                for (Animal animal : curral) {
                    System.out.println(animal.detalhes());
                    System.out.println(" - Som: " + animal.emitirSom());
                    System.out.println();
                }
            }
            case 2 -> {
                System.out.println("COLETANDO PRODUTOS:");
                curral.forEach(animal -> System.out.println(animal.coletarProduto()));
            }
            case SAIR -> System.out.println("Saindo do jogo. Bom trabalho na fazenda!");
            default -> System.out.println("Opção inválida!");
        }
    }
}
