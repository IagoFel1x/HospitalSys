// TriagemController.java
package Controller;

import Model.Triagem;
import Model.dao.impl.TriagemDAOImpl;
import Model.dao.interfaces.TriagemDAO;
import java.util.List;

public class TriagemController {

    private final TriagemDAO triagemDAO;

    public TriagemController() {
        this.triagemDAO = new TriagemDAOImpl();
    }

    public void salvar(Triagem triagem) {
        triagemDAO.salvar(triagem);
    }

    public void atualizar(Triagem triagem) {
        triagemDAO.atualizar(triagem);
    }

    public void excluir(int id_triagem) {
        triagemDAO.excluir(id_triagem);
    }

    public Triagem buscarPorId(int id_triagem) {
        return triagemDAO.buscarPorId(id_triagem);
    }

    public List<Triagem> listarTodos() {
        return triagemDAO.listarTodos();
    }
}