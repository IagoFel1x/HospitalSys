// Exame.java
package Model;

import java.util.Date;
import java.util.List;

public class Exame {

    private int id_exame;
    private String tipo_exame;
    private Date data; // Mapeado de DATETIME
    private String tipo_resultado;

    // Relacionamentos (N:1 e 1:N)
    private Consulta consulta;
    private List<Diagnostico> diagnosticos;

    public Exame() {
    }

    // Getters e Setters
    public int getId_exame() {
        return id_exame;
    }

    public void setId_exame(int id_exame) {
        this.id_exame = id_exame;
    }

    public String getTipo_exame() {
        return tipo_exame;
    }

    public void setTipo_exame(String tipo_exame) {
        this.tipo_exame = tipo_exame;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo_resultado() {
        return tipo_resultado;
    }

    public void setTipo_resultado(String tipo_resultado) {
        this.tipo_resultado = tipo_resultado;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
}