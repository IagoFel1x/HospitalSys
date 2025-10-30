package Model.dao.interfaces;

import Model.Diagnostico;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DiagnosticoDAO {
    void salvar(Diagnostico diagnostico) throws SQLException;
    Diagnostico buscarPorId(int id) throws SQLException;
    ArrayList<Diagnostico> listarPorExame(int idExame) throws SQLException;
}