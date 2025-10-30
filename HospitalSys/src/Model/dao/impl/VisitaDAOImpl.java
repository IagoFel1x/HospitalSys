package Model.dao.impl;

import Model.Paciente;
import Model.Visita;
import Model.dao.interfaces.VisitaDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class VisitaDAOImpl implements VisitaDAO {

    @Override
    public void salvar(Visita visita) throws SQLException {
        String sql = "INSERT INTO Visita (data, observacao, id_paciente) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(visita.getData()));
            pstmt.setString(2, visita.getObservacao());
            pstmt.setInt(3, visita.getPaciente().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Visita WHERE id_visita = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Visita> listarPorPaciente(int idPaciente) throws SQLException {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = getSqlCompleto() + " WHERE v.id_paciente = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    visitas.add(resultSetToVisita(rs));
                }
            }
        }
        return visitas;
    }
    
    private String getSqlCompleto() {
        return "SELECT v.*, p.nome AS nome_paciente FROM Visita v " +
               "JOIN Paciente p ON v.id_paciente = p.id_paciente";
    }

    private Visita resultSetToVisita(ResultSet rs) throws SQLException {
        Visita v = new Visita();
        v.setId(rs.getInt("id_visita"));
        v.setData(rs.getTimestamp("data").toLocalDateTime());
        v.setObservacao(rs.getString("observacao"));
        
        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        p.setNome(rs.getString("nome_paciente"));
        v.setPaciente(p);
        
        return v;
    }
}