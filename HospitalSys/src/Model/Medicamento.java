package model;

public class Medicamento {

    private int id;
    private String nome;
    private String fabricante;
    private String descricao;

    // Construtores
    public Medicamento() {
    }

    public Medicamento(String nome, String fabricante, String descricao) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.descricao = descricao;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Medicamento [ID=" + id + ", Nome=" + nome + ", Fabricante=" + fabricante + "]";
    }
}
