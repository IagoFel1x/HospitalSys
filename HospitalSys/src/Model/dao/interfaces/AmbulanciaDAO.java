package Model.dao.interfaces;

import Model.Ambulancia;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AmbulanciaDAO {
    void salvar(Ambulancia ambulancia) throws SQLException;
    void atualizar(Ambulancia ambulancia) throws SQLException;
    void deletar(int id) throws SQLException;
    Ambulancia buscarPorId(int id) throws SQLException;
    ArrayList<Ambulancia> listarTodos() throws SQLException;
}