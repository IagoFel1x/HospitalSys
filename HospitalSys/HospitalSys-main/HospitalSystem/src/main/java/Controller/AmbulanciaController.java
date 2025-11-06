// AmbulanciaController.java
package Controller;

import Model.Ambulancia;
import Model.dao.impl.AmbulanciaDAOImpl;
import Model.dao.interfaces.AmbulanciaDAO;
import java.util.List;

public class AmbulanciaController {

    private final AmbulanciaDAO ambulanciaDAO;

    public AmbulanciaController() {
        this.ambulanciaDAO = new AmbulanciaDAOImpl();
    }

    public void salvar(Ambulancia ambulancia) {
        ambulanciaDAO.salvar(ambulancia);
    }

    public void atualizar(Ambulancia ambulancia) {
        ambulanciaDAO.atualizar(ambulancia);
    }

    public void excluir(int id_ambulancia) {
        ambulanciaDAO.excluir(id_ambulancia);
    }

    public Ambulancia buscarPorId(int id_ambulancia) {
        return ambulanciaDAO.buscarPorId(id_ambulancia);
    }

    public List<Ambulancia> listarTodos() {
        return ambulanciaDAO.listarTodos();
    }
}