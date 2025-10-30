// FichaMedica.java
package Model;

public class FichaMedica {

    private int id_ficha;
    private String hist_clinico;
    private String alergias;
    private String observacoes;

    // Relacionamento (1:1)
    private Paciente paciente;

    public FichaMedica() {
    }

    // Getters e Setters
    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }

    public String getHist_clinico() {
        return hist_clinico;
    }

    public void setHist_clinico(String hist_clinico) {
        this.hist_clinico = hist_clinico;
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
}