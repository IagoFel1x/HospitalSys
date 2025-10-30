// LeitoController.java
package Controller;

import Model.Leito;
import Model.dao.impl.LeitoDAOImpl;
import Model.dao.interfaces.LeitoDAO;
import java.util.List;

public class LeitoController {

    private final LeitoDAO leitoDAO;

    public LeitoController() {
        this.leitoDAO = new LeitoDAOImpl();
    }

    public void salvar(Leito leito) {
        leitoDAO.salvar(leito);
    }

    public void atualizar(Leito leito) {
        leitoDAO.atualizar(leito);
    }

    public void excluir(int id_leito) {
        leitoDAO.excluir(id_leito);
    }

    public Leito buscarPorId(int id_leito) {
        return leitoDAO.buscarPorId(id_leito);
    }

    public List<Leito> listarTodos() {
        return leitoDAO.listarTodos();
    }
}