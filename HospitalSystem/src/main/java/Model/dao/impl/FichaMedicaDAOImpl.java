// FichaMedicaDAOImpl.java
package Model.dao.impl;

import Model.FichaMedica;
import Model.Paciente;
import Model.dao.interfaces.FichaMedicaDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FichaMedicaDAOImpl implements FichaMedicaDAO {

    @Override
    public void salvar(FichaMedica fichaMedica) {
        String sql = "INSERT INTO FichaMedica (hist_clinico, alergias, observacoes, id_paciente) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fichaMedica.getHist_clinico());
            stmt.setString(2, fichaMedica.getAlergias());
            stmt.setString(3, fichaMedica.getObservacoes());
            stmt.setInt(4, fichaMedica.getPaciente().getId_paciente());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    fichaMedica.setId_ficha(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar ficha médica: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(FichaMedica fichaMedica) {
        String sql = "UPDATE FichaMedica SET hist_clinico = ?, alergias = ?, observacoes = ?, id_paciente = ? WHERE id_ficha = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, fichaMedica.getHist_clinico());
            stmt.setString(2, fichaMedica.getAlergias());
            stmt.setString(3, fichaMedica.getObservacoes());
            stmt.setInt(4, fichaMedica.getPaciente().getId_paciente());
            stmt.setInt(5, fichaMedica.getId_ficha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ficha médica: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_ficha) {
        String sql = "DELETE FROM FichaMedica WHERE id_ficha = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_ficha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir ficha médica: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public FichaMedica buscarPorId(int id_ficha) {
        String sql = "SELECT * FROM FichaMedica WHERE id_ficha = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FichaMedica ficha = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_ficha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                ficha = instanciarFichaMedica(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ficha médica por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return ficha;
    }

    @Override
    public FichaMedica buscarPorIdPaciente(int id_paciente) {
        String sql = "SELECT * FROM FichaMedica WHERE id_paciente = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FichaMedica ficha = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_paciente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                ficha = instanciarFichaMedica(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ficha médica por ID do paciente: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return ficha;
    }

    @Override
    public List<FichaMedica> listarTodos() {
        String sql = "SELECT * FROM FichaMedica";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FichaMedica> fichas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                FichaMedica ficha = instanciarFichaMedica(rs);
                fichas.add(ficha);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar fichas médicas: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return fichas;
    }

    private FichaMedica instanciarFichaMedica(ResultSet rs) throws SQLException {
        FichaMedica ficha = new FichaMedica();
        ficha.setId_ficha(rs.getInt("id_ficha"));
        ficha.setHist_clinico(rs.getString("hist_clinico"));
        ficha.setAlergias(rs.getString("alergias"));
        ficha.setObservacoes(rs.getString("observacoes"));

        Paciente paciente = new Paciente();
        paciente.setId_paciente(rs.getInt("id_paciente"));
        ficha.setPaciente(paciente);
        
        return ficha;
    }
}