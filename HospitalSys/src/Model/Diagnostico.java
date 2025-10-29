package model;

import java.time.LocalDateTime;

public class Diagnostico {

    private int id;
    private String descricaoMed;
    private LocalDateTime data; // Do SQL DATETIME

    // Chave Estrangeira
    private Exame exame;

    // Construtores
    public Diagnostico() {
    }

    public Diagnostico(String descricaoMed, LocalDateTime data, Exame exame) {
        this.descricaoMed = descricaoMed;
        this.data = data;
        this.exame = exame;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoMed() {
        return descricaoMed;
    }

    public void setDescricaoMed(String descricaoMed) {
        this.descricaoMed = descricaoMed;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    @Override
    public String toString() {
        return "Diagnostico [ID=" + id + ", Descrição=" + descricaoMed + "]";
    }
}
