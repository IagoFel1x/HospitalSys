// FuncionarioController.java
package Controller;

import Model.Funcionario;
import Model.dao.impl.FuncionarioDAOImpl;
import Model.dao.interfaces.FuncionarioDAO;
import java.util.List;

public class FuncionarioController {

    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAOImpl();
    }

    public void salvar(Funcionario funcionario) {
        funcionarioDAO.salvar(funcionario);
    }

    public void atualizar(Funcionario funcionario) {
        funcionarioDAO.atualizar(funcionario);
    }

    public void excluir(int id_funcionario) {
        funcionarioDAO.excluir(id_funcionario);
    }

    public Funcionario buscarPorId(int id_funcionario) {
        return funcionarioDAO.buscarPorId(id_funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioDAO.listarTodos();
    }
}