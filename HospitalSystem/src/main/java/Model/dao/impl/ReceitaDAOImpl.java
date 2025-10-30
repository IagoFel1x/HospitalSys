// ReceitaDAOImpl.java
package Model.dao.impl;

import Model.Receita;
import Model.Diagnostico;
import Model.Medicamento;
import Model.dao.interfaces.ReceitaDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAOImpl implements ReceitaDAO {

    @Override
    public void salvar(Receita receita) {
        String sql = "INSERT INTO Receita (dosagem, frequencia, duracao, id_diagnostico, id_medicamento) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, receita.getDosagem());
            stmt.setString(2, receita.getFrequencia());
            stmt.setString(3, receita.getDuracao());
            stmt.setInt(4, receita.getDiagnostico().getId_diagnostico());
            stmt.setInt(5, receita.getMedicamento().getId_medicamento());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    receita.setId_receita(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar receita: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Receita receita) {
        String sql = "UPDATE Receita SET dosagem = ?, frequencia = ?, duracao = ?, id_diagnostico = ?, id_medicamento = ? WHERE id_receita = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, receita.getDosagem());
            stmt.setString(2, receita.getFrequencia());
            stmt.setString(3, receita.getDuracao());
            stmt.setInt(4, receita.getDiagnostico().getId_diagnostico());
            stmt.setInt(5, receita.getMedicamento().getId_medicamento());
            stmt.setInt(6, receita.getId_receita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar receita: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_receita) {
        String sql = "DELETE FROM Receita WHERE id_receita = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_receita);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir receita: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Receita buscarPorId(int id_receita) {
        String sql = "SELECT * FROM Receita WHERE id_receita = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Receita receita = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_receita);
            rs = stmt.executeQuery();

            if (rs.next()) {
                receita = instanciarReceita(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar receita por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return receita;
    }

    @Override
    public List<Receita> listarTodos() {
        String sql = "SELECT * FROM Receita";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Receita> receitas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Receita receita = instanciarReceita(rs);
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar receitas: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return receitas;
    }

    @Override
    public List<Receita> listarPorDiagnostico(int id_diagnostico) {
        String sql = "SELECT * FROM Receita WHERE id_diagnostico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Receita> receitas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_diagnostico);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Receita receita = instanciarReceita(rs);
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar receitas por diagnóstico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return receitas;
    }

    private Receita instanciarReceita(ResultSet rs) throws SQLException {
        Receita receita = new Receita();
        receita.setId_receita(rs.getInt("id_receita"));
        receita.setDosagem(rs.getString("dosagem"));
        receita.setFrequencia(rs.getString("frequencia"));
        receita.setDuracao(rs.getString("duracao"));

        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId_diagnostico(rs.getInt("id_diagnostico"));
        receita.setDiagnostico(diagnostico);

        Medicamento medicamento = new Medicamento();
        medicamento.setId_medicamento(rs.getInt("id_medicamento"));
        receita.setMedicamento(medicamento);
        
        return receita;
    }
}