// HospitalDAOImpl.java
package Model.dao.impl;

import Model.Hospital;
import Model.dao.interfaces.HospitalDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAOImpl implements HospitalDAO {

    @Override
    public void salvar(Hospital hospital) {
        String sql = "INSERT INTO Hospital (nome, endereco, telefone) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, hospital.getNome());
            stmt.setString(2, hospital.getEndereco());
            stmt.setString(3, hospital.getTelefone());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    hospital.setId_hospital(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar hospital: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Hospital hospital) {
        String sql = "UPDATE Hospital SET nome = ?, endereco = ?, telefone = ? WHERE id_hospital = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, hospital.getNome());
            stmt.setString(2, hospital.getEndereco());
            stmt.setString(3, hospital.getTelefone());
            stmt.setInt(4, hospital.getId_hospital());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar hospital: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_hospital) {
        String sql = "DELETE FROM Hospital WHERE id_hospital = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_hospital);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir hospital: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Hospital buscarPorId(int id_hospital) {
        String sql = "SELECT * FROM Hospital WHERE id_hospital = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Hospital hospital = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_hospital);
            rs = stmt.executeQuery();

            if (rs.next()) {
                hospital = new Hospital();
                hospital.setId_hospital(rs.getInt("id_hospital"));
                hospital.setNome(rs.getString("nome"));
                hospital.setEndereco(rs.getString("endereco"));
                hospital.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar hospital por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return hospital;
    }

    @Override
    public List<Hospital> listarTodos() {
        String sql = "SELECT * FROM Hospital";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Hospital> hospitais = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Hospital hospital = new Hospital();
                hospital.setId_hospital(rs.getInt("id_hospital"));
                hospital.setNome(rs.getString("nome"));
                hospital.setEndereco(rs.getString("endereco"));
                hospital.setTelefone(rs.getString("telefone"));
                hospitais.add(hospital);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar hospitais: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return hospitais;
    }
}