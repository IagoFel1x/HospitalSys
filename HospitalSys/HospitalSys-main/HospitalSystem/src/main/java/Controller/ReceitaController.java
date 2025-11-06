// ReceitaController.java
package Controller;

import Model.Receita;
import Model.dao.impl.ReceitaDAOImpl;
import Model.dao.interfaces.ReceitaDAO;
import java.util.List;

public class ReceitaController {

    private final ReceitaDAO receitaDAO;

    public ReceitaController() {
        this.receitaDAO = new ReceitaDAOImpl();
    }

    public void salvar(Receita receita) {
        receitaDAO.salvar(receita);
    }

    public void atualizar(Receita receita) {
        receitaDAO.atualizar(receita);
    }

    public void excluir(int id_receita) {
        receitaDAO.excluir(id_receita);
    }

    public Receita buscarPorId(int id_receita) {
        return receitaDAO.buscarPorId(id_receita);
    }

    public List<Receita> listarTodos() {
        return receitaDAO.listarTodos();
    }

    public List<Receita> listarPorDiagnostico(int id_diagnostico) {
        return receitaDAO.listarPorDiagnostico(id_diagnostico);
    }
}