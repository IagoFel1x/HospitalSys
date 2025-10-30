// PacienteController.java
package Controller;

import Model.Paciente;
import Model.dao.impl.PacienteDAOImpl;
import Model.dao.interfaces.PacienteDAO;
import java.util.List;

public class PacienteController {

    private final PacienteDAO pacienteDAO;

    public PacienteController() {
        this.pacienteDAO = new PacienteDAOImpl();
    }

    public void salvar(Paciente paciente) {
        pacienteDAO.salvar(paciente);
    }

    public void atualizar(Paciente paciente) {
        pacienteDAO.atualizar(paciente);
    }

    public void excluir(int id_paciente) {
        pacienteDAO.excluir(id_paciente);
    }

    public Paciente buscarPorId(int id_paciente) {
        return pacienteDAO.buscarPorId(id_paciente);
    }

    public List<Paciente> listarTodos() {
        return pacienteDAO.listarTodos();
    }
}