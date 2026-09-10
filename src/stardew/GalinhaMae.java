package stardew;

import interfaces.Animal;

public class GalinhaMae implements Animal {
    protected String nome;
    protected int idade;
    protected String status;

    public GalinhaMae(String nome, int idade, String status) {
        this.nome = nome;
        this.idade = idade;
        this.status = status;
    }

    @Override
    public void emitirSom() {
        System.out.println("Có-có!");
    }

    @Override
    public void coletarProduto() {
        System.out.println("Você coletou um ovo de " + nome + "!");
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("[Galinha] Nome: " + nome + " | Idade: " + idade + " ano(s) | Status: " + status);
    }
}
