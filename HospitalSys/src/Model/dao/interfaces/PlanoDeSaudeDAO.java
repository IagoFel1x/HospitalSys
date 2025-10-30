package Model.dao.interfaces;

import Model.PlanoDeSaude;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlanoDeSaudeDAO {
    void salvar(PlanoDeSaude plano) throws SQLException;
    void atualizar(PlanoDeSaude plano) throws SQLException;
    void deletar(int id) throws SQLException;
    PlanoDeSaude buscarPorId(int id) throws SQLException;
    ArrayList<PlanoDeSaude> listarTodos() throws SQLException;
}