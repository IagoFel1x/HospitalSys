package Model.dao.interfaces;

import Model.Leito;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LeitoDAO {
    void salvar(Leito leito) throws SQLException;
    void atualizar(Leito leito) throws SQLException;
    void deletar(int id) throws SQLException;
    Leito buscarPorId(int id) throws SQLException;
    ArrayList<Leito> listarTodos() throws SQLException;
    
    // Métodos Específicos
    ArrayList<Leito> listarPorStatus(String status) throws SQLException;
    void atualizarStatus(int idLeito, String novoStatus) throws SQLException;
}