package Controller;

import Model.Paciente;
import Model.Visita;
import Model.dao.impl.VisitaDAOImpl;
import Model.dao.interfaces.VisitaDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VisitaController {
    
    private VisitaDAO visitaDAO;
    private PacienteController pacienteCtrl;

    public VisitaController() {
        this.visitaDAO = new VisitaDAOImpl();
        this.pacienteCtrl = new PacienteController();
    }
    
    public boolean agendarVisita(int idPaciente, LocalDateTime data, String observacao) {
        try {
            Paciente p = pacienteCtrl.buscarPaciente(idPaciente);
            if (p == null) {
                System.err.println("Erro: Paciente com ID " + idPaciente + " não encontrado!");
                return false;
            }
            
            Visita v = new Visita(data, observacao, p);
            visitaDAO.salvar(v);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao agendar visita: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Visita> listarVisitasPorPaciente(int idPaciente) {
        try {
            return visitaDAO.listarPorPaciente(idPaciente);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar visitas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // ... (Método de deletar/cancelar visita) ...
}