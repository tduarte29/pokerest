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
import org.springframework.web.bind.annotation.RestController;

import ifsp.bra.rest.poke.dto.TreinadorDTO;
import ifsp.bra.rest.poke.models.Treinador;
import ifsp.bra.rest.poke.service.TreinadorService;

@RestController
@RequestMapping("/api/treinadores")
public class TreinadorController {
    private final TreinadorService treinadorService;

    @Autowired
    public TreinadorController(TreinadorService treinadorService) {
        this.treinadorService = treinadorService;
    }

    @GetMapping
    public List<TreinadorDTO> getAllTreinadores() {
        return treinadorService.findAll().stream()
            .map(TreinadorDTO::toDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinadorDTO> getTreinadorById(@PathVariable Long id) {
        Optional<Treinador> treinador = treinadorService.findById(id);
        return treinador.map(t -> ResponseEntity.ok(TreinadorDTO.toDTO(t)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TreinadorDTO createTreinador(@RequestBody Treinador treinador) {
        return TreinadorDTO.toDTO(treinadorService.save(treinador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinadorDTO> updateTreinador(@PathVariable Long id, @RequestBody Treinador treinadorDetails) {
        Optional<Treinador> treinadorOptional = treinadorService.findById(id);
        if (!treinadorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Treinador treinador = treinadorOptional.get();
        treinador.setNome(treinadorDetails.getNome());
        treinador.setIdade(treinadorDetails.getIdade());
        treinador.setRegiao(treinadorDetails.getRegiao());
        Treinador updatedTreinador = treinadorService.save(treinador);
        return ResponseEntity.ok(TreinadorDTO.toDTO(updatedTreinador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreinador(@PathVariable Long id) {
        treinadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}