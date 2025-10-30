package Model.dao.impl;

import Model.Ambulancia;
import Model.Hospital;
import Model.dao.interfaces.AmbulanciaDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmbulanciaDAOImpl implements AmbulanciaDAO {

    @Override
    public void salvar(Ambulancia ambulancia) throws SQLException {
        String sql = "INSERT INTO Ambulancia (placa, modelo, ano, id_hospital) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ambulancia.getPlaca());
            pstmt.setString(2, ambulancia.getModelo());
            pstmt.setInt(3, ambulancia.getAno());
            pstmt.setInt(4, ambulancia.getHospital().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Ambulancia ambulancia) throws SQLException {
        String sql = "UPDATE Ambulancia SET placa = ?, modelo = ?, ano = ?, id_hospital = ? WHERE id_ambulancia = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ambulancia.getPlaca());
            pstmt.setString(2, ambulancia.getModelo());
            pstmt.setInt(3, ambulancia.getAno());
            pstmt.setInt(4, ambulancia.getHospital().getId());
            pstmt.setInt(5, ambulancia.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Ambulancia WHERE id_ambulancia = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Ambulancia buscarPorId(int id) throws SQLException {
        String sql = getSqlCompleto() + " WHERE a.id_ambulancia = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToAmbulancia(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Ambulancia> listarTodos() throws SQLException {
        ArrayList<Ambulancia> ambulancias = new ArrayList<>();
        String sql = getSqlCompleto();
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ambulancias.add(resultSetToAmbulancia(rs));
            }
        }
        return ambulancias;
    }

    private String getSqlCompleto() {
        return "SELECT a.*, h.nome AS nome_hospital FROM Ambulancia a " +
               "JOIN Hospital h ON a.id_hospital = h.id_hospital";
    }

    private Ambulancia resultSetToAmbulancia(ResultSet rs) throws SQLException {
        Ambulancia a = new Ambulancia();
        a.setId(rs.getInt("id_ambulancia"));
        a.setPlaca(rs.getString("placa"));
        a.setModelo(rs.getString("modelo"));
        a.setAno(rs.getInt("ano"));
        
        Hospital h = new Hospital();
        h.setId(rs.getInt("id_hospital"));
        h.setNome(rs.getString("nome_hospital"));
        a.setHospital(h);
        
        return a;
    }
}