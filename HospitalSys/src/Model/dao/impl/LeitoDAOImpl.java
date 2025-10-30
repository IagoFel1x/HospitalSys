package Model.dao.impl;

import Model.Leito;
import Model.dao.interfaces.LeitoDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeitoDAOImpl implements LeitoDAO {

    @Override
    public void salvar(Leito leito) throws SQLException {
        String sql = "INSERT INTO Leito (numero, tipo, status) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leito.getNumero());
            pstmt.setString(2, leito.getTipo());
            pstmt.setString(3, leito.getStatus());
            pstmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Leito> listarTodos() throws SQLException {
        ArrayList<Leito> leitos = new ArrayList<>();
        String sql = "SELECT * FROM Leito";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                leitos.add(resultSetToLeito(rs));
            }
        }
        return leitos;
    }
    
    @Override
    public ArrayList<Leito> listarPorStatus(String status) throws SQLException {
         ArrayList<Leito> leitos = new ArrayList<>();
        String sql = "SELECT * FROM Leito WHERE status = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    leitos.add(resultSetToLeito(rs));
                }
            }
        }
        return leitos;
    }

    @Override
    public void atualizar(Leito leito) throws SQLException {
        String sql = "UPDATE Leito SET numero = ?, tipo = ?, status = ? WHERE id_leito = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leito.getNumero());
            pstmt.setString(2, leito.getTipo());
            pstmt.setString(3, leito.getStatus());
            pstmt.setInt(4, leito.getId());
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public void atualizarStatus(int idLeito, String novoStatus) throws SQLException {
        String sql = "UPDATE Leito SET status = ? WHERE id_leito = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novoStatus);
            pstmt.setInt(2, idLeito);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Leito WHERE id_leito = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Leito buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Leito WHERE id_leito = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToLeito(rs);
                }
            }
        }
        return null;
    }

    private Leito resultSetToLeito(ResultSet rs) throws SQLException {
        Leito l = new Leito();
        l.setId(rs.getInt("id_leito"));
        l.setNumero(rs.getString("numero"));
        l.setTipo(rs.getString("tipo"));
        l.setStatus(rs.getString("status"));
        return l;
    }
}