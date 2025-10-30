package Model.dao.interfaces;

import Model.Medicamento;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicamentoDAO {
    void salvar(Medicamento medicamento) throws SQLException;
    void atualizar(Medicamento medicamento) throws SQLException;
    void deletar(int id) throws SQLException;
    Medicamento buscarPorId(int id) throws SQLException;
    ArrayList<Medicamento> listarTodos() throws SQLException;
}