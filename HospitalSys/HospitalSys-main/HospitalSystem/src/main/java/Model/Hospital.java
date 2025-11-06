// Hospital.java
package Model;

import java.util.List;

/**
 * Representa a entidade Hospital no sistema, contendo suas informações básicas
 * e os relacionamentos com outras entidades (Ambulâncias, Funcionários e Médicos).
 */
public class Hospital {

    // --- Atributos de Identificação e Dados Básicos ---
    private int id_hospital; // Chave primária: Identificador único do hospital.
    private String nome;         // Nome do hospital.
    private String endereco;     // Endereço físico do hospital.
    private String telefone;     // Telefone de contato do hospital.

    // --- Relacionamentos (Associações com outras classes Model) ---
    // Lista de Ambulâncias que pertencem ou estão vinculadas a este hospital (Relacionamento 1:N).
    private List<Ambulancia> ambulancias;

    // Lista de Funcionários (Administrativos, Enfermeiros, etc.) lotados neste hospital (Relacionamento 1:N).
    private List<Funcionario> funcionarios;

    // Lista de Médicos que trabalham neste hospital (Pode ser 1:N ou N:M dependendo da modelagem de dados).
    private List<Medico> medicos;

    /**
     * Construtor padrão.
     */
    public Hospital() {
    }

    // --- Getters e Setters (Métodos de Acesso e Modificação dos Atributos) ---

    /**
     * Obtém o ID (identificador) do hospital.
     * @return O ID do hospital.
     */
    public int getId_hospital() {
        return id_hospital;
    }

    /**
     * Define o ID (identificador) do hospital.
     * @param id_hospital O novo ID do hospital.
     */
    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    /**
     * Obtém o nome do hospital.
     * @return O nome do hospital.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do hospital.
     * @param nome O novo nome do hospital.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o endereço do hospital.
     * @return O endereço do hospital.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do hospital.
     * @param endereco O novo endereço do hospital.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém o telefone do hospital.
     * @return O telefone de contato do hospital.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do hospital.
     * @param telefone O novo telefone de contato.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // --- Getters e Setters para Relacionamentos ---

    /**
     * Obtém a lista de ambulâncias vinculadas ao hospital.
     * @return A lista de objetos Ambulancia.
     */
    public List<Ambulancia> getAmbulancias() {
        return ambulancias;
    }

    /**
     * Define a lista de ambulâncias vinculadas ao hospital.
     * @param ambulancias A nova lista de Ambulancia.
     */
    public void setAmbulancias(List<Ambulancia> ambulancias) {
        this.ambulancias = ambulancias;
    }

    /**
     * Obtém a lista de funcionários lotados neste hospital.
     * @return A lista de objetos Funcionario.
     */
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Define a lista de funcionários lotados neste hospital.
     * @param funcionarios A nova lista de Funcionario.
     */
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * Obtém a lista de médicos vinculados ao hospital.
     * @return A lista de objetos Medico.
     */
    public List<Medico> getMedicos() {
        return medicos;
    }

    /**
     * Define a lista de médicos vinculados ao hospital.
     * @param medicos A nova lista de Medico.
     */
    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}