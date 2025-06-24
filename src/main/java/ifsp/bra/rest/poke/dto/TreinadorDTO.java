package ifsp.bra.rest.poke.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class TreinadorDTO {
    private Long id;
    private String nome;
    private Integer idade;
    private RegiaoDTO regiao;
    private Set<PokemonResumoDTO> pokemons;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public RegiaoDTO getRegiao() { return regiao; }
    public void setRegiao(RegiaoDTO regiao) { this.regiao = regiao; }

    public Set<PokemonResumoDTO> getPokemons() { return pokemons; }
    public void setPokemons(Set<PokemonResumoDTO> pokemons) { this.pokemons = pokemons; }

    public static TreinadorDTO toDTO(ifsp.bra.rest.poke.models.Treinador treinador) {
        TreinadorDTO dto = new TreinadorDTO();
        dto.setId(treinador.getId());
        dto.setNome(treinador.getNome());
        dto.setIdade(treinador.getIdade());
        dto.setRegiao(RegiaoDTO.toResumoDTO(treinador.getRegiao()));
        if (treinador.getPokemons() != null) {
            dto.setPokemons(
                treinador.getPokemons().stream()
                    .map(p -> {
                        PokemonResumoDTO pokeDto = new PokemonResumoDTO();
                        pokeDto.setId(p.getId());
                        pokeDto.setNome(p.getNome());
                        pokeDto.setNumPokedex(p.getNumPokedex());
                        pokeDto.setDescricao(p.getDescricao());
                        // Se quiser incluir treinador resumido, pode adicionar aqui
                        return pokeDto;
                    })
                    .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    public static TreinadorDTO toResumoDTO(ifsp.bra.rest.poke.models.Treinador treinador) {
        TreinadorDTO dto = new TreinadorDTO();
        dto.setId(treinador.getId());
        dto.setNome(treinador.getNome());
        dto.setIdade(treinador.getIdade());
        dto.setRegiao(RegiaoDTO.toResumoDTO(treinador.getRegiao()));
        // NÃO preenche dto.setPokemons()
        return dto;
    }
}
