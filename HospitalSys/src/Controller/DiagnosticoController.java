package Controller;

import Model.Diagnostico;
import Model.Exame;
import Model.dao.impl.DiagnosticoDAOImpl;
import Model.dao.interfaces.DiagnosticoDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiagnosticoController {
    
    private DiagnosticoDAO diagnosticoDAO;
    private ExameController exameCtrl;

    public DiagnosticoController() {
        this.diagnosticoDAO = new DiagnosticoDAOImpl();
        this.exameCtrl = new ExameController();
    }
    
    public boolean adicionarDiagnostico(int idExame, String descricao) {
        try {
            Exame e = exameCtrl.buscarExame(idExame);
            if (e == null) {
                System.err.println("Erro: Exame com ID " + idExame + " não encontrado!");
                return false;
            }
            
            Diagnostico d = new Diagnostico(descricao, LocalDateTime.now(), e);
            diagnosticoDAO.salvar(d);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar diagnóstico: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Diagnostico> listarDiagnosticosPorExame(int idExame) {
        try {
            return diagnosticoDAO.listarPorExame(idExame);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar diagnósticos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Diagnostico buscarDiagnostico(int id) {
         try {
            return diagnosticoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar diagnóstico: " + e.getMessage());
            return null;
        }
    }
}