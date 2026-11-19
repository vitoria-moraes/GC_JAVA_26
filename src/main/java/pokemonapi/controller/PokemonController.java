package pokemonapi.controller;

import pokemonapi.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final List<Pokemon> pokemonList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public PokemonController() {
        // Dados iniciais de teste
        pokemonList.add(new Pokemon(idCounter.getAndIncrement(), "Pikachu", "Elétrico"));
        pokemonList.add(new Pokemon(idCounter.getAndIncrement(), "Charmander", "Fogo"));
        pokemonList.add(new Pokemon(idCounter.getAndIncrement(), "Bulbasaur", "Planta"));
        pokemonList.add(new Pokemon(idCounter.getAndIncrement(), "Squirtle", "Água"));
    }

    // 1. GET - Listar todos com limite máximo de 50
    @GetMapping
    public ResponseEntity<List<Pokemon>> listarTodos(@RequestParam(defaultValue = "50") int limite) {
        int limiteEfetivo = Math.min(limite, 50);
        int tamanhoSublista = Math.min(pokemonList.size(), limiteEfetivo);

        List<Pokemon> resultado = pokemonList.subList(0, tamanhoSublista);
        return ResponseEntity.ok(resultado);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> buscarPorId(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return pokemon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 2. POST - Criar um novo Pokémon
    @PostMapping
    public ResponseEntity<Pokemon> criar(@RequestBody Pokemon pokemon) {
        pokemon.setId(idCounter.getAndIncrement());
        pokemonList.add(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
    }

    // 3. PUT - Atualizar um Pokémon existente
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> atualizar(@PathVariable Long id, @RequestBody Pokemon pokemonAtualizado) {
        for (Pokemon p : pokemonList) {
            if (p.getId().equals(id)) {
                p.setNome(pokemonAtualizado.getNome());
                p.setTipo(pokemonAtualizado.getTipo());
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // 4. DELETE - Eliminar um Pokémon por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = pokemonList.removeIf(p -> p.getId().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}