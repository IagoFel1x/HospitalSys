// MedicamentoDAOImpl.java
package Model.dao.impl;

import Model.Medicamento;
import Model.dao.interfaces.MedicamentoDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAOImpl implements MedicamentoDAO {

    @Override
    public void salvar(Medicamento medicamento) {
        String sql = "INSERT INTO Medicamento (nome, fabricante, descricao) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getFabricante());
            stmt.setString(3, medicamento.getDescricao());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    medicamento.setId_medicamento(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar medicamento: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Medicamento medicamento) {
        String sql = "UPDATE Medicamento SET nome = ?, fabricante = ?, descricao = ? WHERE id_medicamento = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getFabricante());
            stmt.setString(3, medicamento.getDescricao());
            stmt.setInt(4, medicamento.getId_medicamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar medicamento: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_medicamento) {
        String sql = "DELETE FROM Medicamento WHERE id_medicamento = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_medicamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir medicamento: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Medicamento buscarPorId(int id_medicamento) {
        String sql = "SELECT * FROM Medicamento WHERE id_medicamento = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medicamento medicamento = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_medicamento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                medicamento = new Medicamento();
                medicamento.setId_medicamento(rs.getInt("id_medicamento"));
                medicamento.setNome(rs.getString("nome"));
                medicamento.setFabricante(rs.getString("fabricante"));
                medicamento.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar medicamento por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return medicamento;
    }

    @Override
    public List<Medicamento> listarTodos() {
        String sql = "SELECT * FROM Medicamento";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medicamento> medicamentos = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setId_medicamento(rs.getInt("id_medicamento"));
                medicamento.setNome(rs.getString("nome"));
                medicamento.setFabricante(rs.getString("fabricante"));
                medicamento.setDescricao(rs.getString("descricao"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar medicamentos: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return medicamentos;
    }
}