package Model.dao.impl;

import Model.Medicamento;
import Model.dao.interfaces.MedicamentoDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentoDAOImpl implements MedicamentoDAO {

    @Override
    public void salvar(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO Medicamento (nome, fabricante, descricao) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, medicamento.getNome());
            pstmt.setString(2, medicamento.getFabricante());
            pstmt.setString(3, medicamento.getDescricao());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE Medicamento SET nome = ?, fabricante = ?, descricao = ? WHERE id_medicamento = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, medicamento.getNome());
            pstmt.setString(2, medicamento.getFabricante());
            pstmt.setString(3, medicamento.getDescricao());
            pstmt.setInt(4, medicamento.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Medicamento WHERE id_medicamento = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Medicamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Medicamento WHERE id_medicamento = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToMedicamento(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Medicamento> listarTodos() throws SQLException {
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                medicamentos.add(resultSetToMedicamento(rs));
            }
        }
        return medicamentos;
    }

    private Medicamento resultSetToMedicamento(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento();
        m.setId(rs.getInt("id_medicamento"));
        m.setNome(rs.getString("nome"));
        m.setFabricante(rs.getString("fabricante"));
        m.setDescricao(rs.getString("descricao"));
        return m;
    }
}