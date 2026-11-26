package com.gc26.fundamentos.stardew;

public class GalinhaMae extends AnimalDeFazenda {

    public GalinhaMae(String nome, int idade, String status) {
        super(nome, idade, status);
    }

    @Override
    protected String tipo() {
        return "Galinha";
    }

    @Override
    public String emitirSom() {
        return "Có-có!";
    }

    @Override
    public String coletarProduto() {
        return "Você coletou um ovo de " + getNome() + "!";
    }
}
