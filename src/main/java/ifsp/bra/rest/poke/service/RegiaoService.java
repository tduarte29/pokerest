package ifsp.bra.rest.poke.service;

import ifsp.bra.rest.poke.models.Regiao;
import ifsp.bra.rest.poke.repositories.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegiaoService {
    private final RegiaoRepository regiaoRepository;

    @Autowired
    public RegiaoService(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    public List<Regiao> findAll() {
        return regiaoRepository.findAll();
    }

    public Optional<Regiao> findById(Long id) {
        return regiaoRepository.findById(id);
    }

    public Regiao save(Regiao regiao) {
        return regiaoRepository.save(regiao);
    }

    public void deleteById(Long id) {
        regiaoRepository.deleteById(id);
    }

    public Regiao findByNome(String nome) {
        return regiaoRepository.findByNome(nome);
    }
}
