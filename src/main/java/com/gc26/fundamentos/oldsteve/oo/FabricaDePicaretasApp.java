package com.gc26.fundamentos.oldsteve.oo;

public class FabricaDePicaretasApp {

    public static void main(String[] args) {
        System.out.println("VELHO STEVE - FÁBRICA DE PICARETAS OO");

        Picareta madeira = new PicaretaMadeira();
        Picareta pedra = new PicaretaPedra();
        Picareta ferro = new PicaretaFerro();

        System.out.println("\nPicaretas fabricadas:");
        System.out.println("  - " + madeira);
        System.out.println("  - " + pedra);
        System.out.println("  - " + ferro);

        System.out.println("\nUsando a picareta de ferro para minerar...");
        ferro.minerar(30);
        ferro.minerar(20);

        System.out.println("\nCriando picareta de ouro...");
        Picareta ouro = new PicaretaOuro();

        System.out.println("\nReparando a picareta de ferro...");
        ferro.reparar(50);

        System.out.println("\nEstado final das picaretas:");
        System.out.println("  - " + madeira);
        System.out.println("  - " + pedra);
        System.out.println("  - " + ferro);
        System.out.println("  - " + ouro);
    }
}
