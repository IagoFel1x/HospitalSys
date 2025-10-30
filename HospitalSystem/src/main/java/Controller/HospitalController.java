// HospitalController.java
package Controller;

import Model.Hospital;
import Model.dao.impl.HospitalDAOImpl;
import Model.dao.interfaces.HospitalDAO;
import java.util.List;

public class HospitalController {

    private final HospitalDAO hospitalDAO;

    public HospitalController() {
        this.hospitalDAO = new HospitalDAOImpl();
    }

    public void salvar(Hospital hospital) {
        hospitalDAO.salvar(hospital);
    }

    public void atualizar(Hospital hospital) {
        hospitalDAO.atualizar(hospital);
    }

    public void excluir(int id_hospital) {
        hospitalDAO.excluir(id_hospital);
    }

    public Hospital buscarPorId(int id_hospital) {
        return hospitalDAO.buscarPorId(id_hospital);
    }

    public List<Hospital> listarTodos() {
        return hospitalDAO.listarTodos();
    }
}