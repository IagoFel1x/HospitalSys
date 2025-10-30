package Model.dao.impl;

import Model.FichaMedica;
import Model.Paciente;
import Model.dao.interfaces.FichaMedicaDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FichaMedicaDAOImpl implements FichaMedicaDAO {

    @Override
    public void salvar(FichaMedica ficha) throws SQLException {
        String sql = "INSERT INTO FichaMedica (hist_clinico, alergias, observacoes, id_paciente) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ficha.getHistClinico());
            pstmt.setString(2, ficha.getAlergias());
            pstmt.setString(3, ficha.getObservacoes());
            pstmt.setInt(4, ficha.getPaciente().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(FichaMedica ficha) throws SQLException {
        String sql = "UPDATE FichaMedica SET hist_clinico = ?, alergias = ?, observacoes = ?, id_paciente = ? WHERE id_ficha = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ficha.getHistClinico());
            pstmt.setString(2, ficha.getAlergias());
            pstmt.setString(3, ficha.getObservacoes());
            pstmt.setInt(4, ficha.getPaciente().getId());
            pstmt.setInt(5, ficha.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public FichaMedica buscarPorId(int id) throws SQLException {
        String sql = getSqlCompleto() + " WHERE f.id_ficha = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToFichaMedica(rs);
                }
            }
        }
        return null;
    }

    @Override
    public FichaMedica buscarPorPaciente(int idPaciente) throws SQLException {
        String sql = getSqlCompleto() + " WHERE f.id_paciente = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPaciente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetToFichaMedica(rs);
                }
            }
        }
        return null;
    }
    
    private String getSqlCompleto() {
        return "SELECT f.*, p.nome AS nome_paciente FROM FichaMedica f " +
               "JOIN Paciente p ON f.id_paciente = p.id_paciente";
    }

    private FichaMedica resultSetToFichaMedica(ResultSet rs) throws SQLException {
        FichaMedica f = new FichaMedica();
        f.setId(rs.getInt("id_ficha"));
        f.setHistClinico(rs.getString("hist_clinico"));
        f.setAlergias(rs.getString("alergias"));
        f.setObservacoes(rs.getString("observacoes"));
        
        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        p.setNome(rs.getString("nome_paciente"));
        f.setPaciente(p);
        
        return f;
    }
}