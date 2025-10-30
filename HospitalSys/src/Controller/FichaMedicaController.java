package Controller;

import Model.FichaMedica;
import Model.Paciente;
import Model.dao.impl.FichaMedicaDAOImpl;
import Model.dao.interfaces.FichaMedicaDAO;
import java.sql.SQLException;

public class FichaMedicaController {
    
    private FichaMedicaDAO fichaDAO;
    private PacienteController pacienteCtrl;

    public FichaMedicaController() {
        this.fichaDAO = new FichaMedicaDAOImpl();
        this.pacienteCtrl = new PacienteController();
    }
    
    public FichaMedica buscarPorPaciente(int idPaciente) {
        try {
            return fichaDAO.buscarPorPaciente(idPaciente);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar ficha: " + e.getMessage());
            return null;
        }
    }
    
    public boolean salvarFicha(int idPaciente, String histClinico, String alergias, String obs) {
         try {
            Paciente p = pacienteCtrl.buscarPaciente(idPaciente);
             if (p == null) {
                 System.err.println("Erro: Paciente com ID " + idPaciente + " não encontrado!");
                return false;
            }
             
            FichaMedica ficha = new FichaMedica(histClinico, alergias, obs, p);
            
            // Lógica de 1-para-1: verificar se já existe
            FichaMedica existente = fichaDAO.buscarPorPaciente(idPaciente);
            if (existente != null) {
                ficha.setId(existente.getId());
                fichaDAO.atualizar(ficha);
            } else {
                fichaDAO.salvar(ficha);
            }
            return true;
         } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar ficha: " + e.getMessage());
            return false;
        }
    }
}