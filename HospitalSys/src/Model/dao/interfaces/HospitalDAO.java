package Model.dao.interfaces;

import Model.Hospital;
import java.sql.SQLException;
import java.util.ArrayList;

public interface HospitalDAO {
    void salvar(Hospital hospital) throws SQLException;
    void atualizar(Hospital hospital) throws SQLException;
    void deletar(int id) throws SQLException;
    Hospital buscarPorId(int id) throws SQLException;
    ArrayList<Hospital> listarTodos() throws SQLException;
}