// AmbulanciaDAOImpl.java
package Model.dao.impl;

import Model.Ambulancia;
import Model.Hospital;
import Model.dao.interfaces.AmbulanciaDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AmbulanciaDAOImpl implements AmbulanciaDAO {

    @Override
    public void salvar(Ambulancia ambulancia) {
        String sql = "INSERT INTO Ambulancia (placa, modelo, ano, id_hospital) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ambulancia.getPlaca());
            stmt.setString(2, ambulancia.getModelo());
            stmt.setInt(3, ambulancia.getAno());
            stmt.setInt(4, ambulancia.getHospital().getId_hospital());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    ambulancia.setId_ambulancia(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar ambulância: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Ambulancia ambulancia) {
        String sql = "UPDATE Ambulancia SET placa = ?, modelo = ?, ano = ?, id_hospital = ? WHERE id_ambulancia = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ambulancia.getPlaca());
            stmt.setString(2, ambulancia.getModelo());
            stmt.setInt(3, ambulancia.getAno());
            stmt.setInt(4, ambulancia.getHospital().getId_hospital());
            stmt.setInt(5, ambulancia.getId_ambulancia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ambulância: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_ambulancia) {
        String sql = "DELETE FROM Ambulancia WHERE id_ambulancia = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_ambulancia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir ambulância: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Ambulancia buscarPorId(int id_ambulancia) {
        String sql = "SELECT * FROM Ambulancia WHERE id_ambulancia = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ambulancia ambulancia = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_ambulancia);
            rs = stmt.executeQuery();

            if (rs.next()) {
                ambulancia = new Ambulancia();
                ambulancia.setId_ambulancia(rs.getInt("id_ambulancia"));
                ambulancia.setPlaca(rs.getString("placa"));
                ambulancia.setModelo(rs.getString("modelo"));
                ambulancia.setAno(rs.getInt("ano"));

                Hospital hospital = new Hospital();
                hospital.setId_hospital(rs.getInt("id_hospital"));
                ambulancia.setHospital(hospital);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ambulância por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return ambulancia;
    }

    @Override
    public List<Ambulancia> listarTodos() {
        String sql = "SELECT * FROM Ambulancia";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Ambulancia> ambulancias = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ambulancia ambulancia = new Ambulancia();
                ambulancia.setId_ambulancia(rs.getInt("id_ambulancia"));
                ambulancia.setPlaca(rs.getString("placa"));
                ambulancia.setModelo(rs.getString("modelo"));
                ambulancia.setAno(rs.getInt("ano"));

                Hospital hospital = new Hospital();
                hospital.setId_hospital(rs.getInt("id_hospital"));
                ambulancia.setHospital(hospital);
                
                ambulancias.add(ambulancia);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ambulâncias: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return ambulancias;
    }
}