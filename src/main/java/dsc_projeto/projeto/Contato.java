package dsc_projeto.projeto;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Contato implements Serializable {
    @Column(name = "EMAIL", length = 150, nullable = false)
    private String email;
    @Column(name = "TELEFONE", length = 150, nullable = false)
    private String telefone;
    @Column(name = "CAIXA_POSTAL", length = 50, nullable = true)
    private String caixaPostal;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }
    
}
