// PlanoDeSaudeDAOImpl.java
package Model.dao.impl;

import Model.PlanoDeSaude;
import Model.dao.interfaces.PlanoDeSaudeDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlanoDeSaudeDAOImpl implements PlanoDeSaudeDAO {

    @Override
    public void salvar(PlanoDeSaude planoDeSaude) {
        String sql = "INSERT INTO PlanoDeSaude (nome, tipo, cobertura) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, planoDeSaude.getNome());
            stmt.setString(2, planoDeSaude.getTipo());
            stmt.setString(3, planoDeSaude.getCobertura());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    planoDeSaude.setId_plano_saude(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar plano de saúde: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(PlanoDeSaude planoDeSaude) {
        String sql = "UPDATE PlanoDeSaude SET nome = ?, tipo = ?, cobertura = ? WHERE id_plano_saude = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, planoDeSaude.getNome());
            stmt.setString(2, planoDeSaude.getTipo());
            stmt.setString(3, planoDeSaude.getCobertura());
            stmt.setInt(4, planoDeSaude.getId_plano_saude());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar plano de saúde: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_plano_saude) {
        String sql = "DELETE FROM PlanoDeSaude WHERE id_plano_saude = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_plano_saude);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir plano de saúde: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public PlanoDeSaude buscarPorId(int id_plano_saude) {
        String sql = "SELECT * FROM PlanoDeSaude WHERE id_plano_saude = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PlanoDeSaude plano = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_plano_saude);
            rs = stmt.executeQuery();

            if (rs.next()) {
                plano = new PlanoDeSaude();
                plano.setId_plano_saude(rs.getInt("id_plano_saude"));
                plano.setNome(rs.getString("nome"));
                plano.setTipo(rs.getString("tipo"));
                plano.setCobertura(rs.getString("cobertura"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar plano de saúde por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return plano;
    }

    @Override
    public List<PlanoDeSaude> listarTodos() {
        String sql = "SELECT * FROM PlanoDeSaude";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PlanoDeSaude> planos = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PlanoDeSaude plano = new PlanoDeSaude();
                plano.setId_plano_saude(rs.getInt("id_plano_saude"));
                plano.setNome(rs.getString("nome"));
                plano.setTipo(rs.getString("tipo"));
                plano.setCobertura(rs.getString("cobertura"));
                planos.add(plano);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar planos de saúde: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return planos;
    }
}