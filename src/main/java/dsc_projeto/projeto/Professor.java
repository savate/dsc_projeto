package dsc_projeto.projeto;

import dsc_projeto.projeto.tipos.TiposTitulo;
import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="TB_PROFESSOR") 
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName = "ID")
public class Professor extends Pessoa implements Serializable {
    @Column(name = "TITULO")
    private TiposTitulo titulo;
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Disciplina> disciplinas;
    @ElementCollection
    @CollectionTable(name = "TB_ESPECIALIZACAO",
            joinColumns = @JoinColumn(name = "ID_PROFESSOR"))
    @Column(name = "NOME_ESPECIALIZACAO")
    protected Collection<String> especializacoes;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID")
    private Curso curso;

    public TiposTitulo getTitulo() {
        return titulo;
    }

    public void setTitulo(TiposTitulo titulo) {
        this.titulo = titulo;
    }

    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Collection<String> getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(Collection<String> especializacoes) {
        this.especializacoes = especializacoes;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    @Override
    public String toString() {
        return "exemplo.jpa.Professor["+ super.toString()  + "]";
    }
    
}
