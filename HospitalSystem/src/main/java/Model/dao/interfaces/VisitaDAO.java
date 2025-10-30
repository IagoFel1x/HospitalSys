// VisitaDAO.java
package Model.dao.interfaces;

import Model.Visita;
import java.util.List;

public interface VisitaDAO {
    void salvar(Visita visita);
    void atualizar(Visita visita);
    void excluir(int id_visita);
    Visita buscarPorId(int id_visita);
    List<Visita> listarTodos();
}