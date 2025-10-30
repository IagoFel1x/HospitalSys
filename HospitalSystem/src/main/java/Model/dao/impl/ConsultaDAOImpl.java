// ConsultaDAOImpl.java
package Model.dao.impl;

import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Model.dao.interfaces.ConsultaDAO;
import conexao.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAOImpl implements ConsultaDAO {

    @Override
    public void salvar(Consulta consulta) {
        String sql = "INSERT INTO Consulta (data, hora, diagnostico_geral, id_paciente, id_medico) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new java.sql.Date(consulta.getData().getTime()));
            stmt.setTime(2, consulta.getHora());
            stmt.setString(3, consulta.getDiagnostico_geral());
            stmt.setInt(4, consulta.getPaciente().getId_paciente());
            stmt.setInt(5, consulta.getMedico().getId_medico());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    consulta.setId_consulta(rs.getInt(1));
                }
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar consulta: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void atualizar(Consulta consulta) {
        String sql = "UPDATE Consulta SET data = ?, hora = ?, diagnostico_geral = ?, id_paciente = ?, id_medico = ? WHERE id_consulta = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(consulta.getData().getTime()));
            stmt.setTime(2, consulta.getHora());
            stmt.setString(3, consulta.getDiagnostico_geral());
            stmt.setInt(4, consulta.getPaciente().getId_paciente());
            stmt.setInt(5, consulta.getMedico().getId_medico());
            stmt.setInt(6, consulta.getId_consulta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public void excluir(int id_consulta) {
        String sql = "DELETE FROM Consulta WHERE id_consulta = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_consulta);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir consulta: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    @Override
    public Consulta buscarPorId(int id_consulta) {
        String sql = "SELECT * FROM Consulta WHERE id_consulta = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consulta consulta = null;

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_consulta);
            rs = stmt.executeQuery();

            if (rs.next()) {
                consulta = instanciarConsulta(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consulta por ID: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return consulta;
    }

    @Override
    public List<Consulta> listarTodos() {
        String sql = "SELECT * FROM Consulta";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = instanciarConsulta(rs);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return consultas;
    }

    @Override
    public List<Consulta> listarPorPaciente(int id_paciente) {
        String sql = "SELECT * FROM Consulta WHERE id_paciente = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_paciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = instanciarConsulta(rs);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas por paciente: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return consultas;
    }
    
    @Override
    public List<Consulta> listarPorMedico(int id_medico) {
        String sql = "SELECT * FROM Consulta WHERE id_medico = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();

        try {
            conexao = ConexaoBD.getConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_medico);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = instanciarConsulta(rs);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas por médico: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return consultas;
    }

    private Consulta instanciarConsulta(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId_consulta(rs.getInt("id_consulta"));
        consulta.setData(rs.getDate("data"));
        consulta.setHora(rs.getTime("hora"));
        consulta.setDiagnostico_geral(rs.getString("diagnostico_geral"));

        Paciente paciente = new Paciente();
        paciente.setId_paciente(rs.getInt("id_paciente"));
        consulta.setPaciente(paciente);

        Medico medico = new Medico();
        medico.setId_medico(rs.getInt("id_medico"));
        consulta.setMedico(medico);
        
        return consulta;
    }
}