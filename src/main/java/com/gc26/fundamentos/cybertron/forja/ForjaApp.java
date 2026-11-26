package com.gc26.fundamentos.cybertron.forja;

import com.gc26.fundamentos.cybertron.forja.repository.AutobotRepository;
import com.gc26.fundamentos.cybertron.forja.service.AutobotService;

public class ForjaApp {

    public static void main(String[] args) {
        AutobotRepository repository = new AutobotRepository();
        AutobotService service = new AutobotService(repository);

        service.cadastrar("Optimus Prime", 850.0);
        service.cadastrar("Bumblebee", 320.0);

        service.listarTodos().forEach(a ->
                System.out.println(a.getNome() + " - " + a.getEnergon()));
    }
}
