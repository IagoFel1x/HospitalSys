package model;

// Entidade baseada na tabela 'Leito'
public class Leito {

    private int id;
    private String numero;
    private String tipo;
    private String status; // Ex: 'Ocupado', 'Livre', 'Manutenção'

    // Construtores
    public Leito() {
    }

    public Leito(String numero, String tipo, String status) {
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Leito [ID=" + id + ", Numero=" + numero + ", Tipo=" + tipo + ", Status=" + status + "]";
    }
}
