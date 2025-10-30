package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Leito;
import Model.dao.interfaces.LeitoDAO;
import Model.dao.impl.LeitoDAOImpl;

public class LeitoController {
    
    private LeitoDAO leitoDAO;
    
    public LeitoController() {
        this.leitoDAO = new LeitoDAOImpl();
    }
    
    public boolean adicionarLeito(String numero, String tipo, String status) {
        try {
            Leito l = new Leito(numero, tipo, status);
            leitoDAO.salvar(l);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar leito: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Leito> listarTodosLeitos() {
        try {
            return leitoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar leitos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Leito> listarLeitosLivres() {
        try {
            return leitoDAO.listarPorStatus("Livre");
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar leitos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Leito buscarLeito(int id) {
        try {
            return leitoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar leito: " + e.getMessage());
            return null;
        }
    }
    
    // Método 'protected' para ser usado apenas por outros controllers (InternacaoController)
    protected boolean atualizarStatusLeito(int id, String status) {
        try {
            leitoDAO.atualizarStatus(id, status);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar status do leito: " + e.getMessage());
            return false;
        }
    }
}