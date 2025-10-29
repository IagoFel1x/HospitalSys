package model;

public class Ambulancia {

    private int id;
    private String placa;
    private String modelo;
    private int ano;

    // Chave Estrangeira
    private Hospital hospital;

    // Construtores
    public Ambulancia() {
    }

    public Ambulancia(String placa, String modelo, int ano, Hospital hospital) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.hospital = hospital;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Ambulancia [ID=" + id + ", Placa=" + placa + ", Modelo=" + modelo + "]";
    }
}
