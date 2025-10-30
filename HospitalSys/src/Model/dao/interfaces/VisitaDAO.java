package Model.dao.interfaces;

import Model.Visita;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VisitaDAO {
    void salvar(Visita visita) throws SQLException;
    void deletar(int id) throws SQLException;
    ArrayList<Visita> listarPorPaciente(int idPaciente) throws SQLException;
}