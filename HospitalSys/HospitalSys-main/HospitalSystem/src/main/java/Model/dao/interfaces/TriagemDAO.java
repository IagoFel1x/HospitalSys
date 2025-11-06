// TriagemDAO.java
package Model.dao.interfaces;

import Model.Triagem;
import java.util.List;

public interface TriagemDAO {
    void salvar(Triagem triagem);
    void atualizar(Triagem triagem);
    void excluir(int id_triagem);
    Triagem buscarPorId(int id_triagem);
    List<Triagem> listarTodos();
}