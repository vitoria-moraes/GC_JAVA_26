package com.gc26.fundamentos.stardew;

public abstract class AnimalDeFazenda implements Animal {

    private final String nome;
    private final int idade;
    private final String status;

    protected AnimalDeFazenda(String nome, int idade, String status) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O animal precisa de um nome.");
        }
        if (idade < 0) {
            throw new IllegalArgumentException("A idade não pode ser negativa.");
        }
        this.nome = nome;
        this.idade = idade;
        this.status = status;
    }

    protected abstract String tipo();

    protected String getNome() {
        return nome;
    }

    @Override
    public String detalhes() {
        return "[" + tipo() + "] Nome: " + nome + " | Idade: " + idade + " ano(s) | Status: " + status;
    }
}
