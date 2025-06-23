package ifsp.bra.rest.poke.service;

import ifsp.bra.rest.poke.models.Movimento;
import ifsp.bra.rest.poke.repositories.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentoService {
    private final MovimentoRepository movimentoRepository;

    @Autowired
    public MovimentoService(MovimentoRepository movimentoRepository) {
        this.movimentoRepository = movimentoRepository;
    }

    public List<Movimento> findAll() {
        return movimentoRepository.findAll();
    }

    public Optional<Movimento> findById(Long id) {
        return movimentoRepository.findById(id);
    }

    public Movimento save(Movimento movimento) {
        return movimentoRepository.save(movimento);
    }

    public void deleteById(Long id) {
        movimentoRepository.deleteById(id);
    }

    public Movimento findByNome(String nome) {
        return movimentoRepository.findByNome(nome);
    }

    public List<Movimento> findByTipo(String tipo) {
        return movimentoRepository.findByTipo(tipo);
    }

    public List<Movimento> findByCategoria(String categoria) {
        return movimentoRepository.findByCategoria(categoria);
    }
}
