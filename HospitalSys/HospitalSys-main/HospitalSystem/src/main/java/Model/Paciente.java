// Paciente.java
package Model;

import java.util.Date;
import java.util.List;

public class Paciente {

    private int id_paciente;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String sexo;
    private Date data_nascimento;

    // Relacionamentos (1:1, 1:N, N:M)
    private FichaMedica fichaMedica;
    private List<Internacao> internacoes;
    private List<Visita> visitas;
    private List<Consulta> consultas;
    private List<PlanoDeSaude> planosDeSaude;
    private List<Medico> medicos;

    public Paciente() {
    }

    // Getters e Setters
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public FichaMedica getFichaMedica() {
        return fichaMedica;
    }

    public void setFichaMedica(FichaMedica fichaMedica) {
        this.fichaMedica = fichaMedica;
    }

    public List<Internacao> getInternacoes() {
        return internacoes;
    }

    public void setInternacoes(List<Internacao> internacoes) {
        this.internacoes = internacoes;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<PlanoDeSaude> getPlanosDeSaude() {
        return planosDeSaude;
    }

    public void setPlanosDeSaude(List<PlanoDeSaude> planosDeSaude) {
        this.planosDeSaude = planosDeSaude;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}