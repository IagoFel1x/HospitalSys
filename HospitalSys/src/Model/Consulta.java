package model;

import java.time.LocalDate;
import java.time.LocalTime;

// Entidade baseada na tabela 'Consulta'
public class Consulta {

    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String diagnosticoGeral;

    // Chaves Estrangeiras como Objetos
    private Paciente paciente;
    private Medico medico;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDiagnosticoGeral() {
        return diagnosticoGeral;
    }

    public void setDiagnosticoGeral(String diagnosticoGeral) {
        this.diagnosticoGeral = diagnosticoGeral;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        String nomePaciente = (paciente != null) ? paciente.getNome() : "N/A (ID: " + (paciente != null ? paciente.getId() : "null") + ")";
        String nomeMedico = (medico != null) ? medico.getNome() : "N/A (ID: " + (medico != null ? medico.getId() : "null") + ")";

        return "Consulta [ID=" + id
                + ", Data=" + data
                + ", Hora=" + hora
                + ", Paciente=" + nomePaciente
                + ", Medico=" + nomeMedico
                + ", Diagnostico=" + diagnosticoGeral + "]";
    }
}
