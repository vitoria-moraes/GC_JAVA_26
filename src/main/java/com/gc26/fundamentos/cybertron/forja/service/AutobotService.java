package com.gc26.fundamentos.cybertron.forja.service;

import java.util.List;

import com.gc26.fundamentos.cybertron.forja.model.Autobot;
import com.gc26.fundamentos.cybertron.forja.repository.AutobotRepository;

public class AutobotService {

    private final AutobotRepository repository;

    public AutobotService(AutobotRepository repository) {
        this.repository = repository;
    }

    public Autobot cadastrar(String nome, double energon) {
        Autobot autobot = new Autobot(nome, energon);
        if (repository.buscarPorNome(nome).isPresent()) {
            throw new IllegalStateException("Já existe um Autobot chamado " + nome);
        }
        repository.salvar(autobot);
        return autobot;
    }

    public List<Autobot> listarTodos() {
        return repository.listarTodos();
    }
}
