package Model.dao.interfaces;

import Model.Medico;
import Model.Paciente;
import Model.PlanoDeSaude;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PacienteDAO {
    void salvar(Paciente paciente) throws SQLException;
    void atualizar(Paciente paciente) throws SQLException;
    void deletar(int id) throws SQLException;
    Paciente buscarPorId(int id) throws SQLException;
    ArrayList<Paciente> listarTodos() throws SQLException;
    
    // Métodos para N:M com PlanoDeSaude
    void vincularPlanoDeSaude(int idPaciente, int idPlano) throws SQLException;
    void desvincularPlanoDeSaude(int idPaciente, int idPlano) throws SQLException;
    ArrayList<PlanoDeSaude> listarPlanosPorPaciente(int idPaciente) throws SQLException;

    // Métodos para N:M com Medico
    void vincularMedico(int idPaciente, int idMedico) throws SQLException;
    ArrayList<Medico> listarMedicosPorPaciente(int idPaciente) throws SQLException;
}