package model;

import java.time.LocalDateTime;

public class Exame {

    private int id;
    private String tipoExame;
    private LocalDateTime data; // Do SQL DATETIME
    private String tipoResultado;

    // Chave Estrangeira
    private Consulta consulta;

    // Construtores
    public Exame() {
    }

    public Exame(String tipoExame, LocalDateTime data, String tipoResultado, Consulta consulta) {
        this.tipoExame = tipoExame;
        this.data = data;
        this.tipoResultado = tipoResultado;
        this.consulta = consulta;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(String tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    @Override
    public String toString() {
        return "Exame [ID=" + id + ", Tipo=" + tipoExame + ", Data=" + data + "]";
    }
}
