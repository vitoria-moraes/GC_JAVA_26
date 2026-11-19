package org.cybertron.autobot;

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
     * Soma o Energon acumulado de todas as unidades registradas para monitorar
     * a reserva energética total da base.
     *
     * @return soma total de Energon dos Autobots cadastrados
     */
    public double calcularEnergonTotal() {
        double total = 0;
        for (int i = 0; i < autobots.size(); i++) {
            total += autobots.get(i).getEnergon();
        }
        return total;
    }

    /**
     * Localiza um Autobot pelo nome exato utilizando comparação de conteúdo textual,
     * evitando falhas de referência de memória em strings instanciadas dinamicamente.
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
     * Executa o protocolo de reparo acrescentando uma carga padrão de Energon ao Autobot.
     * Caso o saldo atual esteja corrompido (negativo), a operação é interrompida com exceção
     * explícita para evitar mascarar falhas críticas de integridade.
     *
     * @param autobot unidade que receberá a recarga de manutenção
     * @throws IllegalArgumentException se o Autobot for nulo ou possuir Energon negativo
     */
    public void reparar(Autobot autobot) {
        if (autobot == null) {
            throw new IllegalArgumentException("Autobot inválido para reparo.");
        }
        if (autobot.getEnergon() < 0) {
            throw new IllegalArgumentException(
                    "Falha no reparo: Energon negativo detectado para " + autobot.getNome()
            );
        }
        autobot.setEnergon(autobot.getEnergon() + RECARGA_REPARO_ENERGON);
    }

    /**
     * Define a categoria operacional do Autobot com base em sua reserva atual de Energon
     * para alocação em missões: acima de 500 é ELITE, acima de 200 é PADRAO e os demais
     * permanecem em RESERVA.
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
        } else if (energonAtual > LIMITE_ENERGON_PADRAO) {
            return "PADRAO";
        } else {
            return "RESERVA";
        }
    }
}