// HospitalDAO.java
package Model.dao.interfaces;

import Model.Hospital;
import java.util.List;

public interface HospitalDAO {
    void salvar(Hospital hospital);
    void atualizar(Hospital hospital);
    void excluir(int id_hospital);
    Hospital buscarPorId(int id_hospital);
    List<Hospital> listarTodos();
}