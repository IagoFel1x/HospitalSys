// Internacao.java
package Model;

import java.util.Date; // Use java.util.Date para POJO

public class Internacao {

    private int id_internacao;
    private Date data_inicio; // Mapeado de DATETIME
    private Date data_fim;    // Mapeado de DATETIME
    private String motivo;

    // Relacionamentos (N:1)
    private Paciente paciente;
    private Leito leito;

    public Internacao() {
    }

    // Getters e Setters
    public int getId_internacao() {
        return id_internacao;
    }

    public void setId_internacao(int id_internacao) {
        this.id_internacao = id_internacao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
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
}