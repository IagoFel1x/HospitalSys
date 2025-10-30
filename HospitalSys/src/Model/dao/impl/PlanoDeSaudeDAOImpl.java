package Model.dao.impl;

import Model.PlanoDeSaude;
import Model.dao.interfaces.PlanoDeSaudeDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlanoDeSaudeDAOImpl implements PlanoDeSaudeDAO {

    @Override
    public void salvar(PlanoDeSaude plano) throws SQLException {
        String sql = "INSERT INTO PlanoDeSaude (nome, tipo, cobertura) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plano.getNome());
            pstmt.setString(2, plano.getTipo());
            pstmt.setString(3, plano.getCobertura());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(PlanoDeSaude plano) throws SQLException {
        String sql = "UPDATE PlanoDeSaude SET nome = ?, tipo = ?, cobertura = ? WHERE id_plano_saude = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plano.getNome());
            pstmt.setString(2, plano.getTipo());
            pstmt.setString(3, plano.getCobertura());
            pstmt.setInt(4, plano.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM PlanoDeSaude WHERE id_plano_saude = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public PlanoDeSaude buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM PlanoDeSaude WHERE id_plano_saude = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToPlanoDeSaude(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<PlanoDeSaude> listarTodos() throws SQLException {
        ArrayList<PlanoDeSaude> planos = new ArrayList<>();
        String sql = "SELECT * FROM PlanoDeSaude";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                planos.add(resultSetToPlanoDeSaude(rs));
            }
        }
        return planos;
    }
    
    private PlanoDeSaude resultSetToPlanoDeSaude(ResultSet rs) throws SQLException {
        PlanoDeSaude p = new PlanoDeSaude();
        p.setId(rs.getInt("id_plano_saude"));
        p.setNome(rs.getString("nome"));
        p.setTipo(rs.getString("tipo"));
        p.setCobertura(rs.getString("cobertura"));
        return p;
    }
}