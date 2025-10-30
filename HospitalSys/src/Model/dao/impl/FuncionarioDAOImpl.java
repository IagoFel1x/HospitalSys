package Model.dao.impl;

import Model.Funcionario;
import Model.Hospital;
import Model.dao.interfaces.FuncionarioDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    @Override
    public void salvar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (nome, cargo, cpf, telefone, email, id_hospital) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCargo());
            pstmt.setString(3, funcionario.getCpf());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getEmail());
            pstmt.setInt(6, funcionario.getHospital().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionario SET nome = ?, cargo = ?, cpf = ?, telefone = ?, email = ?, id_hospital = ? WHERE id_funcionario = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCargo());
            pstmt.setString(3, funcionario.getCpf());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getEmail());
            pstmt.setInt(6, funcionario.getHospital().getId());
            pstmt.setInt(7, funcionario.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = getSqlCompleto() + " WHERE f.id_funcionario = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToFuncionario(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Funcionario> listarTodos() throws SQLException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = getSqlCompleto();
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                funcionarios.add(resultSetToFuncionario(rs));
            }
        }
        return funcionarios;
    }
    
    private String getSqlCompleto() {
        return "SELECT f.*, h.nome AS nome_hospital FROM Funcionario f " +
               "JOIN Hospital h ON f.id_hospital = h.id_hospital";
    }

    private Funcionario resultSetToFuncionario(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setId(rs.getInt("id_funcionario"));
        f.setNome(rs.getString("nome"));
        f.setCargo(rs.getString("cargo"));
        f.setCpf(rs.getString("cpf"));
        f.setTelefone(rs.getString("telefone"));
        f.setEmail(rs.getString("email"));
        
        Hospital h = new Hospital();
        h.setId(rs.getInt("id_hospital"));
        h.setNome(rs.getString("nome_hospital"));
        f.setHospital(h);
        
        return f;
    }
}