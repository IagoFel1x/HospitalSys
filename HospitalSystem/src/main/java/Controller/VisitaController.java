// VisitaController.java
package Controller;

import Model.Visita;
import Model.dao.impl.VisitaDAOImpl;
import Model.dao.interfaces.VisitaDAO;
import java.util.List;

public class VisitaController {

    private final VisitaDAO visitaDAO;

    public VisitaController() {
        this.visitaDAO = new VisitaDAOImpl();
    }

    public void salvar(Visita visita) {
        visitaDAO.salvar(visita);
    }

    public void atualizar(Visita visita) {
        visitaDAO.atualizar(visita);
    }

    public void excluir(int id_visita) {
        visitaDAO.excluir(id_visita);
    }

    public Visita buscarPorId(int id_visita) {
        return visitaDAO.buscarPorId(id_visita);
    }

    public List<Visita> listarTodos() {
        return visitaDAO.listarTodos();
    }
}