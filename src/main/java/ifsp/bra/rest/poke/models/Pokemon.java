package ifsp.bra.rest.poke.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "num_pokedex", nullable = false)
    private Integer numPokedex;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_treinador", nullable = false)
    private Treinador treinador;

    @ManyToMany
    @JoinTable(
        name = "Pokemon_Movimento",
        joinColumns = @JoinColumn(name = "id_pokemon"),
        inverseJoinColumns = @JoinColumn(name = "id_movimento")
    )
    private Set<Movimento> movimentos = new HashSet<>();

    // Constructors, getters, and setters
    public Pokemon() {}

    public Pokemon(String nome, Integer numPokedex, String descricao, Treinador treinador) {
        this.nome = nome;
        this.numPokedex = numPokedex;
        this.descricao = descricao;
        this.treinador = treinador;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumPokedex() {
        return numPokedex;
    }

    public void setNumPokedex(Integer numPokedex) {
        this.numPokedex = numPokedex;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public Set<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(Set<Movimento> movimentos) {
        this.movimentos = movimentos;
    }

    // Helper methods for moves
    public void addMovimento(Movimento movimento) {
        this.movimentos.add(movimento);
        movimento.getPokemons().add(this);
    }

    public void removeMovimento(Movimento movimento) {
        this.movimentos.remove(movimento);
        movimento.getPokemons().remove(this);
    }
}