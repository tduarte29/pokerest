package ifsp.bra.rest.poke.dto;

public class PokemonResumoDTO {
    private Long id;
    private String nome;
    private Integer numPokedex;
    private String descricao;
    private TreinadorResumoDTO treinador;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getNumPokedex() { return numPokedex; }
    public void setNumPokedex(Integer numPokedex) { this.numPokedex = numPokedex; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public TreinadorResumoDTO getTreinador() { return treinador; }
    public void setTreinador(TreinadorResumoDTO treinador) { this.treinador = treinador; }
}