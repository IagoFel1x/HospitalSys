// PlanoDeSaude.java
package Model;

import java.util.List;

public class PlanoDeSaude {

    private int id_plano_saude;
    private String nome;
    private String tipo; // Mapeado de ENUM para String (mais simples)
    private String cobertura;

    // Relacionamento (N:M)
    private List<Paciente> pacientes;

    public PlanoDeSaude() {
    }

    // Getters e Setters
    public int getId_plano_saude() {
        return id_plano_saude;
    }

    public void setId_plano_saude(int id_plano_saude) {
        this.id_plano_saude = id_plano_saude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}