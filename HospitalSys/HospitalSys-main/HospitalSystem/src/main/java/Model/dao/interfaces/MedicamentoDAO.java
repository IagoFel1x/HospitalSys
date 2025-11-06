// MedicamentoDAO.java
package Model.dao.interfaces;

import Model.Medicamento;
import java.util.List;

public interface MedicamentoDAO {
    void salvar(Medicamento medicamento);
    void atualizar(Medicamento medicamento);
    void excluir(int id_medicamento);
    Medicamento buscarPorId(int id_medicamento);
    List<Medicamento> listarTodos();
}