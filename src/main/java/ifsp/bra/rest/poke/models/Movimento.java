package ifsp.bra.rest.poke.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Movimento")
public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimento")
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "poder")
    private Integer poder;

    @Column(name = "precisao", nullable = false)
    private Integer precisao;

    @Column(name = "pp_max", nullable = false)
    private Integer ppMax;

    @ManyToMany(mappedBy = "movimentos")
    private Set<Pokemon> pokemons = new HashSet<>();

    // Constructors, getters, and setters
    public Movimento() {}

    public Movimento(String nome, String tipo, String categoria, Integer poder, Integer precisao, Integer ppMax) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.poder = poder;
        this.precisao = precisao;
        this.ppMax = ppMax;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public Integer getPrecisao() {
        return precisao;
    }

    public void setPrecisao(Integer precisao) {
        this.precisao = precisao;
    }

    public Integer getPpMax() {
        return ppMax;
    }

    public void setPpMax(Integer ppMax) {
        this.ppMax = ppMax;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
