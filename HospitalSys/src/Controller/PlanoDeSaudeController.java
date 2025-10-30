package Controller;

import Model.PlanoDeSaude;
import Model.dao.impl.PlanoDeSaudeDAOImpl;
import Model.dao.interfaces.PlanoDeSaudeDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlanoDeSaudeController {
    
    private PlanoDeSaudeDAO planoDAO;

    public PlanoDeSaudeController() {
        this.planoDAO = new PlanoDeSaudeDAOImpl();
    }
    
    public boolean adicionarPlano(String nome, String tipo, String cobertura) {
        try {
            PlanoDeSaude p = new PlanoDeSaude(nome, tipo, cobertura);
            planoDAO.salvar(p);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar plano: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<PlanoDeSaude> listarPlanos() {
        try {
            return planoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar planos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // ... (Métodos adicionarPlano, listarPlanos já existem) ...

    public PlanoDeSaude buscarPlano(int id) {
        try {
            return planoDAO.buscarPorId(id); // Requer PlanoDeSaudeDAO.buscarPorId()
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar plano: " + e.getMessage());
            return null;
        }
    }

    public boolean atualizarPlano(int id, String nome, String tipo, String cobertura) {
        try {
            PlanoDeSaude p = new PlanoDeSaude(nome, tipo, cobertura);
            p.setId(id);
            planoDAO.atualizar(p); // Requer PlanoDeSaudeDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar plano: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarPlano(int id) {
        try {
            planoDAO.deletar(id); // Requer PlanoDeSaudeDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao deletar plano: " + e.getMessage());
            return false;
        }
    }
}