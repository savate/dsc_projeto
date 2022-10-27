package dsc_projeto.projeto;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_ALUNO") 
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName = "ID")
public class Aluno extends Pessoa implements Serializable {
   @Column(name = "CURSO")
   private String curso;
   @Column(name = "PERIODO")
   private String periodo;

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
   
   
}
