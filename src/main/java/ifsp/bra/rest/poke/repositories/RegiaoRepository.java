package ifsp.bra.rest.poke.repositories;

import ifsp.bra.rest.poke.models.*;

import ifsp.bra.rest.poke.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
    Regiao findByNome(String nome);
}
