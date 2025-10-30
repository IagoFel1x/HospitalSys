// DiagnosticoDAOImpl.java
package Model.dao.impl;

import Model.Diagnostico;
import Model.Exame;
import Model.dao.interfaces.DiagnosticoDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAOImpl implements DiagnosticoDAO {

    @Override
    public void salvar(Diagnostico diagnostico) {
        String sql = "INSERT INTO Diagnostico (descricao_med, data, id_exame) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, diagnostico.getDescricao_med());
            stmt.setTimestamp(2, new Timestamp(diagnostico.getData().getTime()));
            stmt.setInt(3, diagnostico.getExame().getId_exame());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    diagnostico.setId_diagnostico(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar diagnóstico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Diagnostico diagnostico) {
        String sql = "UPDATE Diagnostico SET descricao_med = ?, data = ?, id_exame = ? WHERE id_diagnostico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, diagnostico.getDescricao_med());
            stmt.setTimestamp(2, new Timestamp(diagnostico.getData().getTime()));
            stmt.setInt(3, diagnostico.getExame().getId_exame());
            stmt.setInt(4, diagnostico.getId_diagnostico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar diagnóstico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_diagnostico) {
        String sql = "DELETE FROM Diagnostico WHERE id_diagnostico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_diagnostico);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir diagnóstico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Diagnostico buscarPorId(int id_diagnostico) {
        String sql = "SELECT * FROM Diagnostico WHERE id_diagnostico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Diagnostico diagnostico = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_diagnostico);
            rs = stmt.executeQuery();

            if (rs.next()) {
                diagnostico = new Diagnostico();
                diagnostico.setId_diagnostico(rs.getInt("id_diagnostico"));
                diagnostico.setDescricao_med(rs.getString("descricao_med"));
                diagnostico.setData(rs.getTimestamp("data"));

                Exame exame = new Exame();
                exame.setId_exame(rs.getInt("id_exame"));
                diagnostico.setExame(exame);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar diagnóstico por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return diagnostico;
    }

    @Override
    public List<Diagnostico> listarTodos() {
        String sql = "SELECT * FROM Diagnostico";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Diagnostico> diagnosticos = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setId_diagnostico(rs.getInt("id_diagnostico"));
                diagnostico.setDescricao_med(rs.getString("descricao_med"));
                diagnostico.setData(rs.getTimestamp("data"));

                Exame exame = new Exame();
                exame.setId_exame(rs.getInt("id_exame"));
                diagnostico.setExame(exame);
                
                diagnosticos.add(diagnostico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar diagnósticos: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return diagnosticos;
    }
}