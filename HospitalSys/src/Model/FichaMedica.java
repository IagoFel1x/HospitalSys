package model;

public class FichaMedica {

    private int id;
    private String histClinico;
    private String alergias;
    private String observacoes;

    // Chave Estrangeira (Relação 1-para-1)
    private Paciente paciente;

    // Construtores
    public FichaMedica() {
    }

    public FichaMedica(String histClinico, String alergias, String observacoes, Paciente paciente) {
        this.histClinico = histClinico;
        this.alergias = alergias;
        this.observacoes = observacoes;
        this.paciente = paciente;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHistClinico() {
        return histClinico;
    }

    public void setHistClinico(String histClinico) {
        this.histClinico = histClinico;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
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
        return "FichaMedica [ID=" + id + ", Paciente=" + nomePaciente + ", Alergias=" + alergias + "]";
    }
}
