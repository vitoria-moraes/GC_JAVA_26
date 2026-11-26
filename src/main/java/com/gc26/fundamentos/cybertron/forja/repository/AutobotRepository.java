package com.gc26.fundamentos.cybertron.forja.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gc26.fundamentos.cybertron.forja.model.Autobot;

public class AutobotRepository {

    private final List<Autobot> autobots = new ArrayList<>();

    public void salvar(Autobot autobot) {
        autobots.add(autobot);
    }

    public List<Autobot> listarTodos() {
        return List.copyOf(autobots);
    }

    public Optional<Autobot> buscarPorNome(String nome) {
        return autobots.stream().filter(a -> a.getNome().equals(nome)).findFirst();
    }
}
