package ifsp.bra.rest.poke.dto;

import java.util.Set;
import java.util.stream.Collectors;

import ifsp.bra.rest.poke.models.Pokemon;

public class PokemonDTO {
    private Long id;
    private String nome;
    private Integer numPokedex;
    private String descricao;
    private TreinadorDTO treinador;
    private Set<MovimentoDTO> movimentos;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getNumPokedex() { return numPokedex; }
    public void setNumPokedex(Integer numPokedex) { this.numPokedex = numPokedex; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public TreinadorDTO getTreinador() { return treinador; }
    public void setTreinador(TreinadorDTO treinador) { this.treinador = treinador; }

    public Set<MovimentoDTO> getMovimentos() { return movimentos; }
    public void setMovimentos(Set<MovimentoDTO> movimentos) { this.movimentos = movimentos; }

    public static PokemonDTO toDTO(Pokemon pokemon) {
        PokemonDTO dto = new PokemonDTO();
        dto.setId(pokemon.getId());
        dto.setNome(pokemon.getNome());
        dto.setNumPokedex(pokemon.getNumPokedex());
        dto.setDescricao(pokemon.getDescricao());
        dto.setTreinador(TreinadorDTO.toResumoDTO(pokemon.getTreinador())); // Use o DTO resumido aqui!
        dto.setMovimentos(
            pokemon.getMovimentos().stream()
                .map(MovimentoDTO::toResumoDTO)
                .collect(Collectors.toSet())
        );
        return dto;
    }
}
