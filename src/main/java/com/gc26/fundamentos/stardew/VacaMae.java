package com.gc26.fundamentos.stardew;

public class VacaMae extends AnimalDeFazenda {

    public VacaMae(String nome, int idade, String status) {
        super(nome, idade, status);
    }

    @Override
    protected String tipo() {
        return "Vaca";
    }

    @Override
    public String emitirSom() {
        return "Muuu!";
    }

    @Override
    public String coletarProduto() {
        return "Você coletou leite fresco de " + getNome() + "!";
    }
}
