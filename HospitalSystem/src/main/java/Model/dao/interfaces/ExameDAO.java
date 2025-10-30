// ExameDAO.java
package Model.dao.interfaces;

import Model.Exame;
import java.util.List;

public interface ExameDAO {
    void salvar(Exame exame);
    void atualizar(Exame exame);
    void excluir(int id_exame);
    Exame buscarPorId(int id_exame);
    List<Exame> listarTodos();
    List<Exame> listarPorConsulta(int id_consulta); // Método útil
}