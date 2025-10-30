// TriagemDAOImpl.java
package Model.dao.impl;

import Model.Triagem;
import Model.dao.interfaces.TriagemDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TriagemDAOImpl implements TriagemDAO {

    @Override
    public void salvar(Triagem triagem) {
        String sql = "INSERT INTO Triagem (pressao_arterial, temperatura, peso, altura, obs) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, triagem.getPressao_arterial());
            stmt.setDouble(2, triagem.getTemperatura());
            stmt.setDouble(3, triagem.getPeso());
            stmt.setDouble(4, triagem.getAltura());
            stmt.setString(5, triagem.getObs());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    triagem.setId_triagem(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar triagem: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Triagem triagem) {
        String sql = "UPDATE Triagem SET pressao_arterial = ?, temperatura = ?, peso = ?, altura = ?, obs = ? WHERE id_triagem = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, triagem.getPressao_arterial());
            stmt.setDouble(2, triagem.getTemperatura());
            stmt.setDouble(3, triagem.getPeso());
            stmt.setDouble(4, triagem.getAltura());
            stmt.setString(5, triagem.getObs());
            stmt.setInt(6, triagem.getId_triagem());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar triagem: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_triagem) {
        String sql = "DELETE FROM Triagem WHERE id_triagem = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_triagem);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir triagem: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Triagem buscarPorId(int id_triagem) {
        String sql = "SELECT * FROM Triagem WHERE id_triagem = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Triagem triagem = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_triagem);
            rs = stmt.executeQuery();

            if (rs.next()) {
                triagem = new Triagem();
                triagem.setId_triagem(rs.getInt("id_triagem"));
                triagem.setPressao_arterial(rs.getString("pressao_arterial"));
                triagem.setTemperatura(rs.getDouble("temperatura"));
                triagem.setPeso(rs.getDouble("peso"));
                triagem.setAltura(rs.getDouble("altura"));
                triagem.setObs(rs.getString("obs"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar triagem por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return triagem;
    }

    @Override
    public List<Triagem> listarTodos() {
        String sql = "SELECT * FROM Triagem";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Triagem> triagens = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Triagem triagem = new Triagem();
                triagem.setId_triagem(rs.getInt("id_triagem"));
                triagem.setPressao_arterial(rs.getString("pressao_arterial"));
                triagem.setTemperatura(rs.getDouble("temperatura"));
                triagem.setPeso(rs.getDouble("peso"));
                triagem.setAltura(rs.getDouble("altura"));
                triagem.setObs(rs.getString("obs"));
                triagens.add(triagem);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar triagens: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return triagens;
    }
}