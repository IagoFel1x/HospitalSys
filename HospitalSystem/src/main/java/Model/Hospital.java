// Hospital.java
package Model;

import java.util.List;

public class Hospital {

    private int id_hospital;
    private String nome;
    private String endereco;
    private String telefone;

    // Relacionamentos (1:N e N:M)
    private List<Ambulancia> ambulancias;
    private List<Funcionario> funcionarios;
    private List<Medico> medicos;

    public Hospital() {
    }

    // Getters e Setters
    public int getId_hospital() {
        return id_hospital;
    }

    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Ambulancia> getAmbulancias() {
        return ambulancias;
    }

    public void setAmbulancias(List<Ambulancia> ambulancias) {
        this.ambulancias = ambulancias;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}