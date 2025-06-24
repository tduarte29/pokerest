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

import ifsp.bra.rest.poke.dto.MovimentoDTO;
import ifsp.bra.rest.poke.models.Movimento;
import ifsp.bra.rest.poke.service.MovimentoService;

@RestController
@RequestMapping("/api/movimentos")
public class MovimentoController {
    private final MovimentoService movimentoService;

    @Autowired
    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    @GetMapping
    public List<MovimentoDTO> getAllMovimentos() {
        return movimentoService.findAll().stream()
            .map(MovimentoDTO::toDTO)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoDTO> getMovimentoById(@PathVariable Long id) {
        Optional<Movimento> movimento = movimentoService.findById(id);
        return movimento.map(m -> ResponseEntity.ok(MovimentoDTO.toDTO(m)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovimentoDTO createMovimento(@RequestBody Movimento movimento) {
        return MovimentoDTO.toDTO(movimentoService.save(movimento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentoDTO> updateMovimento(@PathVariable Long id, @RequestBody Movimento movimentoDetails) {
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
        return ResponseEntity.ok(MovimentoDTO.toDTO(updatedMovimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimento(@PathVariable Long id) {
        movimentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<MovimentoDTO> getMovimentoByNome(@PathVariable String nome) {
        Movimento movimento = movimentoService.findByNome(nome);
        if (movimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MovimentoDTO.toDTO(movimento));
    }

    @GetMapping("/tipo/{tipo}")
    public List<MovimentoDTO> getMovimentosByTipo(@PathVariable String tipo) {
        return movimentoService.findByTipo(tipo).stream()
            .map(MovimentoDTO::toDTO)
            .toList();
    }

    @GetMapping("/categoria/{categoria}")
    public List<MovimentoDTO> getMovimentosByCategoria(@PathVariable String categoria) {
        return movimentoService.findByCategoria(categoria).stream()
            .map(MovimentoDTO::toDTO)
            .toList();
    }
}
