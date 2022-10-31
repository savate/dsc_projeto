package dsc_projeto.projeto;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="TB_PROFESSOR") 
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName = "ID")
public class Professor extends Pessoa implements Serializable {
    @Column(name = "TITULO")
    private String titulo;
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Disciplina> disciplinas;
    @ElementCollection
    @CollectionTable(name = "TB_CURSO",
            joinColumns = @JoinColumn(name = "ID_PROFESSOR"))
    @Column(name = "NOME_CURSO")
    protected Collection<String> cursos;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    public Collection<String> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<String> cursos) {
        this.cursos = cursos;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

}
