// Visita.java
package Model;

import java.util.Date;

public class Visita {

    private int id_visita;
    private Date data; // Mapeado de DATETIME
    private String observacao;

    // Relacionamento (N:1)
    private Paciente paciente;

    public Visita() {
    }

    // Getters e Setters
    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}