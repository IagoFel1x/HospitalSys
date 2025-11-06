// ExameDAOImpl.java
package Model.dao.impl;

import Model.Exame;
import Model.Consulta;
import Model.dao.interfaces.ExameDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExameDAOImpl implements ExameDAO {

    @Override
    public void salvar(Exame exame) {
        String sql = "INSERT INTO Exame (tipo_exame, data, tipo_resultado, id_consulta) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, exame.getTipo_exame());
            stmt.setTimestamp(2, new Timestamp(exame.getData().getTime()));
            stmt.setString(3, exame.getTipo_resultado());
            stmt.setInt(4, exame.getConsulta().getId_consulta());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    exame.setId_exame(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar exame: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Exame exame) {
        String sql = "UPDATE Exame SET tipo_exame = ?, data = ?, tipo_resultado = ?, id_consulta = ? WHERE id_exame = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, exame.getTipo_exame());
            stmt.setTimestamp(2, new Timestamp(exame.getData().getTime()));
            stmt.setString(3, exame.getTipo_resultado());
            stmt.setInt(4, exame.getConsulta().getId_consulta());
            stmt.setInt(5, exame.getId_exame());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar exame: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_exame) {
        String sql = "DELETE FROM Exame WHERE id_exame = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_exame);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir exame: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Exame buscarPorId(int id_exame) {
        String sql = "SELECT * FROM Exame WHERE id_exame = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Exame exame = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_exame);
            rs = stmt.executeQuery();

            if (rs.next()) {
                exame = instanciarExame(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar exame por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return exame;
    }

    @Override
    public List<Exame> listarTodos() {
        String sql = "SELECT * FROM Exame";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Exame> exames = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Exame exame = instanciarExame(rs);
                exames.add(exame);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar exames: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return exames;
    }

    @Override
    public List<Exame> listarPorConsulta(int id_consulta) {
        String sql = "SELECT * FROM Exame WHERE id_consulta = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Exame> exames = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_consulta);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Exame exame = instanciarExame(rs);
                exames.add(exame);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar exames por consulta: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return exames;
    }

    private Exame instanciarExame(ResultSet rs) throws SQLException {
        Exame exame = new Exame();
        exame.setId_exame(rs.getInt("id_exame"));
        exame.setTipo_exame(rs.getString("tipo_exame"));
        exame.setData(rs.getTimestamp("data"));
        exame.setTipo_resultado(rs.getString("tipo_resultado"));

        Consulta consulta = new Consulta();
        consulta.setId_consulta(rs.getInt("id_consulta"));
        exame.setConsulta(consulta);
        
        return exame;
    }
}