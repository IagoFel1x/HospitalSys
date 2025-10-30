// Medico.java
package Model;

import java.util.List;

public class Medico {

    private int id_medico;
    private String nome;
    private String especialidade;
    private String crm;
    private String telefone;
    private String email;

    // Relacionamentos (1:N e N:M)
    private List<Consulta> consultas;
    private List<Hospital> hospitais;
    private List<Paciente> pacientes;

    public Medico() {
    }

    // Getters e Setters
    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<Hospital> getHospitais() {
        return hospitais;
    }

    public void setHospitais(List<Hospital> hospitais) {
        this.hospitais = hospitais;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}