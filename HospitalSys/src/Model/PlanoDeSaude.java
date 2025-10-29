package model;

public class PlanoDeSaude {

    private int id;
    private String nome;
    private String tipo; // Do SQL ENUM('PublicoSus', 'Particular')
    private String cobertura;

    // Construtores
    public PlanoDeSaude() {
    }

    public PlanoDeSaude(String nome, String tipo, String cobertura) {
        this.nome = nome;
        this.tipo = tipo;
        this.cobertura = cobertura;
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

    @Override
    public String toString() {
        return "PlanoDeSaude [ID=" + id + ", Nome=" + nome + ", Tipo=" + tipo + "]";
    }
}
