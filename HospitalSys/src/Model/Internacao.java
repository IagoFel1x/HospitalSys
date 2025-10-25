package model;

import java.time.LocalDateTime;

// Entidade baseada na tabela 'Internacao'
public class Internacao {

    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim; // Pode ser nulo
    private String motivo;

    // Chaves Estrangeiras como Objetos
    private Paciente paciente;
    private Leito leito;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Leito getLeito() {
        return leito;
    }

    public void setLeito(Leito leito) {
        this.leito = leito;
    }

    @Override
    public String toString() {
        String pacienteNome = (paciente != null) ? paciente.getNome() : "N/A";
        String leitoNumero = (leito != null) ? leito.getNumero() : "N/A";
        String fim = (dataFim != null) ? dataFim.toString() : "EM ANDAMENTO";

        return "Internacao [ID=" + id + ", Paciente=" + pacienteNome
                + ", Leito=" + leitoNumero + ", Inicio=" + dataInicio
                + ", Fim=" + fim + ", Motivo=" + motivo + "]";
    }
}
