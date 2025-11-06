// ConsultaController.java
package Controller;

import Model.Consulta;
import Model.dao.impl.ConsultaDAOImpl;
import Model.dao.interfaces.ConsultaDAO;
import java.util.List;

public class ConsultaController {

    private final ConsultaDAO consultaDAO;

    public ConsultaController() {
        this.consultaDAO = new ConsultaDAOImpl();
    }

    public void salvar(Consulta consulta) {
        consultaDAO.salvar(consulta);
    }

    public void atualizar(Consulta consulta) {
        consultaDAO.atualizar(consulta);
    }

    public void excluir(int id_consulta) {
        consultaDAO.excluir(id_consulta);
    }

    public Consulta buscarPorId(int id_consulta) {
        return consultaDAO.buscarPorId(id_consulta);
    }

    public List<Consulta> listarTodos() {
        return consultaDAO.listarTodos();
    }

    public List<Consulta> listarPorPaciente(int id_paciente) {
        return consultaDAO.listarPorPaciente(id_paciente);
    }

    public List<Consulta> listarPorMedico(int id_medico) {
        return consultaDAO.listarPorMedico(id_medico);
    }
}