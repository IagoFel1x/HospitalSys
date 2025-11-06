// Consulta.java
package Model;

import java.util.Date;
import java.sql.Time; // Ideal para o tipo TIME do SQL
import java.util.List;

public class Consulta {

    private int id_consulta;
    private Date data; // Mapeado de DATE
    private Time hora; // Mapeado de TIME
    private String diagnostico_geral;

    // Relacionamentos (N:1 e 1:N)
    private Paciente paciente;
    private Medico medico;
    private List<Exame> exames;
    // A Triagem não possui chave estrangeira na tabela SQL,
    // embora esteja no ERD. Seguindo o SQL, ela não é ligada aqui.

    public Consulta() {
    }

    // Getters e Setters
    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDiagnostico_geral() {
        return diagnostico_geral;
    }

    public void setDiagnostico_geral(String diagnostico_geral) {
        this.diagnostico_geral = diagnostico_geral;
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

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }
}