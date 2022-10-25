package dsc_projeto.projeto;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
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
   

}
