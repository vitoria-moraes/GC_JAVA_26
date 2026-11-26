package com.gc26.fundamentos.cybertron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gc26.fundamentos.cybertron.forja.model.Autobot;
import com.gc26.fundamentos.cybertron.forja.repository.AutobotRepository;
import com.gc26.fundamentos.cybertron.forja.service.AutobotService;

class ForjaTest {

    @Test
    void cadastraEListaAutobots() {
        AutobotService service = new AutobotService(new AutobotRepository());

        service.cadastrar("Optimus Prime", 850.0);
        service.cadastrar("Bumblebee", 320.0);

        assertEquals(2, service.listarTodos().size());
    }

    @Test
    void naoCadastraNomeRepetido() {
        AutobotService service = new AutobotService(new AutobotRepository());
        service.cadastrar("Bumblebee", 320.0);

        assertThrows(IllegalStateException.class, () -> service.cadastrar("Bumblebee", 100.0));
        assertEquals(1, service.listarTodos().size());
    }

    @Test
    void validaDadosDoAutobot() {
        assertThrows(IllegalArgumentException.class, () -> new Autobot(" ", 10.0));
        assertThrows(IllegalArgumentException.class, () -> new Autobot("Ratchet", -1.0));
        Autobot ratchet = new Autobot("Ratchet", 10.0);
        assertThrows(IllegalArgumentException.class, () -> ratchet.adicionarEnergon(0));
        ratchet.adicionarEnergon(5.0);
        assertEquals(15.0, ratchet.getEnergon(), 0.001);
    }
}
