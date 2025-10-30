// FuncionarioDAOImpl.java
package Model.dao.impl;

import Model.Funcionario;
import Model.Hospital;
import Model.dao.interfaces.FuncionarioDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    @Override
    public void salvar(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario (nome, cargo, cpf, telefone, email, id_hospital) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getEmail());
            stmt.setInt(6, funcionario.getHospital().getId_hospital());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    funcionario.setId_funcionario(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar funcionário: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET nome = ?, cargo = ?, cpf = ?, telefone = ?, email = ?, id_hospital = ? WHERE id_funcionario = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getEmail());
            stmt.setInt(6, funcionario.getHospital().getId_hospital());
            stmt.setInt(7, funcionario.getId_funcionario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_funcionario) {
        String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_funcionario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Funcionario buscarPorId(int id_funcionario) {
        String sql = "SELECT * FROM Funcionario WHERE id_funcionario = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_funcionario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));

                Hospital hospital = new Hospital();
                hospital.setId_hospital(rs.getInt("id_hospital"));
                funcionario.setHospital(hospital);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM Funcionario";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));

                Hospital hospital = new Hospital();
                hospital.setId_hospital(rs.getInt("id_hospital"));
                funcionario.setHospital(hospital);
                
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return funcionarios;
    }
}