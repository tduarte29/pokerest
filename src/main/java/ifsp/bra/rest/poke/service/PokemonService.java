package ifsp.bra.rest.poke.service;

import ifsp.bra.rest.poke.models.Pokemon;
import ifsp.bra.rest.poke.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final TreinadorService treinadorService;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, TreinadorService treinadorService) {
        this.pokemonRepository = pokemonRepository;
        this.treinadorService = treinadorService;
    }

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> findById(Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon save(Pokemon pokemon) {
        // Ensure the trainer exists
        treinadorService.findById(pokemon.getTreinador().getId())
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));
        return pokemonRepository.save(pokemon);
    }

    public void deleteById(Long id) {
        pokemonRepository.deleteById(id);
    }

    public List<Pokemon> findByTreinadorId(Long treinadorId) {
        return pokemonRepository.findByTreinadorId(treinadorId);
    }

    public List<Pokemon> findByNomeContaining(String nome) {
        return pokemonRepository.findByNomeContaining(nome);
    }

    public List<Pokemon> findByNumPokedex(Integer numPokedex) {
        return pokemonRepository.findByNumPokedex(numPokedex);
    }
}
