package ifsp.bra.rest.poke.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Regiao")
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_regiao")
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "geracao_introduzida", nullable = false)
    private Integer geracaoIntroduzida;

    // @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL)
    // private List<Treinador> treinadores;

    public Regiao() {}

    public Regiao(String nome, String descricao, Integer geracaoIntroduzida) {
        this.nome = nome;
        this.descricao = descricao;
        this.geracaoIntroduzida = geracaoIntroduzida;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getGeracaoIntroduzida() {
        return geracaoIntroduzida;
    }

    public void setGeracaoIntroduzida(Integer geracaoIntroduzida) {
        this.geracaoIntroduzida = geracaoIntroduzida;
    }

//     public List<Treinador> getTreinadores() {
//         return treinadores;
//     }

//     public void setTreinadores(List<Treinador> treinadores) {
//         this.treinadores = treinadores;
//     }
}
