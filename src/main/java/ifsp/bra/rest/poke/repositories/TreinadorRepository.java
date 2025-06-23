package ifsp.bra.rest.poke.repositories;

import ifsp.bra.rest.poke.models.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
    List<Treinador> findByRegiaoId(Long regiaoId);
    List<Treinador> findByNomeContaining(String nome);
}
