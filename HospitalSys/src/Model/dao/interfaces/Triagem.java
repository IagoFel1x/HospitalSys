package Model.dao.interfaces;

import Model.Triagem;
import java.sql.SQLException;

public interface TriagemDAO {
    int salvar(Triagem triagem) throws SQLException; // Retorna o ID
    Triagem buscarPorId(int id) throws SQLException;
}