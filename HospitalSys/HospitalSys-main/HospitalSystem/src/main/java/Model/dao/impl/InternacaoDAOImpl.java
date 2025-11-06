// InternacaoDAOImpl.java
package Model.dao.impl;

import Model.Internacao;
import Model.Leito;
import Model.Paciente;
import Model.dao.interfaces.InternacaoDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InternacaoDAOImpl implements InternacaoDAO {

    @Override
    public void salvar(Internacao internacao) {
        String sql = "INSERT INTO Internacao (data_inicio, data_fim, motivo, id_paciente, id_leito) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, new Timestamp(internacao.getData_inicio().getTime()));
            
            if (internacao.getData_fim() != null) {
                stmt.setTimestamp(2, new Timestamp(internacao.getData_fim().getTime()));
            } else {
                stmt.setTimestamp(2, null);
            }
            
            stmt.setString(3, internacao.getMotivo());
            stmt.setInt(4, internacao.getPaciente().getId_paciente());
            stmt.setInt(5, internacao.getLeito().getId_leito());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    internacao.setId_internacao(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar internação: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Internacao internacao) {
        String sql = "UPDATE Internacao SET data_inicio = ?, data_fim = ?, motivo = ?, id_paciente = ?, id_leito = ? WHERE id_internacao = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setTimestamp(1, new Timestamp(internacao.getData_inicio().getTime()));
            
            if (internacao.getData_fim() != null) {
                stmt.setTimestamp(2, new Timestamp(internacao.getData_fim().getTime()));
            } else {
                stmt.setTimestamp(2, null);
            }
            
            stmt.setString(3, internacao.getMotivo());
            stmt.setInt(4, internacao.getPaciente().getId_paciente());
            stmt.setInt(5, internacao.getLeito().getId_leito());
            stmt.setInt(6, internacao.getId_internacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar internação: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_internacao) {
        String sql = "DELETE FROM Internacao WHERE id_internacao = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_internacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir internação: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Internacao buscarPorId(int id_internacao) {
        String sql = "SELECT * FROM Internacao WHERE id_internacao = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Internacao internacao = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_internacao);
            rs = stmt.executeQuery();

            if (rs.next()) {
                internacao = instanciarInternacao(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar internação por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return internacao;
    }

    @Override
    public List<Internacao> listarTodos() {
        String sql = "SELECT * FROM Internacao";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Internacao> internacoes = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Internacao internacao = instanciarInternacao(rs);
                internacoes.add(internacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar internações: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return internacoes;
    }

    @Override
    public List<Internacao> listarPorPaciente(int id_paciente) {
        String sql = "SELECT * FROM Internacao WHERE id_paciente = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Internacao> internacoes = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_paciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Internacao internacao = instanciarInternacao(rs);
                internacoes.add(internacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar internações por paciente: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return internacoes;
    }

    private Internacao instanciarInternacao(ResultSet rs) throws SQLException {
        Internacao internacao = new Internacao();
        internacao.setId_internacao(rs.getInt("id_internacao"));
        internacao.setData_inicio(rs.getTimestamp("data_inicio"));
        internacao.setData_fim(rs.getTimestamp("data_fim"));
        internacao.setMotivo(rs.getString("motivo"));

        Paciente paciente = new Paciente();
        paciente.setId_paciente(rs.getInt("id_paciente"));
        internacao.setPaciente(paciente);

        Leito leito = new Leito();
        leito.setId_leito(rs.getInt("id_leito"));
        internacao.setLeito(leito);
        
        return internacao;
    }
}