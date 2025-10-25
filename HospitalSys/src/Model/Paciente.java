package model;

// Entidade baseada na tabela 'Paciente'
public class Paciente {

    private int id;
    private String nome;
    private String email;
    private String telefone;

    // Construtores
    public Paciente() {
    }

    public Paciente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Paciente [ID=" + getId() + ", Nome=" + getNome() + ", Email=" + getEmail() + ", Telefone=" + getTelefone() + "]";
    }
}
