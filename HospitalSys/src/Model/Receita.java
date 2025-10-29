package model;

public class Receita {

    private int id;
    private String dosagem;
    private String frequencia;
    private String duracao;

    // Chaves Estrangeiras
    private Diagnostico diagnostico;
    private Medicamento medicamento;

    // Construtores
    public Receita() {
    }

    public Receita(String dosagem, String frequencia, String duracao, Diagnostico diagnostico, Medicamento medicamento) {
        this.dosagem = dosagem;
        this.frequencia = frequencia;
        this.duracao = duracao;
        this.diagnostico = diagnostico;
        this.medicamento = medicamento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public String toString() {
        String nomeMedicamento = (medicamento != null) ? medicamento.getNome() : "N/A";
        return "Receita [ID=" + id + ", Medicamento=" + nomeMedicamento + ", Dosagem=" + dosagem + "]";
    }
}
