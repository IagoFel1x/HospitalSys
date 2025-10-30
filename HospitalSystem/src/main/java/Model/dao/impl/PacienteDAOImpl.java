// PacienteDAOImpl.java
package Model.dao.impl;

import Model.Paciente;
import Model.dao.interfaces.PacienteDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements PacienteDAO {

    @Override
    public void salvar(Paciente paciente) {
        String sql = "INSERT INTO Paciente (nome, email, telefone, endereco, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getTelefone());
            stmt.setString(4, paciente.getEndereco());
            stmt.setString(5, paciente.getSexo());
            stmt.setDate(6, new java.sql.Date(paciente.getData_nascimento().getTime()));

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    paciente.setId_paciente(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar paciente: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Paciente paciente) {
        String sql = "UPDATE Paciente SET nome = ?, email = ?, telefone = ?, endereco = ?, sexo = ?, data_nascimento = ? WHERE id_paciente = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getTelefone());
            stmt.setString(4, paciente.getEndereco());
            stmt.setString(5, paciente.getSexo());
            stmt.setDate(6, new java.sql.Date(paciente.getData_nascimento().getTime()));
            stmt.setInt(7, paciente.getId_paciente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_paciente) {
        String sql = "DELETE FROM Paciente WHERE id_paciente = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_paciente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Paciente buscarPorId(int id_paciente) {
        String sql = "SELECT * FROM Paciente WHERE id_paciente = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_paciente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setData_nascimento(rs.getDate("data_nascimento"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        String sql = "SELECT * FROM Paciente";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setData_nascimento(rs.getDate("data_nascimento"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os pacientes: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return pacientes;
    }
}