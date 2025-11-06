// ExameController.java
package Controller;

import Model.Exame;
import Model.dao.impl.ExameDAOImpl;
import Model.dao.interfaces.ExameDAO;
import java.util.List;

public class ExameController {

    private final ExameDAO exameDAO;

    public ExameController() {
        this.exameDAO = new ExameDAOImpl();
    }

    public void salvar(Exame exame) {
        exameDAO.salvar(exame);
    }

    public void atualizar(Exame exame) {
        exameDAO.atualizar(exame);
    }

    public void excluir(int id_exame) {
        exameDAO.excluir(id_exame);
    }

    public Exame buscarPorId(int id_exame) {
        return exameDAO.buscarPorId(id_exame);
    }

    public List<Exame> listarTodos() {
        return exameDAO.listarTodos();
    }

    public List<Exame> listarPorConsulta(int id_consulta) {
        return exameDAO.listarPorConsulta(id_consulta);
    }
}