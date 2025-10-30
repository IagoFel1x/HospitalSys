// LeitoDAOImpl.java
package Model.dao.impl;

import Model.Leito;
import Model.dao.interfaces.LeitoDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeitoDAOImpl implements LeitoDAO {

    @Override
    public void salvar(Leito leito) {
        String sql = "INSERT INTO Leito (numero, tipo, status) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, leito.getNumero());
            stmt.setString(2, leito.getTipo());
            stmt.setString(3, leito.getStatus());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    leito.setId_leito(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar leito: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Leito leito) {
        String sql = "UPDATE Leito SET numero = ?, tipo = ?, status = ? WHERE id_leito = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, leito.getNumero());
            stmt.setString(2, leito.getTipo());
            stmt.setString(3, leito.getStatus());
            stmt.setInt(4, leito.getId_leito());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar leito: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_leito) {
        String sql = "DELETE FROM Leito WHERE id_leito = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_leito);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir leito: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Leito buscarPorId(int id_leito) {
        String sql = "SELECT * FROM Leito WHERE id_leito = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Leito leito = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_leito);
            rs = stmt.executeQuery();

            if (rs.next()) {
                leito = new Leito();
                leito.setId_leito(rs.getInt("id_leito"));
                leito.setNumero(rs.getString("numero"));
                leito.setTipo(rs.getString("tipo"));
                leito.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar leito por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return leito;
    }

    @Override
    public List<Leito> listarTodos() {
        String sql = "SELECT * FROM Leito";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Leito> leitos = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Leito leito = new Leito();
                leito.setId_leito(rs.getInt("id_leito"));
                leito.setNumero(rs.getString("numero"));
                leito.setTipo(rs.getString("tipo"));
                leito.setStatus(rs.getString("status"));
                leitos.add(leito);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar leitos: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return leitos;
    }
}