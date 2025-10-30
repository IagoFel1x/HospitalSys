package Model.dao.interfaces;

import Model.Exame;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ExameDAO {
    void salvar(Exame exame) throws SQLException;
    Exame buscarPorId(int id) throws SQLException;
    ArrayList<Exame> listarPorConsulta(int idConsulta) throws SQLException;
}