// Diagnostico.java
package Model;

import java.util.Date;
import java.util.List;

public class Diagnostico {

    private int id_diagnostico;
    private String descricao_med;
    private Date data; // Mapeado de DATETIME

    // Relacionamentos (N:1 e 1:N)
    private Exame exame;
    private List<Receita> receitas;

    public Diagnostico() {
    }

    // Getters e Setters
    public int getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(int id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public String getDescricao_med() {
        return descricao_med;
    }

    public void setDescricao_med(String descricao_med) {
        this.descricao_med = descricao_med;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
}