package com.gc26.fundamentos.cybertron.autobot;

import java.util.ArrayList;
import java.util.List;

/**
 * Centraliza o cadastro, busca, manutenção de energia e classificação operacional
 * dos Autobots ativos na base.
 */
public class GerenciadorAutobots {

    private static final double RECARGA_REPARO_ENERGON = 100.0;
    private static final double LIMITE_ENERGON_ELITE = 500.0;
    private static final double LIMITE_ENERGON_PADRAO = 200.0;

    private final List<Autobot> autobots = new ArrayList<>();

    /**
     * Registra um novo Autobot na lista de unidades ativas da base.
     *
     * @param autobot unidade a ser adicionada ao gerenciamento
     * @throws IllegalArgumentException se o Autobot informado for nulo
     */
    public void adicionar(Autobot autobot) {
        if (autobot == null) {
            throw new IllegalArgumentException("Não é possível adicionar um Autobot nulo.");
        }
        autobots.add(autobot);
    }

    /**
     * Soma o Energon acumulado de todas as unidades registradas.
     *
     * @return soma total de Energon dos Autobots cadastrados
     */
    public double calcularEnergonTotal() {
        double total = 0;
        for (Autobot autobot : autobots) {
            total += autobot.getEnergon();
        }
        return total;
    }

    /**
     * Localiza um Autobot pelo nome exato, comparando o conteúdo do texto.
     *
     * @param nome nome exato do Autobot procurado
     * @return o {@link Autobot} correspondente, ou {@code null} caso não esteja na base
     */
    public Autobot buscar(String nome) {
        if (nome == null) {
            return null;
        }
        for (Autobot autobot : autobots) {
            if (autobot.getNome().equals(nome)) {
                return autobot;
            }
        }
        return null;
    }

    /**
     * Acrescenta uma carga padrão de Energon ao Autobot.
     *
     * @param autobot unidade que receberá a recarga de manutenção
     * @throws IllegalArgumentException se o Autobot for nulo
     */
    public void reparar(Autobot autobot) {
        if (autobot == null) {
            throw new IllegalArgumentException("Autobot inválido para reparo.");
        }
        autobot.setEnergon(autobot.getEnergon() + RECARGA_REPARO_ENERGON);
    }

    /**
     * Define a categoria operacional do Autobot: acima de 500 é ELITE,
     * acima de 200 é PADRAO e os demais permanecem em RESERVA.
     *
     * @param autobot unidade a ser avaliada
     * @return classificação operacional ("ELITE", "PADRAO" ou "RESERVA")
     * @throws IllegalArgumentException se o Autobot informado for nulo
     */
    public String classificar(Autobot autobot) {
        if (autobot == null) {
            throw new IllegalArgumentException("Não é possível classificar um Autobot nulo.");
        }
        double energonAtual = autobot.getEnergon();
        if (energonAtual > LIMITE_ENERGON_ELITE) {
            return "ELITE";
        }
        if (energonAtual > LIMITE_ENERGON_PADRAO) {
            return "PADRAO";
        }
        return "RESERVA";
    }
}
