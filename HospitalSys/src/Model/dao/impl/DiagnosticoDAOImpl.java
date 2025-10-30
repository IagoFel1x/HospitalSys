package Model.dao.impl;

import Model.Diagnostico;
import Model.Exame;
import Model.dao.interfaces.DiagnosticoDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DiagnosticoDAOImpl implements DiagnosticoDAO {

    @Override
    public void salvar(Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT INTO Diagnostico (descricao_med, data, id_exame) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, diagnostico.getDescricaoMed());
            pstmt.setTimestamp(2, Timestamp.valueOf(diagnostico.getData()));
            pstmt.setInt(3, diagnostico.getExame().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Diagnostico buscarPorId(int id) throws SQLException {
        String sql = getSqlCompleto() + " WHERE d.id_diagnostico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToDiagnostico(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Diagnostico> listarPorExame(int idExame) throws SQLException {
        ArrayList<Diagnostico> diagnosticos = new ArrayList<>();
        String sql = getSqlCompleto() + " WHERE d.id_exame = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idExame);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    diagnosticos.add(resultSetToDiagnostico(rs));
                }
            }
        }
        return diagnosticos;
    }

    private String getSqlCompleto() {
        return "SELECT d.*, e.tipo_exame AS tipo_exame FROM Diagnostico d " +
               "JOIN Exame e ON d.id_exame = e.id_exame";
    }

    private Diagnostico resultSetToDiagnostico(ResultSet rs) throws SQLException {
        Diagnostico d = new Diagnostico();
        d.setId(rs.getInt("id_diagnostico"));
        d.setDescricaoMed(rs.getString("descricao_med"));
        d.setData(rs.getTimestamp("data").toLocalDateTime());
        
        Exame e = new Exame();
        e.setId(rs.getInt("id_exame"));
        e.setTipoExame(rs.getString("tipo_exame"));
        d.setExame(e);
        
        return d;
    }
}