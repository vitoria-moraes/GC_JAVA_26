package stardew;

import interfaces.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StardewManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> curral = new ArrayList<>();

        curral.add(new VacaMae("Mimosa", 7, "Sem filhotes"));
        curral.add(new GalinhaMae("Giselda", 1, "Botando ovos"));
        curral.add(new Bezerro("Malhado", 0, "Crescendo"));
        curral.add(new Pintinho("Piu", 0, "Crescendo"));

        int opcao = 0;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ver animais do curral");
            System.out.println("2. Coletar produtos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                System.out.println();

                switch (opcao) {
                    case 1:
                        System.out.println("🐾 ANIMAIS NO CURRAL:");
                        for (Animal a : curral) {
                            a.mostrarDetalhes();
                            System.out.print(" - Som: ");
                            a.emitirSom();
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println(" COLETANDO PRODUTOS:");
                        for (Animal a : curral) {
                            a.coletarProduto();
                        }
                        break;
                    case 3:
                        System.out.println("Saindo do jogo. Bom trabalho na fazenda!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } else {
                scanner.next();
                System.out.println("Digite um número válido.");
            }
        } while (opcao != 3);

        scanner.close();
    }
}
