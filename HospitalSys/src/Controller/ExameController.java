package Controller;

import Model.Consulta;
import Model.Exame;
import Model.dao.impl.ExameDAOImpl;
import Model.dao.interfaces.ExameDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExameController {
    
    private ExameDAO exameDAO;
    private ConsultaController consultaCtrl;
    
    public ExameController() {
        this.exameDAO = new ExameDAOImpl();
        this.consultaCtrl = new ConsultaController();
    }
    
    public boolean solicitarExame(int idConsulta, String tipoExame, String resultado) {
        try {
            Consulta c = consultaCtrl.buscarConsulta(idConsulta);
            if (c == null) {
                System.err.println("Erro: Consulta com ID " + idConsulta + " não encontrada!");
                return false;
            }
            
            Exame e = new Exame(tipoExame, LocalDateTime.now(), resultado, c);
            exameDAO.salvar(e);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar exame: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Exame> listarExamesPorConsulta(int idConsulta) {
         try {
            return exameDAO.listarPorConsulta(idConsulta);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar exames: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Exame buscarExame(int id) {
         try {
            return exameDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar exame: " + e.getMessage());
            return null;
        }
    }
}