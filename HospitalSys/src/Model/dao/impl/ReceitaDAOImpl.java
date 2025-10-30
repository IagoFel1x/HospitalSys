package Model.dao.impl;

import Model.Diagnostico;
import Model.Medicamento;
import Model.Receita;
import Model.dao.interfaces.ReceitaDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceitaDAOImpl implements ReceitaDAO {

    @Override
    public void salvar(Receita receita) throws SQLException {
        String sql = "INSERT INTO Receita (dosagem, frequencia, duracao, id_diagnostico, id_medicamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, receita.getDosagem());
            pstmt.setString(2, receita.getFrequencia());
            pstmt.setString(3, receita.getDuracao());
            pstmt.setInt(4, receita.getDiagnostico().getId());
            pstmt.setInt(5, receita.getMedicamento().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Receita> listarPorDiagnostico(int idDiagnostico) throws SQLException {
        ArrayList<Receita> receitas = new ArrayList<>();
        String sql = getSqlCompleto() + " WHERE r.id_diagnostico = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDiagnostico);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    receitas.add(resultSetToReceita(rs));
                }
            }
        }
        return receitas;
    }
    
    private String getSqlCompleto() {
        return "SELECT r.*, d.descricao_med, m.nome AS nome_medicamento " +
               "FROM Receita r " +
               "JOIN Diagnostico d ON r.id_diagnostico = d.id_diagnostico " +
               "JOIN Medicamento m ON r.id_medicamento = m.id_medicamento";
    }

    private Receita resultSetToReceita(ResultSet rs) throws SQLException {
        Receita r = new Receita();
        r.setId(rs.getInt("id_receita"));
        r.setDosagem(rs.getString("dosagem"));
        r.setFrequencia(rs.getString("frequencia"));
        r.setDuracao(rs.getString("duracao"));
        
        Diagnostico d = new Diagnostico();
        d.setId(rs.getInt("id_diagnostico"));
        d.setDescricaoMed(rs.getString("descricao_med"));
        r.setDiagnostico(d);
        
        Medicamento m = new Medicamento();
        m.setId(rs.getInt("id_medicamento"));
        m.setNome(rs.getString("nome_medicamento"));
        r.setMedicamento(m);
        
        return r;
    }
}