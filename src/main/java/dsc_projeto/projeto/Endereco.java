package dsc_projeto.projeto;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {
    @Column(name = "LOGRADOURO", length = 150, nullable = false)
    private String logradouro;
    @Column(name = "BAIRRO", length = 150, nullable = false)
    private String bairro;
    @Column(name = "NUMERO", length = 5, nullable = false)
    private Integer numero;
    @Column(name = "CEP", length = 20, nullable = false)
    private String cep;
    @Column(name = "CIDADE", length = 50, nullable = false)
    private String cidade;
    @Column(name = "ESTADO", length = 50, nullable = false)
    private String estado;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
