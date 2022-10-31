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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="TB_ALUNO") 
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName = "ID")
public class Aluno extends Pessoa implements Serializable {
    @Column(name = "CURSO")
    private String curso;
    @Column(name = "PERIODO")
    private String periodo;
    @Column (name = "MATRICULA")
    private String matricula;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_ALUNOS_DISCIPLINAS", 
         joinColumns = {
             @JoinColumn(name = "ID_ALUNO")
         },
         inverseJoinColumns = {
             @JoinColumn(name = "ID_DISCIPLINA")
         })
    private Collection<Disciplina> disciplinas;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_DISCIPLINA", referencedColumnName = "ID")
    Disciplina disciplinaRep;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Disciplina getDisciplinaRep() {
        return disciplinaRep;
    }

    public void setDisciplinaRep(Disciplina disciplinaRep) {
        this.disciplinaRep = disciplinaRep;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
 
}
