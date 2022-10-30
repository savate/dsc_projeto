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
   @Column(name = "ESPECIALIZACAO")
   private String especializacao;
   @Column(name = "TITULO")
   private String titulo;
   @ElementCollection
   @CollectionTable(name = "TB_DISC_PROFESSOR",
           joinColumns = @JoinColumn(name = "ID_PESSOA", nullable = false))
   @Column(name = "PROF_DISCIPLINAS", nullable = false, length = 50)
   private Collection<String> disciplinasEnsinadas;
   @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY,
           cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turma> turmas;
   
    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
   
    public Collection<String> getDisciplinasEnsinadas() {
        return disciplinasEnsinadas;
    }
   
    public void addDisciplinasEnsinadas(String disciplina) {
        if (disciplinasEnsinadas == null) {
            disciplinasEnsinadas = new HashSet<>();
        }
        disciplinasEnsinadas.add(disciplina);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
