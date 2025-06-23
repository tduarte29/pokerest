package ifsp.bra.rest.poke.service;

import ifsp.bra.rest.poke.models.Treinador;
import ifsp.bra.rest.poke.repositories.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinadorService {
    private final TreinadorRepository treinadorRepository;
    private final RegiaoService regiaoService;

    @Autowired
    public TreinadorService(TreinadorRepository treinadorRepository, RegiaoService regiaoService) {
        this.treinadorRepository = treinadorRepository;
        this.regiaoService = regiaoService;
    }

    public List<Treinador> findAll() {
        return treinadorRepository.findAll();
    }

    public Optional<Treinador> findById(Long id) {
        return treinadorRepository.findById(id);
    }

    public Treinador save(Treinador treinador) {
        // Ensure the region exists
        regiaoService.findById(treinador.getRegiao().getId())
                .orElseThrow(() -> new RuntimeException("Região não encontrada"));
        return treinadorRepository.save(treinador);
    }

    public void deleteById(Long id) {
        treinadorRepository.deleteById(id);
    }

    public List<Treinador> findByRegiaoId(Long regiaoId) {
        return treinadorRepository.findByRegiaoId(regiaoId);
    }

    public List<Treinador> findByNomeContaining(String nome) {
        return treinadorRepository.findByNomeContaining(nome);
    }
}
