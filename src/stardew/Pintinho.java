package stardew;

public class Pintinho extends GalinhaMae {
    public Pintinho(String nome, int idade, String status) {
        super(nome, idade, status);
    }

    @Override
    public void coletarProduto() {
        System.out.println(nome + " é um pintinho e ainda não bota ovos.");
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("[Pintinho] Nome: " + nome + " | Idade: " + idade + " ano(s) | Status: " + status);
    }
}
