// FichaMedicaController.java
package Controller;

import Model.FichaMedica;
import Model.dao.impl.FichaMedicaDAOImpl;
import Model.dao.interfaces.FichaMedicaDAO;
import java.util.List;

public class FichaMedicaController {

    private final FichaMedicaDAO fichaMedicaDAO;

    public FichaMedicaController() {
        this.fichaMedicaDAO = new FichaMedicaDAOImpl();
    }

    public void salvar(FichaMedica fichaMedica) {
        fichaMedicaDAO.salvar(fichaMedica);
    }

    public void atualizar(FichaMedica fichaMedica) {
        fichaMedicaDAO.atualizar(fichaMedica);
    }

    public void excluir(int id_ficha) {
        fichaMedicaDAO.excluir(id_ficha);
    }

    public FichaMedica buscarPorId(int id_ficha) {
        return fichaMedicaDAO.buscarPorId(id_ficha);
    }

    public FichaMedica buscarPorIdPaciente(int id_paciente) {
        return fichaMedicaDAO.buscarPorIdPaciente(id_paciente);
    }

    public List<FichaMedica> listarTodos() {
        return fichaMedicaDAO.listarTodos();
    }
}