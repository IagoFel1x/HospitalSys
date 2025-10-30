package Model.dao.impl;

import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Model.dao.interfaces.ConsultaDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class ConsultaDAOImpl implements ConsultaDAO {

    @Override
    public void salvar(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO Consulta (data, hora, diagnostico_geral, id_paciente, id_medico) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(consulta.getData()));
            pstmt.setTime(2, Time.valueOf(consulta.getHora()));
            pstmt.setString(3, consulta.getDiagnosticoGeral());
            pstmt.setInt(4, consulta.getPaciente().getId());
            pstmt.setInt(5, consulta.getMedico().getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE Consulta SET data = ?, hora = ?, diagnostico_geral = ?, id_paciente = ?, id_medico = ? WHERE id_consulta = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(consulta.getData()));
            pstmt.setTime(2, Time.valueOf(consulta.getHora()));
            pstmt.setString(3, consulta.getDiagnosticoGeral());
            pstmt.setInt(4, consulta.getPaciente().getId());
            pstmt.setInt(5, consulta.getMedico().getId());
            pstmt.setInt(6, consulta.getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Consulta WHERE id_consulta = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Consulta buscarPorId(int id) throws SQLException {
        String sql = getSqlCompleto() + " WHERE c.id_consulta = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToConsulta(rs);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Consulta> listarTodos() throws SQLException {
        ArrayList<Consulta> consultas = new ArrayList<>();
        String sql = getSqlCompleto();
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                consultas.add(resultSetToConsulta(rs));
            }
        }
        return consultas;
    }
    
    private String getSqlCompleto() {
         return "SELECT c.*, " +
                "p.id_paciente, p.nome AS nome_paciente, " +
                "m.id_medico, m.nome AS nome_medico, m.especialidade AS medico_espec " +
                "FROM Consulta c " +
                "JOIN Paciente p ON c.id_paciente = p.id_paciente " +
                "JOIN Medico m ON c.id_medico = m.id_medico";
    }

    private Consulta resultSetToConsulta(ResultSet rs) throws SQLException {
        Consulta c = new Consulta();
        c.setId(rs.getInt("id_consulta"));
        c.setData(rs.getDate("data").toLocalDate());
        c.setHora(rs.getTime("hora").toLocalTime());
        c.setDiagnosticoGeral(rs.getString("diagnostico_geral"));

        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        p.setNome(rs.getString("nome_paciente"));
        c.setPaciente(p);

        Medico m = new Medico();
        m.setId(rs.getInt("id_medico"));
        m.setNome(rs.getString("nome_medico"));
        m.setEspecialidade(rs.getString("medico_espec"));
        c.setMedico(m);
                
        return c;
    }
}