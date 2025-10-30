package Model.dao.impl;

import Model.Triagem;
import Model.dao.interfaces.TriagemDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TriagemDAOImpl implements TriagemDAO {

    @Override
    public int salvar(Triagem triagem) throws SQLException {
        String sql = "INSERT INTO Triagem (pressao_arterial, temperatura, peso, altura, obs) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, triagem.getPressaoArterial());
            pstmt.setDouble(2, triagem.getTemperatura());
            pstmt.setDouble(3, triagem.getPeso());
            pstmt.setDouble(4, triagem.getAltura());
            pstmt.setString(5, triagem.getObs());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retorna o ID gerado
                }
            }
        }
        return 0;
    }

    @Override
    public Triagem buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Triagem WHERE id_triagem = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToTriagem(rs);
                }
            }
        }
        return null;
    }

    private Triagem resultSetToTriagem(ResultSet rs) throws SQLException {
        Triagem t = new Triagem();
        t.setId(rs.getInt("id_triagem"));
        t.setPressaoArterial(rs.getString("pressao_arterial"));
        t.setTemperatura(rs.getDouble("temperatura"));
        t.setPeso(rs.getDouble("peso"));
        t.setAltura(rs.getDouble("altura"));
        t.setObs(rs.getString("obs"));
        return t;
    }
}