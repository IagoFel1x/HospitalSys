// DiagnosticoDAO.java
package Model.dao.interfaces;

import Model.Diagnostico;
import java.util.List;

public interface DiagnosticoDAO {
    void salvar(Diagnostico diagnostico);
    void atualizar(Diagnostico diagnostico);
    void excluir(int id_diagnostico);
    Diagnostico buscarPorId(int id_diagnostico);
    List<Diagnostico> listarTodos();
}