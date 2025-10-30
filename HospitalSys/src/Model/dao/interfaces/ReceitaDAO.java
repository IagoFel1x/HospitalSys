package Model.dao.interfaces;

import Model.Receita;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReceitaDAO {
    void salvar(Receita receita) throws SQLException;
    ArrayList<Receita> listarPorDiagnostico(int idDiagnostico) throws SQLException;
}