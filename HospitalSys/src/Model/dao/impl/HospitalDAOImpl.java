package Model.dao.impl;

import Model.Hospital;
import Model.dao.interfaces.HospitalDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HospitalDAOImpl implements HospitalDAO {

    @Override
    public void salvar(Hospital hospital) throws SQLException {
        String sql = "INSERT INTO Hospital (nome, endereco, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hospital.getNome());
            pstmt.setString(2, hospital.getEndereco());
            pstmt.setString(3, hospital.getTelefone());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Hospital hospital) throws SQLException {
        String sql = "UPDATE Hospital SET nome = ?, endereco = ?, telefone = ? WHERE id_hospital = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hospital.getNome());
            pstmt.setString(2, hospital.getEndereco());
            pstmt.setString(3, hospital.getTelefone());
            pstmt.setInt(4, hospital.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Hospital WHERE id_hospital = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Hospital buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Hospital WHERE id_hospital = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToHospital(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Hospital> listarTodos() throws SQLException {
        ArrayList<Hospital> hospitais = new ArrayList<>();
        String sql = "SELECT * FROM Hospital";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                hospitais.add(resultSetToHospital(rs));
            }
        }
        return hospitais;
    }
    
    private Hospital resultSetToHospital(ResultSet rs) throws SQLException {
        Hospital h = new Hospital();
        h.setId(rs.getInt("id_hospital"));
        h.setNome(rs.getString("nome"));
        h.setEndereco(rs.getString("endereco"));
        h.setTelefone(rs.getString("telefone"));
        return h;
    }
}