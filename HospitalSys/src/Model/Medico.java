package br.com.hospital.model.entidade;

// Entidade baseada na tabela 'Medico'
public class Medico {

    private int id;
    private String nome;
    private String especialidade;
    private String crm;
    private String telefone;
    private String email;

    // Construtores
    public Medico() {
    }

    public Medico(String nome, String especialidade, String crm, String telefone, String email) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Medico [ID=" + id + ", Nome=" + nome + ", Especialidade=" + especialidade + ", CRM=" + crm + "]";
    }
}
