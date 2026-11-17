package org.example.atividadebarbieguardaroupas;

/**
 * Sistema do guarda-roupa da Barbie.
 */
public class GuardaRoupa {

    private double orcamento;

    /**
     * Cria o guarda-roupa com um orçamento disponível.
     *
     * @param orcamento valor máximo para gastar em looks
     */
    public GuardaRoupa(double orcamento) {
        this.orcamento = orcamento;
    }

    /**
     * Calcula o preço de um look com desconto.
     *
     * <p>Regra: o desconto deve estar entre 0 e 100.
     * Fora disso, é erro de cadastro e deve avisar.</p>
     *
     * @param preco      preço original do look
     * @param percentual desconto de 0 a 100
     * @return preço final
     */
    public double precoComDesconto(double preco, double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O percentual de desconto deve estar entre 0 e 100.");
        }
        return preco - (preco * percentual / 100);
    }

    /**
     * Diz se um look cabe no orçamento.
     *
     * <p>Regra: o orçamento inclui o valor exato.</p>
     *
     * @param preco preço do look
     * @return true se couber no orçamento
     */
    public boolean cabeNoOrcamento(double preco) {
        return preco <= orcamento;
    }

    /**
     * Diz se um look é de gala.
     *
     * <p>Regra: acima de 200 reais é gala.</p>
     *
     * @param preco preço do look
     * @return true se for de gala
     */
    public boolean ehDeGala(double preco) {
        return preco > 200.0;
    }

    /**
     * Registra uma compra e desconta do orçamento.
     *
     * @param preco valor pago pelo look
     */
    public void registrarCompra(double preco) {
        if (preco > orcamento) {
            throw new IllegalArgumentException("Orçamento insuficiente");
        }
        this.orcamento = this.orcamento - preco;
    }

    /**
     * Devolve o orçamento restante.
     *
     * @return valor ainda disponível
     */
    public double getOrcamento() {
        return orcamento;
    }
}