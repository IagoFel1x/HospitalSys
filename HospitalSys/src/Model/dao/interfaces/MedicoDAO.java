package Model.dao.interfaces;

import Model.Hospital;
import Model.Medico;
import Model.Paciente;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicoDAO {
    void salvar(Medico medico) throws SQLException;
    void atualizar(Medico medico) throws SQLException;
    void deletar(int id) throws SQLException;
    Medico buscarPorId(int id) throws SQLException;
    ArrayList<Medico> listarTodos() throws SQLException;
    
    // Métodos para N:M com Hospital
    void vincularHospital(int idMedico, int idHospital) throws SQLException;
    void desvincularHospital(int idMedico, int idHospital) throws SQLException;
    ArrayList<Hospital> listarHospitaisPorMedico(int idMedico) throws SQLException;

    // Métodos para N:M com Paciente
    ArrayList<Paciente> listarPacientesPorMedico(int idMedico) throws SQLException;
}