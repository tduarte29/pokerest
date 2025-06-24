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

import ifsp.bra.rest.poke.dto.RegiaoDTO;
import ifsp.bra.rest.poke.models.Regiao;
import ifsp.bra.rest.poke.service.RegiaoService;

@RestController
@RequestMapping("/api/regioes")
public class RegiaoController {
    private final RegiaoService regiaoService;

    @Autowired
    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @GetMapping
    public List<RegiaoDTO> getAllRegioes() {
        return regiaoService.findAll().stream()
            .map(RegiaoDTO::toDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoDTO> getRegiaoById(@PathVariable Long id) {
        Optional<Regiao> regiao = regiaoService.findById(id);
        return regiao.map(r -> ResponseEntity.ok(RegiaoDTO.toDTO(r)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RegiaoDTO createRegiao(@RequestBody Regiao regiao) {
        return RegiaoDTO.toDTO(regiaoService.save(regiao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoDTO> updateRegiao(@PathVariable Long id, @RequestBody Regiao regiaoDetails) {
        Optional<Regiao> regiaoOptional = regiaoService.findById(id);
        if (!regiaoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Regiao regiao = regiaoOptional.get();
        regiao.setNome(regiaoDetails.getNome());
        regiao.setDescricao(regiaoDetails.getDescricao());
        regiao.setGeracaoIntroduzida(regiaoDetails.getGeracaoIntroduzida());
        Regiao updatedRegiao = regiaoService.save(regiao);
        return ResponseEntity.ok(RegiaoDTO.toDTO(updatedRegiao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegiao(@PathVariable Long id) {
        regiaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<RegiaoDTO> getRegiaoByNome(@PathVariable String nome) {
        Regiao regiao = regiaoService.findByNome(nome);
        if (regiao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RegiaoDTO.toDTO(regiao));
    }
}
