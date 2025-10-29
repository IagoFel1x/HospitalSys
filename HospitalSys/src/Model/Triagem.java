package model;

public class Triagem {

    private int id;
    private String pressaoArterial;
    private double temperatura; // Do SQL DECIMAL(4, 2)
    private double peso; // Do SQL DECIMAL(5, 2)
    private double altura; // Do SQL DECIMAL(3, 2)
    private String obs;

    // Construtores
    public Triagem() {
    }

    public Triagem(String pressaoArterial, double temperatura, double peso, double altura, String obs) {
        this.pressaoArterial = pressaoArterial;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.obs = obs;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "Triagem [ID=" + id + ", Pressão=" + pressaoArterial + ", Temp=" + temperatura + "C]";
    }
}
