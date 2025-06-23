package ifsp.bra.rest.poke.controllers;

import ifsp.bra.rest.poke.models.Movimento;
import ifsp.bra.rest.poke.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimentos")
public class MovimentoController {
    private final MovimentoService movimentoService;

    @Autowired
    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    @GetMapping
    public List<Movimento> getAllMovimentos() {
        return movimentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimento> getMovimentoById(@PathVariable Long id) {
        Optional<Movimento> movimento = movimentoService.findById(id);
        return movimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movimento createMovimento(@RequestBody Movimento movimento) {
        return movimentoService.save(movimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimento> updateMovimento(@PathVariable Long id, @RequestBody Movimento movimentoDetails) {
        Optional<Movimento> movimentoOptional = movimentoService.findById(id);
        if (!movimentoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Movimento movimento = movimentoOptional.get();
        movimento.setNome(movimentoDetails.getNome());
        movimento.setTipo(movimentoDetails.getTipo());
        movimento.setCategoria(movimentoDetails.getCategoria());
        movimento.setPoder(movimentoDetails.getPoder());
        movimento.setPrecisao(movimentoDetails.getPrecisao());
        movimento.setPpMax(movimentoDetails.getPpMax());
        Movimento updatedMovimento = movimentoService.save(movimento);
        return ResponseEntity.ok(updatedMovimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimento(@PathVariable Long id) {
        movimentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Movimento> getMovimentoByNome(@PathVariable String nome) {
        Movimento movimento = movimentoService.findByNome(nome);
        if (movimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimento);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Movimento> getMovimentosByTipo(@PathVariable String tipo) {
        return movimentoService.findByTipo(tipo);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Movimento> getMovimentosByCategoria(@PathVariable String categoria) {
        return movimentoService.findByCategoria(categoria);
    }
}
