package com.gc26.fundamentos.stardew;

public class Pintinho extends GalinhaMae {

    public Pintinho(String nome, int idade, String status) {
        super(nome, idade, status);
    }

    @Override
    protected String tipo() {
        return "Pintinho";
    }

    @Override
    public String coletarProduto() {
        return getNome() + " é um pintinho e ainda não bota ovos.";
    }
}
