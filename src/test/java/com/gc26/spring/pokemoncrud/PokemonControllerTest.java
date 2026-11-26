package com.gc26.spring.pokemoncrud;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listaRespeitaOLimiteInformado() throws Exception {
        mockMvc.perform(get("/pokemons").param("limite", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void buscaPorIdExistenteRetornaOPokemon() throws Exception {
        mockMvc.perform(get("/pokemons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pikachu"));
    }

    @Test
    void buscaPorIdInexistenteRetorna404() throws Exception {
        mockMvc.perform(get("/pokemons/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void criarRetorna201ComONovoPokemon() throws Exception {
        mockMvc.perform(post("/pokemons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Eevee\",\"tipo\":\"Normal\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Eevee"));
    }

    @Test
    void atualizarAlteraNomeETipo() throws Exception {
        mockMvc.perform(put("/pokemons/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Charmeleon\",\"tipo\":\"Fogo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Charmeleon"));
    }

    @Test
    void deletarRemoveEDepoisNaoEncontra() throws Exception {
        mockMvc.perform(delete("/pokemons/4")).andExpect(status().isNoContent());
        mockMvc.perform(delete("/pokemons/4")).andExpect(status().isNotFound());
    }

    @Test
    void criarSemNomeRetorna400ComMensagem() throws Exception {
        mockMvc.perform(post("/pokemons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\" \",\"tipo\":\"Normal\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Nome e tipo são obrigatórios."));
    }

    @Test
    void buscaInexistenteDevolveMensagemDeErro() throws Exception {
        mockMvc.perform(get("/pokemons/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value("Pokémon 9999 não encontrado"));
    }
}
