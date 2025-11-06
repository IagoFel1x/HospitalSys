// MedicoDAOImpl.java
package Model.dao.impl;

import Model.Medico;
import Model.dao.interfaces.MedicoDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAOImpl implements MedicoDAO {

    @Override
    public void salvar(Medico medico) {
        String sql = "INSERT INTO Medico (nome, especialidade, crm, telefone, email) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setString(4, medico.getTelefone());
            stmt.setString(5, medico.getEmail());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    medico.setId_medico(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar médico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Medico medico) {
        String sql = "UPDATE Medico SET nome = ?, especialidade = ?, crm = ?, telefone = ?, email = ? WHERE id_medico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setString(4, medico.getTelefone());
            stmt.setString(5, medico.getEmail());
            stmt.setInt(6, medico.getId_medico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar médico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_medico) {
        String sql = "DELETE FROM Medico WHERE id_medico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_medico);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir médico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Medico buscarPorId(int id_medico) {
        String sql = "SELECT * FROM Medico WHERE id_medico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medico medico = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_medico);
            rs = stmt.executeQuery();

            if (rs.next()) {
                medico = new Medico();
                medico.setId_medico(rs.getInt("id_medico"));
                medico.setNome(rs.getString("nome"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setCrm(rs.getString("crm"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médico por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return medico;
    }

    @Override
    public List<Medico> listarTodos() {
        String sql = "SELECT * FROM Medico";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medico> medicos = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId_medico(rs.getInt("id_medico"));
                medico.setNome(rs.getString("nome"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setCrm(rs.getString("crm"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEmail(rs.getString("email"));
                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar médicos: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return medicos;
    }
}