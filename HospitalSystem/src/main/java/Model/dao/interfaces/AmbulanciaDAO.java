// AmbulanciaDAO.java
package Model.dao.interfaces;

import Model.Ambulancia;
import java.util.List;

public interface AmbulanciaDAO {
    void salvar(Ambulancia ambulancia);
    void atualizar(Ambulancia ambulancia);
    void excluir(int id_ambulancia);
    Ambulancia buscarPorId(int id_ambulancia);
    List<Ambulancia> listarTodos();
}