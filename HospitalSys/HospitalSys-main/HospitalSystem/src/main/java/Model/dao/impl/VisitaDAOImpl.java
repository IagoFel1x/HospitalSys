// VisitaDAOImpl.java
package Model.dao.impl;

import Model.Visita;
import Model.Paciente;
import Model.dao.interfaces.VisitaDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAOImpl implements VisitaDAO {

    @Override
    public void salvar(Visita visita) {
        String sql = "INSERT INTO Visita (data, observacao, id_paciente) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, new Timestamp(visita.getData().getTime()));
            stmt.setString(2, visita.getObservacao());
            stmt.setInt(3, visita.getPaciente().getId_paciente());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    visita.setId_visita(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar visita: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Visita visita) {
        String sql = "UPDATE Visita SET data = ?, observacao = ?, id_paciente = ? WHERE id_visita = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setTimestamp(1, new Timestamp(visita.getData().getTime()));
            stmt.setString(2, visita.getObservacao());
            stmt.setInt(3, visita.getPaciente().getId_paciente());
            stmt.setInt(4, visita.getId_visita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar visita: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_visita) {
        String sql = "DELETE FROM Visita WHERE id_visita = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_visita);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir visita: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Visita buscarPorId(int id_visita) {
        String sql = "SELECT * FROM Visita WHERE id_visita = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Visita visita = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_visita);
            rs = stmt.executeQuery();

            if (rs.next()) {
                visita = new Visita();
                visita.setId_visita(rs.getInt("id_visita"));
                visita.setData(rs.getTimestamp("data"));
                visita.setObservacao(rs.getString("observacao"));

                Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente"));
                visita.setPaciente(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar visita por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return visita;
    }

    @Override
    public List<Visita> listarTodos() {
        String sql = "SELECT * FROM Visita";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Visita> visitas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Visita visita = new Visita();
                visita.setId_visita(rs.getInt("id_visita"));
                visita.setData(rs.getTimestamp("data"));
                visita.setObservacao(rs.getString("observacao"));

                Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente"));
                visita.setPaciente(paciente);
                
                visitas.add(visita);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar visitas: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return visitas;
    }
}