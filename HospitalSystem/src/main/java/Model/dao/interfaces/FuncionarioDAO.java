// FuncionarioDAO.java
package Model.dao.interfaces;

import Model.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    void salvar(Funcionario funcionario);
    void atualizar(Funcionario funcionario);
    void excluir(int id_funcionario);
    Funcionario buscarPorId(int id_funcionario);
    List<Funcionario> listarTodos();
}