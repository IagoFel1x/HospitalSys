// LeitoDAO.java
package Model.dao.interfaces;

import Model.Leito;
import java.util.List;

public interface LeitoDAO {
    void salvar(Leito leito);
    void atualizar(Leito leito);
    void excluir(int id_leito);
    Leito buscarPorId(int id_leito);
    List<Leito> listarTodos();
}