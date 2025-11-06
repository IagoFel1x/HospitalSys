// PlanoDeSaudeController.java
package Controller;

import Model.PlanoDeSaude;
import Model.dao.impl.PlanoDeSaudeDAOImpl;
import Model.dao.interfaces.PlanoDeSaudeDAO;
import java.util.List;

public class PlanoDeSaudeController {

    private final PlanoDeSaudeDAO planoDeSaudeDAO;

    public PlanoDeSaudeController() {
        this.planoDeSaudeDAO = new PlanoDeSaudeDAOImpl();
    }

    public void salvar(PlanoDeSaude planoDeSaude) {
        planoDeSaudeDAO.salvar(planoDeSaude);
    }

    public void atualizar(PlanoDeSaude planoDeSaude) {
        planoDeSaudeDAO.atualizar(planoDeSaude);
    }

    public void excluir(int id_plano_saude) {
        planoDeSaudeDAO.excluir(id_plano_saude);
    }

    public PlanoDeSaude buscarPorId(int id_plano_saude) {
        return planoDeSaudeDAO.buscarPorId(id_plano_saude);
    }

    public List<PlanoDeSaude> listarTodos() {
        return planoDeSaudeDAO.listarTodos();
    }
}