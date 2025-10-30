package Model.dao.interfaces;

import Model.FichaMedica;
import java.sql.SQLException;

public interface FichaMedicaDAO {
    void salvar(FichaMedica ficha) throws SQLException;
    void atualizar(FichaMedica ficha) throws SQLException;
    FichaMedica buscarPorId(int id) throws SQLException;
    
    // Método Chave (Relação 1:1)
    FichaMedica buscarPorPaciente(int idPaciente) throws SQLException;
}