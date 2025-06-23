package ifsp.bra.rest.poke.repositories;

import ifsp.bra.rest.poke.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByTreinadorId(Long treinadorId);
    List<Pokemon> findByNomeContaining(String nome);
    List<Pokemon> findByNumPokedex(Integer numPokedex);
}
