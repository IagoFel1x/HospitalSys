package model;

public class Funcionario {

    private int id;
    private String nome;
    private String cargo;
    private String cpf;
    private String telefone;
    private String email;

    // Chave Estrangeira
    private Hospital hospital;

    // Construtores
    public Funcionario() {
    }

    public Funcionario(String nome, String cargo, String cpf, String telefone, String email, Hospital hospital) {
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.hospital = hospital;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        String nomeHospital = (hospital != null) ? hospital.getNome() : "N/A";
        return "Funcionario [ID=" + id + ", Nome=" + nome + ", Cargo=" + cargo + ", Hospital=" + nomeHospital + "]";
    }
}
