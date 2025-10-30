// Leito.java
package Model;

import java.util.List;

public class Leito {

    private int id_leito;
    private String numero;
    private String tipo;
    private String status;

    // Relacionamento (1:N)
    private List<Internacao> internacoes;

    public Leito() {
    }

    // Getters e Setters
    public int getId_leito() {
        return id_leito;
    }

    public void setId_leito(int id_leito) {
        this.id_leito = id_leito;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Internacao> getInternacoes() {
        return internacoes;
    }

    public void setInternacoes(List<Internacao> internacoes) {
        this.internacoes = internacoes;
    }
}