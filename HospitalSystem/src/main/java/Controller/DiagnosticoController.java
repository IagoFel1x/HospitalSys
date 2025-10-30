// DiagnosticoController.java
package Controller;

import Model.Diagnostico;
import Model.dao.impl.DiagnosticoDAOImpl;
import Model.dao.interfaces.DiagnosticoDAO;
import java.util.List;

public class DiagnosticoController {

    private final DiagnosticoDAO diagnosticoDAO;

    public DiagnosticoController() {
        this.diagnosticoDAO = new DiagnosticoDAOImpl();
    }

    public void salvar(Diagnostico diagnostico) {
        diagnosticoDAO.salvar(diagnostico);
    }

    public void atualizar(Diagnostico diagnostico) {
        diagnosticoDAO.atualizar(diagnostico);
    }

    public void excluir(int id_diagnostico) {
        diagnosticoDAO.excluir(id_diagnostico);
    }

    public Diagnostico buscarPorId(int id_diagnostico) {
        return diagnosticoDAO.buscarPorId(id_diagnostico);
    }

    public List<Diagnostico> listarTodos() {
        return diagnosticoDAO.listarTodos();
    }
}