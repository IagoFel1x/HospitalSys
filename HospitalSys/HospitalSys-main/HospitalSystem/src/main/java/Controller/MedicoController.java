// MedicoController.java
package Controller;

import Model.Medico;
import Model.dao.impl.MedicoDAOImpl;
import Model.dao.interfaces.MedicoDAO;
import java.util.List;

public class MedicoController {

    private final MedicoDAO medicoDAO;

    public MedicoController() {
        this.medicoDAO = new MedicoDAOImpl();
    }

    public void salvar(Medico medico) {
        medicoDAO.salvar(medico);
    }

    public void atualizar(Medico medico) {
        medicoDAO.atualizar(medico);
    }

    public void excluir(int id_medico) {
        medicoDAO.excluir(id_medico);
    }

    public Medico buscarPorId(int id_medico) {
        return medicoDAO.buscarPorId(id_medico);
    }

    public List<Medico> listarTodos() {
        return medicoDAO.listarTodos();
    }
}