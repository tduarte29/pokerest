package ifsp.bra.rest.poke.repositories;

import ifsp.bra.rest.poke.models.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
    Movimento findByNome(String nome);
    List<Movimento> findByTipo(String tipo);
    List<Movimento> findByCategoria(String categoria);
}
