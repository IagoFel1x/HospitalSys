package Controller;

import Model.Hospital;
import Model.Medico;
import Model.Paciente;
import Model.dao.impl.MedicoDAOImpl;
import Model.dao.interfaces.MedicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller para gerenciar todas as operações relacionadas a Médicos.
 * Inclui CRUD básico e gerenciamento de relacionamentos N:M com Hospital e Paciente.
 */
public class MedicoController {

    private MedicoDAO medicoDAO;

    public MedicoController() {
        // O Controller depende da INTERFACE, não da implementação
        this.medicoDAO = new MedicoDAOImpl();
    }

    /**
     * Adiciona um novo médico ao banco de dados.
     */
    public boolean adicionarMedico(String nome, String especialidade, String crm, String telefone, String email) {
        try {
            Medico m = new Medico(nome, especialidade, crm, telefone, email);
            medicoDAO.salvar(m); // Requer MedicoDAO.salvar()
            return true;
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao adicionar médico: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos os médicos cadastrados.
     */
    public ArrayList<Medico> listarTodosMedicos() {
        try {
            return medicoDAO.listarTodos(); // Requer MedicoDAO.listarTodos()
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao listar médicos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Atualiza os dados de um médico existente.
     */
    public boolean atualizarMedico(int id, String nome, String especialidade, String crm, String telefone, String email) {
        try {
            Medico m = new Medico(nome, especialidade, crm, telefone, email);
            m.setId(id);
            medicoDAO.atualizar(m); // Requer MedicoDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao atualizar médico: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deleta um médico do banco de dados pelo ID.
     */
    public boolean deletarMedico(int id) {
        try {
            medicoDAO.deletar(id); // Requer MedicoDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao deletar médico: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca um médico específico pelo seu ID.
     */
    public Medico buscarMedico(int id) {
        try {
            return medicoDAO.buscarPorId(id); // Requer MedicoDAO.buscarPorId()
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao buscar médico: " + e.getMessage());
            return null;
        }
    }
    
    // --- Métodos de Relacionamento N:M (Medico_Hospital) ---

    /**
     * Vincula um Médico a um Hospital (Tabela Medico_Hospital).
     */
    public boolean vincularHospital(int idMedico, int idHospital) {
        try {
            // (Opcional: verificar se medico e hospital existem)
            medicoDAO.vincularHospital(idMedico, idHospital); // Requer MedicoDAO.vincularHospital()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao vincular médico ao hospital: " + e.getMessage());
            return false;
        }
    }

    /**
     * Desvincula um Médico de um Hospital (Tabela Medico_Hospital).
     */
    public boolean desvincularHospital(int idMedico, int idHospital) {
        try {
            medicoDAO.desvincularHospital(idMedico, idHospital); // Requer MedicoDAO.desvincularHospital()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao desvincular médico do hospital: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos os Hospitais onde um Médico trabalha.
     */
    public ArrayList<Hospital> listarHospitaisDoMedico(int idMedico) {
        try {
            return medicoDAO.listarHospitaisPorMedico(idMedico); // Requer MedicoDAO.listarHospitaisPorMedico()
        } catch (SQLException e) {
            System.err.println("Erro ao listar hospitais do médico: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // --- Métodos de Relacionamento N:M (Medico_Paciente) ---
    
    /**
     * Lista todos os Pacientes associados a um Médico.
     */
    public ArrayList<Paciente> listarPacientesDoMedico(int idMedico) {
        try {
            return medicoDAO.listarPacientesPorMedico(idMedico); // Requer MedicoDAO.listarPacientesPorMedico()
        } catch (SQLException e) {
            System.err.println("