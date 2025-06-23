package ifsp.bra.rest.poke.controllers;


import ifsp.bra.rest.poke.models.Regiao;
import ifsp.bra.rest.poke.service.RegiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regioes")
public class RegiaoController {
    private final RegiaoService regiaoService;

    @Autowired
    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @GetMapping
    public List<Regiao> getAllRegioes() {
        return regiaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regiao> getRegiaoById(@PathVariable Long id) {
        Optional<Regiao> regiao = regiaoService.findById(id);
        return regiao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Regiao createRegiao(@RequestBody Regiao regiao) {
        return regiaoService.save(regiao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Regiao> updateRegiao(@PathVariable Long id, @RequestBody Regiao regiaoDetails) {
        Optional<Regiao> regiaoOptional = regiaoService.findById(id);
        if (!regiaoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Regiao regiao = regiaoOptional.get();
        regiao.setNome(regiaoDetails.getNome());
        regiao.setDescricao(regiaoDetails.getDescricao());
        regiao.setGeracaoIntroduzida(regiaoDetails.getGeracaoIntroduzida());
        Regiao updatedRegiao = regiaoService.save(regiao);
        return ResponseEntity.ok(updatedRegiao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegiao(@PathVariable Long id) {
        regiaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Regiao> getRegiaoByNome(@PathVariable String nome) {
        Regiao regiao = regiaoService.findByNome(nome);
        if (regiao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regiao);
    }
}
