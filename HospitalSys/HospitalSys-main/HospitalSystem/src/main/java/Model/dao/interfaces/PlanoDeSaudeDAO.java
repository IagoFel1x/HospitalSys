// PlanoDeSaudeDAO.java
package Model.dao.interfaces;

import Model.PlanoDeSaude;
import java.util.List;

public interface PlanoDeSaudeDAO {
    void salvar(PlanoDeSaude planoDeSaude);
    void atualizar(PlanoDeSaude planoDeSaude);
    void excluir(int id_plano_saude);
    PlanoDeSaude buscarPorId(int id_plano_saude);
    List<PlanoDeSaude> listarTodos();
}