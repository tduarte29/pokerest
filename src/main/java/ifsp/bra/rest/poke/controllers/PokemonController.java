package ifsp.bra.rest.poke.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifsp.bra.rest.poke.dto.PokemonDTO;
import ifsp.bra.rest.poke.models.Movimento;
import ifsp.bra.rest.poke.models.Pokemon;
import ifsp.bra.rest.poke.service.MovimentoService;
import ifsp.bra.rest.poke.service.PokemonService;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Autowired
    private MovimentoService movimentoService;

    @GetMapping
    public List<PokemonDTO> getAllPokemons() {
        return pokemonService.findAll().stream()
            .map(PokemonDTO::toDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable Long id) {
        return pokemonService.findById(id)
            .map(PokemonDTO::toDTO)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PokemonDTO createPokemon(@RequestBody Pokemon pokemon) {
        return PokemonDTO.toDTO(pokemonService.save(pokemon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemonDetails) {
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
        return ResponseEntity.ok(PokemonDTO.toDTO(updatedPokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/treinador/{treinadorId}")
    public List<PokemonDTO> getPokemonsByTreinador(@PathVariable Long treinadorId) {
        return pokemonService.findByTreinadorId(treinadorId)
            .stream()
            .map(PokemonDTO::toDTO)
            .toList();
    }

    @GetMapping("/buscar")
    public List<PokemonDTO> searchPokemons(@RequestParam String nome) {
        return pokemonService.findByNomeContaining(nome)
            .stream()
            .map(PokemonDTO::toDTO)
            .toList();
    }

    @GetMapping("/pokedex/{numPokedex}")
    public List<PokemonDTO> getPokemonsByPokedexNum(@PathVariable Integer numPokedex) {
        return pokemonService.findByNumPokedex(numPokedex)
            .stream()
            .map(PokemonDTO::toDTO)
            .toList();
    }

    @PutMapping("/{pokemonId}/movimentos/{movimentoId}")
    public ResponseEntity<PokemonDTO> addMovimentoToPokemon(@PathVariable Long pokemonId, @PathVariable Long movimentoId) {
        Optional<Pokemon> pokemonOpt = pokemonService.findById(pokemonId);
        Optional<Movimento> movimentoOpt = movimentoService.findById(movimentoId);

        if (!pokemonOpt.isPresent() || !movimentoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Pokemon pokemon = pokemonOpt.get();
        Movimento movimento = movimentoOpt.get();

        pokemon.addMovimento(movimento);
        Pokemon updatedPokemon = pokemonService.save(pokemon);

        // Retorne o DTO para evitar loop
        return ResponseEntity.ok(PokemonDTO.toDTO(updatedPokemon));
    }
}
