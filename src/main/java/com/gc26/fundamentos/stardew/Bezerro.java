package com.gc26.fundamentos.stardew;

public class Bezerro extends VacaMae {

    public Bezerro(String nome, int idade, String status) {
        super(nome, idade, status);
    }

    @Override
    protected String tipo() {
        return "Bezerro";
    }

    @Override
    public String coletarProduto() {
        return getNome() + " é um bezerro filhote e ainda não produz leite.";
    }
}
