package ifsp.bra.rest.poke.controllers;

import ifsp.bra.rest.poke.models.Pokemon;
import ifsp.bra.rest.poke.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemonDetails) {
        Optional<Pokemon> pokemonOptional = pokemonService.findById(id);
        if (!pokemonOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Pokemon pokemon = pokemonOptional.get();
        pokemon.setNome(pokemonDetails.getNome());
        pokemon.setNumPokedex(pokemonDetails.getNumPokedex());
        pokemon.setDescricao(pokemonDetails.getDescricao());
        pokemon.setTreinador(pokemonDetails.getTreinador());
        Pokemon updatedPokemon = pokemonService.save(pokemon);
        return ResponseEntity.ok(updatedPokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/treinador/{treinadorId}")
    public List<Pokemon> getPokemonsByTreinador(@PathVariable Long treinadorId) {
        return pokemonService.findByTreinadorId(treinadorId);
    }

    @GetMapping("/buscar")
    public List<Pokemon> searchPokemons(@RequestParam String nome) {
        return pokemonService.findByNomeContaining(nome);
    }

    @GetMapping("/pokedex/{numPokedex}")
    public List<Pokemon> getPokemonsByPokedexNum(@PathVariable Integer numPokedex) {
        return pokemonService.findByNumPokedex(numPokedex);
    }
}
