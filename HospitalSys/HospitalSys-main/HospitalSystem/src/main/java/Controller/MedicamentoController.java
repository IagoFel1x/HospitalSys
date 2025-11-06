// MedicamentoController.java
package Controller;

import Model.Medicamento;
import Model.dao.impl.MedicamentoDAOImpl;
import Model.dao.interfaces.MedicamentoDAO;
import java.util.List;

public class MedicamentoController {

    private final MedicamentoDAO medicamentoDAO;

    public MedicamentoController() {
        this.medicamentoDAO = new MedicamentoDAOImpl();
    }

    public void salvar(Medicamento medicamento) {
        medicamentoDAO.salvar(medicamento);
    }

    public void atualizar(Medicamento medicamento) {
        medicamentoDAO.atualizar(medicamento);
    }

    public void excluir(int id_medicamento) {
        medicamentoDAO.excluir(id_medicamento);
    }

    public Medicamento buscarPorId(int id_medicamento) {
        return medicamentoDAO.buscarPorId(id_medicamento);
    }

    public List<Medicamento> listarTodos() {
        return medicamentoDAO.listarTodos();
    }
}