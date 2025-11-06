// MedicoDAO.java
package Model.dao.interfaces;

import Model.Medico;
import java.util.List;

public interface MedicoDAO {
    void salvar(Medico medico);
    void atualizar(Medico medico);
    void excluir(int id_medico);
    Medico buscarPorId(int id_medico);
    List<Medico> listarTodos();
}