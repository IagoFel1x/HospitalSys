package Model.dao.impl;

import Model.Consulta;
import Model.Exame;
import Model.dao.interfaces.ExameDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ExameDAOImpl implements ExameDAO {

    @Override
    public void salvar(Exame exame) throws SQLException {
        String sql = "INSERT INTO Exame (tipo_exame, data, tipo_resultado, id_consulta) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, exame.getTipoExame());
            pstmt.setTimestamp(2, Timestamp.valueOf(exame.getData()));
            pstmt.setString(3, exame.getTipoResultado());
            pstmt.setInt(4, exame.getConsulta().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Exame buscarPorId(int id) throws SQLException {
        String sql = getSqlCompleto() + " WHERE e.id_exame = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToExame(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Exame> listarPorConsulta(int idConsulta) throws SQLException {
        ArrayList<Exame> exames = new ArrayList<>();
        String sql = getSqlCompleto() + " WHERE e.id_consulta = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idConsulta);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    exames.add(resultSetToExame(rs));
                }
            }
        }
        return exames;
    }
    
    private String getSqlCompleto() {
        return "SELECT e.*, c.diagnostico_geral AS diag_consulta FROM Exame e " +
               "JOIN Consulta c ON e.id_consulta = c.id_consulta";
    }

    private Exame resultSetToExame(ResultSet rs) throws SQLException {
        Exame e = new Exame();
        e.setId(rs.getInt("id_exame"));
        e.setTipoExame(rs.getString("tipo_exame"));
        e.setData(rs.getTimestamp("data").toLocalDateTime());
        e.setTipoResultado(rs.getString("tipo_resultado"));
        
        Consulta c = new Consulta();
        c.setId(rs.getInt("id_consulta"));
        c.setDiagnosticoGeral(rs.getString("diag_consulta"));
        e.setConsulta(c);
        
        return e;
    }
}