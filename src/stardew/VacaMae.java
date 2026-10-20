package stardew;

import interfaces.Animal;

public class VacaMae implements Animal {
    protected String nome;
    protected int idade;
    protected String status;

    public VacaMae(String nome, int idade, String status) {
        this.nome = nome;
        this.idade = idade;
        this.status = status;
    }

    @Override
    public void emitirSom() {
        System.out.println("Muuu!");
    }

    @Override
    public void coletarProduto() {
        System.out.println("Você coletou leite fresco de " + nome + "!");
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("[Vaca] Nome: " + nome + " | Idade: " + idade + " anos | Status: " + status);
    }
}