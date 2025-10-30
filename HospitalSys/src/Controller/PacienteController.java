package Controller;

import Model.Medico;
import Model.Paciente;
import Model.PlanoDeSaude;
import Model.dao.impl.PacienteDAOImpl;
import Model.dao.interfaces.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller para gerenciar todas as operações relacionadas a Pacientes.
 * Inclui CRUD básico e gerenciamento de relacionamentos N:M com Medico e PlanoDeSaude.
 */
public class PacienteController {

    private PacienteDAO pacienteDAO;

    public PacienteController() {
        // O Controller depende da INTERFACE, não da implementação
        this.pacienteDAO = new PacienteDAOImpl();
    }

    /**
     * Adiciona um novo paciente ao banco de dados.
     */
    public boolean adicionarPaciente(String nome, String email, String telefone, String endereco, String sexo, java.time.LocalDate dataNasc) {
        try {
            Paciente p = new Paciente(nome, email, telefone, endereco, sexo, dataNasc);
            pacienteDAO.salvar(p); // Requer PacienteDAO.salvar()
            return true;
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao adicionar paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos os pacientes cadastrados.
     */
    public ArrayList<Paciente> listarTodosPacientes() {
        try {
            return pacienteDAO.listarTodos(); // Requer PacienteDAO.listarTodos()
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao listar pacientes: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }

    /**
     * Atualiza os dados de um paciente existente.
     */
    public boolean atualizarPaciente(int id, String nome, String email, String telefone, String endereco, String sexo, java.time.LocalDate dataNasc) {
        try {
            Paciente p = new Paciente(nome, email, telefone, endereco, sexo, dataNasc);
            p.setId(id);
            pacienteDAO.atualizar(p); // Requer PacienteDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao atualizar paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deleta um paciente do banco de dados pelo ID.
     */
    public boolean deletarPaciente(int id) {
        try {
            pacienteDAO.deletar(id); // Requer PacienteDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao deletar paciente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Busca um paciente específico pelo seu ID.
     */
    public Paciente buscarPaciente(int id) {
        try {
            return pacienteDAO.buscarPorId(id); // Requer PacienteDAO.buscarPorId()
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao buscar paciente: " + e.getMessage());
            return null;
        }
    }

    // --- Métodos de Relacionamento N:M (Paciente_PlanoDeSaude) ---

    /**
     * Vincula um Plano de Saúde a um Paciente (Tabela Paciente_PlanoDeSaude).
     */
    public boolean vincularPlanoDeSaude(int idPaciente, int idPlano) {
        try {
            // (Opcional: verificar se paciente e plano existem)
            pacienteDAO.vincularPlanoDeSaude(idPaciente, idPlano); // Requer PacienteDAO.vincularPlanoDeSaude()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao vincular plano ao paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Desvincula um Plano de Saúde de um Paciente (Tabela Paciente_PlanoDeSaude).
     */
    public boolean desvincularPlanoDeSaude(int idPaciente, int idPlano) {
        try {
            pacienteDAO.desvincularPlanoDeSaude(idPaciente, idPlano); // Requer PacienteDAO.desvincularPlanoDeSaude()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao desvincular plano do paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos os Planos de Saúde associados a um Paciente.
     */
    public ArrayList<PlanoDeSaude> listarPlanosDoPaciente(int idPaciente) {
        try {
            return pacienteDAO.listarPlanosPorPaciente(idPaciente); // Requer PacienteDAO.listarPlanosPorPaciente()
        } catch (SQLException e) {
            System.err.println("Erro ao listar planos do paciente: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // --- Métodos de Relacionamento N:M (Medico_Paciente) ---
    
    /**
     * Vincula um Médico a um Paciente (Tabela Medico_Paciente).
     */
    public boolean vincularMedico(int idPaciente, int idMedico) {
        try {
            pacienteDAO.vincularMedico(idPaciente, idMedico); // Requer PacienteDAO.vincularMedico()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao vincular médico ao paciente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Desvincula um Médico de um Paciente (Tabela Medico_Paciente).
     */
    public boolean desvincularMedico(int idPaciente, int idMedico) {
        try {
            pacienteDAO.desvincularMedico(idPaciente, idMedico); // Requer PacienteDAO.desvincularMedico()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao desvincular médico do paciente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Lista todos os Médicos associados a um Paciente.
     */
    public ArrayList<Medico> listarMedicosDoPaciente(int idPaciente) {
        try {
            return pacienteDAO.listarMedicosPorPaciente(idPaciente); // Requer PacienteDAO.listarMedicosPorPaciente()
        } catch (SQLException e) {
            System.err.println("Erro ao listar médicos do paciente: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}