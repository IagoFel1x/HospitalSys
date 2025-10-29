package model;

import java.time.LocalDateTime;

public class Visita {

    private int id;
    private LocalDateTime data; // Do SQL DATETIME
    private String observacao;

    // Chave Estrangeira
    private Paciente paciente;

    // Construtores
    public Visita() {
    }

    public Visita(LocalDateTime data, String observacao, Paciente paciente) {
        this.data = data;
        this.observacao = observacao;
        this.paciente = paciente;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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

    @Override
    public String toString() {
        String nomePaciente = (paciente != null) ? paciente.getNome() : "N/A";
        return "Visita [ID=" + id + ", Data=" + data + ", Paciente=" + nomePaciente + "]";
    }
}
