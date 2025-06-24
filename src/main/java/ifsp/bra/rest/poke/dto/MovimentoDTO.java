package ifsp.bra.rest.poke.dto;

import java.util.Set;
import java.util.stream.Collectors;

import ifsp.bra.rest.poke.models.Movimento;

public class MovimentoDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String categoria;
    private Integer poder;
    private Integer precisao;
    private Integer ppMax;
    private Set<PokemonResumoDTO> pokemons;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getPoder() { return poder; }
    public void setPoder(Integer poder) { this.poder = poder; }

    public Integer getPrecisao() { return precisao; }
    public void setPrecisao(Integer precisao) { this.precisao = precisao; }

    public Integer getPpMax() { return ppMax; }
    public void setPpMax(Integer ppMax) { this.ppMax = ppMax; }

    public Set<PokemonResumoDTO> getPokemons() { return pokemons; }
    public void setPokemons(Set<PokemonResumoDTO> pokemons) { this.pokemons = pokemons; }

    // DTO completo (com pokemons) - use em endpoints de movimentos
    public static MovimentoDTO toDTO(Movimento movimento) {
        MovimentoDTO dto = new MovimentoDTO();
        dto.setId(movimento.getId());
        dto.setNome(movimento.getNome());
        dto.setTipo(movimento.getTipo());
        dto.setCategoria(movimento.getCategoria());
        dto.setPoder(movimento.getPoder());
        dto.setPrecisao(movimento.getPrecisao());
        dto.setPpMax(movimento.getPpMax());
        dto.setPokemons(
            movimento.getPokemons().stream()
                .map(p -> {
                    PokemonResumoDTO pokeDto = new PokemonResumoDTO();
                    pokeDto.setId(p.getId());
                    pokeDto.setNome(p.getNome());
                    pokeDto.setNumPokedex(p.getNumPokedex());
                    pokeDto.setDescricao(p.getDescricao());
                    if (p.getTreinador() != null) {
                        TreinadorResumoDTO tDto = new TreinadorResumoDTO();
                        tDto.setId(p.getTreinador().getId());
                        tDto.setNome(p.getTreinador().getNome());
                        pokeDto.setTreinador(tDto);
                    }
                    return pokeDto;
                })
                .collect(Collectors.toSet())
        );
        return dto;
    }

    // DTO resumido (sem pokemons) - use em endpoints de pokemons
    public static MovimentoDTO toResumoDTO(Movimento movimento) {
        MovimentoDTO dto = new MovimentoDTO();
        dto.setId(movimento.getId());
        dto.setNome(movimento.getNome());
        dto.setTipo(movimento.getTipo());
        dto.setCategoria(movimento.getCategoria());
        dto.setPoder(movimento.getPoder());
        dto.setPrecisao(movimento.getPrecisao());
        dto.setPpMax(movimento.getPpMax());
        // NÃO preenche dto.setPokemons()
        return dto;
    }
}
