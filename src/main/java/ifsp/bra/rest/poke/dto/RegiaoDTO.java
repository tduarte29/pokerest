package ifsp.bra.rest.poke.dto;

import java.util.Set;
import java.util.stream.Collectors;

import ifsp.bra.rest.poke.models.Regiao;

public class RegiaoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer geracaoIntroduzida;
    private Set<TreinadorResumoDTO> treinadores; // Novo campo

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getGeracaoIntroduzida() { return geracaoIntroduzida; }
    public void setGeracaoIntroduzida(Integer geracaoIntroduzida) { this.geracaoIntroduzida = geracaoIntroduzida; }

    public Set<TreinadorResumoDTO> getTreinadores() { return treinadores; }
    public void setTreinadores(Set<TreinadorResumoDTO> treinadores) { this.treinadores = treinadores; }

    public static RegiaoDTO toDTO(Regiao regiao) {
        RegiaoDTO dto = new RegiaoDTO();
        dto.setId(regiao.getId());
        dto.setNome(regiao.getNome());
        dto.setDescricao(regiao.getDescricao());
        dto.setGeracaoIntroduzida(regiao.getGeracaoIntroduzida());
        if (regiao.getTreinadores() != null) {
            dto.setTreinadores(
                regiao.getTreinadores().stream()
                    .map(t -> {
                        TreinadorResumoDTO tDto = new TreinadorResumoDTO();
                        tDto.setId(t.getId());
                        tDto.setNome(t.getNome());
                        return tDto;
                    })
                    .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    // Método para DTO resumido (sem treinadores)
    public static RegiaoDTO toResumoDTO(Regiao regiao) {
        RegiaoDTO dto = new RegiaoDTO();
        dto.setId(regiao.getId());
        dto.setNome(regiao.getNome());
        dto.setDescricao(regiao.getDescricao());
        dto.setGeracaoIntroduzida(regiao.getGeracaoIntroduzida());
        // NÃO preenche dto.setTreinadores()
        return dto;
    }
}
