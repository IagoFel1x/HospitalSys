package Controller;

import Model.Medicamento;
import Model.dao.impl.MedicamentoDAOImpl;
import Model.dao.interfaces.MedicamentoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentoController {

    private MedicamentoDAO medicamentoDAO;
    
    public MedicamentoController() {
        this.medicamentoDAO = new MedicamentoDAOImpl();
    }
    
    public boolean adicionarMedicamento(String nome, String fabricante, String descricao) {
        try {
            Medicamento m = new Medicamento(nome, fabricante, descricao);
            medicamentoDAO.salvar(m);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar medicamento: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Medicamento> listarMedicamentos() {
        try {
            return medicamentoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar medicamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Medicamento buscarMedicamento(int id) {
         try {
            return medicamentoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar medicamento: " + e.getMessage());
            return null;
        }
    }
    
    // ... (Métodos adicionarMedicamento, listarMedicamentos, buscarMedicamento já existem) ...

    public boolean atualizarMedicamento(int id, String nome, String fabricante, String descricao) {
        try {
            Medicamento m = new Medicamento(nome, fabricante, descricao);
            m.setId(id);
            medicamentoDAO.atualizar(m); // Requer MedicamentoDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar medicamento: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarMedicamento(int id) {
        try {
            medicamentoDAO.deletar(id); // Requer MedicamentoDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao deletar medicamento: " + e.getMessage());
            return false;
        }
    }
}