// Ambulancia.java
package Model;

public class Ambulancia {

    private int id_ambulancia;
    private String placa;
    private String modelo;
    private int ano;

    // Relacionamento (N:1)
    private Hospital hospital;

    public Ambulancia() {
    }

    // Getters e Setters
    public int getId_ambulancia() {
        return id_ambulancia;
    }

    public void setId_ambulancia(int id_ambulancia) {
        this.id_ambulancia = id_ambulancia;
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
}