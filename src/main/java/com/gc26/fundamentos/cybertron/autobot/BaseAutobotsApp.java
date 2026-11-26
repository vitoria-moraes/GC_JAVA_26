package com.gc26.fundamentos.cybertron.autobot;

public class BaseAutobotsApp {

    public static void main(String[] args) {
        GerenciadorAutobots gerenciador = new GerenciadorAutobots();

        Autobot optimus = new Autobot("Optimus Prime", 850.0, 10);
        Autobot bumblebee = new Autobot("Bumblebee", 320.0, 5);
        Autobot ratchet = new Autobot("Ratchet", 180.0, 7);

        gerenciador.adicionar(optimus);
        gerenciador.adicionar(bumblebee);
        gerenciador.adicionar(ratchet);

        System.out.println("Energon total: " + gerenciador.calcularEnergonTotal());

        Autobot encontrado = gerenciador.buscar("Bumblebee");
        System.out.println(encontrado != null ? "Encontrado: " + encontrado.getNome() : "Autobot nao encontrado");

        gerenciador.reparar(ratchet);
        System.out.println("Energon do Ratchet: " + ratchet.getEnergon());
        System.out.println("Classificacao: " + gerenciador.classificar(ratchet));
    }
}
