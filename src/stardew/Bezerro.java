package stardew;

public class Bezerro extends VacaMae {
    public Bezerro(String nome, int idade, String status) {
        super(nome, idade, status);
    }

    @Override
    public void coletarProduto() {
        System.out.println(nome + " é um bezerro filhote e ainda não produz leite.");
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("[Bezerro] Nome: " + nome + " | Idade: " + idade + " anos | Status: " + status);
    }
}
