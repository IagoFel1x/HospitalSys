package Model.dao.interfaces;

import Model.Internacao;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InternacaoDAO {
    int salvar(Internacao internacao) throws SQLException; // Retorna o ID
    void finalizar(int idInternacao) throws SQLException;
    Internacao buscarPorId(int id) throws SQLException;
    ArrayList<Internacao> listarAtivas() throws SQLException;
    ArrayList<Internacao> listarTodas() throws SQLException;
}