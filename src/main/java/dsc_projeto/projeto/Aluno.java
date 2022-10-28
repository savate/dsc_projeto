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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;

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
   @ElementCollection
   @CollectionTable(name = "TB_DISCIPLINAS",
           joinColumns = @JoinColumn(name = "ID_PESSOA", nullable = false))
   @Column(name = "ALUNO_DISCIPLINAS", nullable = false, length = 50)
   private Collection<String> disciplinas;
 
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
    
    public Collection<String> getDisciplinas() {
        return disciplinas;
    }
    
    public void addDisciplinas(String disciplina) {
        if (disciplinas == null) {
            disciplinas  = new HashSet<>();
        }
        disciplinas.add(disciplina);
    }
}
