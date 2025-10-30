package Model.dao.impl;

import Model.Hospital;
import Model.Medico;
import Model.Paciente;
import Model.dao.interfaces.MedicoDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoDAOImpl implements MedicoDAO {

    @Override
    public void salvar(Medico medico) throws SQLException {
        String sql = "INSERT INTO Medico (nome, especialidade, crm, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, medico.getNome());
            pstmt.setString(2, medico.getEspecialidade());
            pstmt.setString(3, medico.getCrm());
            pstmt.setString(4, medico.getTelefone());
            pstmt.setString(5, medico.getEmail());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Medico medico) throws SQLException {
        String sql = "UPDATE Medico SET nome = ?, especialidade = ?, crm = ?, telefone = ?, email = ? WHERE id_medico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, medico.getNome());
            pstmt.setString(2, medico.getEspecialidade());
            pstmt.setString(3, medico.getCrm());
            pstmt.setString(4, medico.getTelefone());
            pstmt.setString(5, medico.getEmail());
            pstmt.setInt(6, medico.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Medico WHERE id_medico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Medico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Medico WHERE id_medico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToMedico(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Medico> listarTodos() throws SQLException {
        ArrayList<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM Medico";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                medicos.add(resultSetToMedico(rs));
            }
        }
        return medicos;
    }

    // --- Métodos de Relacionamento N:M ---
    
    @Override
    public void vincularHospital(int idMedico, int idHospital) throws SQLException {
        String sql = "INSERT INTO Medico_Hospital (id_medico, id_hospital) VALUES (?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMedico);
            pstmt.setInt(2, idHospital);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void desvincularHospital(int idMedico, int idHospital) throws SQLException {
        String sql = "DELETE FROM Medico_Hospital WHERE id_medico = ? AND id_hospital = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMedico);
            pstmt.setInt(2, idHospital);
            pstmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Hospital> listarHospitaisPorMedico(int idMedico) throws SQLException {
        ArrayList<Hospital> hospitais = new ArrayList<>();
        String sql = "SELECT h.* FROM Hospital h " +
                     "JOIN Medico_Hospital mh ON h.id_hospital = mh.id_hospital " +
                     "WHERE mh.id_medico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMedico);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Hospital h = new Hospital();
                    h.setId(rs.getInt("id_hospital"));
                    h.setNome(rs.getString("nome"));
                    h.setEndereco(rs.getString("endereco"));
                    hospitais.add(h);
                }
            }
        }
        return hospitais;
    }

    @Override
    public ArrayList<Paciente> listarPacientesPorMedico(int idMedico) throws SQLException {
         ArrayList<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT p.* FROM Paciente p " +
                     "JOIN Medico_Paciente mp ON p.id_paciente = mp.id_paciente " +
                     "WHERE mp.id_medico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMedico);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Paciente p = new Paciente(); // Idealmente usar o helper do PacienteDAO
                    p.setId(rs.getInt("id_paciente"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                    pacientes.add(p);
                }
            }
        }
        return pacientes;
    }
    
    // --- Helper ---
    private Medico resultSetToMedico(ResultSet rs) throws SQLException {
        Medico m = new Medico();
        m.setId(rs.getInt("id_medico"));
        m.setNome(rs.getString("nome"));
        m.setEspecialidade(rs.getString("especialidade"));
        m.setCrm(rs.getString("crm"));
        m.setTelefone(rs.getString("telefone"));
        m.setEmail(rs.getString("email"));
        return m;
    }
}