// ReceitaDAO.java
package Model.dao.interfaces;

import Model.Receita;
import java.util.List;

public interface ReceitaDAO {
    void salvar(Receita receita);
    void atualizar(Receita receita);
    void excluir(int id_receita);
    Receita buscarPorId(int id_receita);
    List<Receita> listarTodos();
    List<Receita> listarPorDiagnostico(int id_diagnostico); // Método útil
}