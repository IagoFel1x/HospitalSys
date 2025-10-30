package Model.dao.interfaces;

import Model.Funcionario;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FuncionarioDAO {
    void salvar(Funcionario funcionario) throws SQLException;
    void atualizar(Funcionario funcionario) throws SQLException;
    void deletar(int id) throws SQLException;
    Funcionario buscarPorId(int id) throws SQLException;
    ArrayList<Funcionario> listarTodos() throws SQLException;
}