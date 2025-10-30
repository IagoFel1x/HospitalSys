package Model.dao.impl;

import model.Medico;
import model.Paciente;
import model.PlanoDeSaude;
import Model.dao.interfaces.PacienteDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PacienteDAOImpl implements PacienteDAO {

    @Override
    public void salvar(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO Paciente (nome, email, telefone, endereco, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getEmail());
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getEndereco());
            pstmt.setString(5, paciente.getSexo());
            pstmt.setDate(6, Date.valueOf(paciente.getDataNascimento()));
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Paciente paciente) throws SQLException {
        String sql = "UPDATE Paciente SET nome = ?, email = ?, telefone = ?, endereco = ?, sexo = ?, data_nascimento = ? WHERE id_paciente = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getEmail());
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getEndereco());
            pstmt.setString(5, paciente.getSexo());
            pstmt.setDate(6, Date.valueOf(paciente.getDataNascimento()));
            pstmt.setInt(7, paciente.getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Paciente WHERE id_paciente = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Paciente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Paciente WHERE id_paciente = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToPaciente(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Paciente> listarTodos() throws SQLException {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                pacientes.add(resultSetToPaciente(rs));
            }
        }
        return pacientes;
    }

    // --- Métodos de Relacionamento N:M ---

    @Override
    public void vincularPlanoDeSaude(int idPaciente, int idPlano) throws SQLException {
        String sql = "INSERT INTO Paciente_PlanoDeSaude (id_paciente, id_plano_saude) VALUES (?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            pstmt.setInt(2, idPlano);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void desvincularPlanoDeSaude(int idPaciente, int idPlano) throws SQLException {
        String sql = "DELETE FROM Paciente_PlanoDeSaude WHERE id_paciente = ? AND id_plano_saude = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            pstmt.setInt(2, idPlano);
            pstmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<PlanoDeSaude> listarPlanosPorPaciente(int idPaciente) throws SQLException {
        ArrayList<PlanoDeSaude> planos = new ArrayList<>();
        String sql = "SELECT p.* FROM PlanoDeSaude p " +
                     "JOIN Paciente_PlanoDeSaude pp ON p.id_plano_saude = pp.id_plano_saude " +
                     "WHERE pp.id_paciente = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            try (ResultSet rs = pstmt.executeQuery()) {
                // (Assume que existe um PlanoDeSaudeDAOImpl.resultSetToPlanoDeSaude)
                // Para simplificar, faremos aqui:
                while (rs.next()) {
                    PlanoDeSaude p = new PlanoDeSaude();
                    p.setId(rs.getInt("id_plano_saude"));
                    p.setNome(rs.getString("nome"));
                    p.setTipo(rs.getString("tipo"));
                    p.setCobertura(rs.getString("cobertura"));
                    planos.add(p);
                }
            }
        }
        return planos;
    }

    @Override
    public void vincularMedico(int idPaciente, int idMedico) throws SQLException {
        String sql = "INSERT INTO Medico_Paciente (id_paciente, id_medico) VALUES (?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            pstmt.setInt(2, idMedico);
            pstmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Medico> listarMedicosPorPaciente(int idPaciente) throws SQLException {
        ArrayList<Medico> medicos = new ArrayList<>();
        String sql = "SELECT m.* FROM Medico m " +
                     "JOIN Medico_Paciente mp ON m.id_medico = mp.id_medico " +
                     "WHERE mp.id_paciente = ?";
         try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            try (ResultSet rs = pstmt.executeQuery()) {
                // (Assume que existe um MedicoDAOImpl.resultSetToMedico)
                while (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("id_medico"));
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    m.setCrm(rs.getString("crm"));
                    medicos.add(m);
                }
            }
        }
        return medicos;
    }

    // --- Helper ---
    private Paciente resultSetToPaciente(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        p.setNome(rs.getString("nome"));
        p.setEmail(rs.getString("email"));
        p.setTelefone(rs.getString("telefone"));
        p.setEndereco(rs.getString("endereco"));
        p.setSexo(rs.getString("sexo"));
        if (rs.getDate("data_nascimento") != null) {
            p.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        }
        return p;
    }
}