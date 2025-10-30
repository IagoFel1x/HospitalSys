package Model.dao.interfaces;

import Model.Consulta;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ConsultaDAO {
    void salvar(Consulta consulta) throws SQLException;
    void atualizar(Consulta consulta) throws SQLException;
    void deletar(int id) throws SQLException;
    Consulta buscarPorId(int id) throws SQLException;
    ArrayList<Consulta> listarTodos() throws SQLException;
}