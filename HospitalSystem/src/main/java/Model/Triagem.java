// Triagem.java
package Model;

public class Triagem {

    private int id_triagem;
    private String pressao_arterial;
    private double temperatura; // Mapeado de DECIMAL
    private double peso;        // Mapeado de DECIMAL
    private double altura;      // Mapeado de DECIMAL
    private String obs;
    
    // Nota: A tabela Triagem no seu SQL não tem FK para Consulta.
    // Se precisar da ligação do ERD, a tabela SQL precisa ser alterada.
    // Seguindo fielmente o SQL, a classe é assim.

    public Triagem() {
    }

    // Getters e Setters
    public int getId_triagem() {
        return id_triagem;
    }

    public void setId_triagem(int id_triagem) {
        this.id_triagem = id_triagem;
    }

    public String getPressao_arterial() {
        return pressao_arterial;
    }

    public void setPressao_arterial(String pressao_arterial) {
        this.pressao_arterial = pressao_arterial;
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
}