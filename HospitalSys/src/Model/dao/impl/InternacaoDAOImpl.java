package Model.dao.impl;

import Model.Internacao;
import Model.Leito;
import Model.Paciente;
import Model.dao.interfaces.InternacaoDAO;
import conexao.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class InternacaoDAOImpl implements InternacaoDAO {

    @Override
    public int salvar(Internacao internacao) throws SQLException {
        String sql = "INSERT INTO Internacao (data_inicio, motivo, id_paciente, id_leito) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setTimestamp(1, Timestamp.valueOf(internacao.getDataInicio()));
            pstmt.setString(2, internacao.getMotivo());
            pstmt.setInt(3, internacao.getPaciente().getId());
            pstmt.setInt(4, internacao.getLeito().getId());
            
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retorna o ID gerado
                }
            }
        }
        return 0;
    }
    
    @Override
    public ArrayList<Internacao> listarAtivas() throws SQLException {
        return listarComFiltro("WHERE i.data_fim IS NULL");
    }

    @Override
    public ArrayList<Internacao> listarTodas() throws SQLException {
        return listarComFiltro(null);
    }
    
    @Override
    public Internacao buscarPorId(int id) throws SQLException {
        ArrayList<Internacao> resultado = listarComFiltro("WHERE i.id_internacao = " + id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    private ArrayList<Internacao> listarComFiltro(String filtro) throws SQLException {
        ArrayList<Internacao> internacoes = new ArrayList<>();
        String sql = "SELECT i.*, " +
                     "p.id_paciente, p.nome AS nome_paciente, " +
                     "l.id_leito, l.numero AS numero_leito, l.tipo AS tipo_leito, l.status AS status_leito " +
                     "FROM Internacao i " +
                     "JOIN Paciente p ON i.id_paciente = p.id_paciente " +
                     "JOIN Leito l ON i.id_leito = l.id_leito ";
        
        if (filtro != null && !filtro.isEmpty()) {
            sql += filtro;
        }

        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                internacoes.add(resultSetToInternacao(rs));
            }
        }
        return internacoes;
    }

    @Override
    public void finalizar(int idInternacao) throws SQLException {
        String sql = "UPDATE Internacao SET data_fim = NOW() WHERE id_internacao = ?";
        try (Connection conn = ConexaoDB.abrirConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idInternacao);
            pstmt.executeUpdate();
        }
    }

    private Internacao resultSetToInternacao(ResultSet rs) throws SQLException {
        Internacao i = new Internacao();
        i.setId(rs.getInt("id_internacao"));
        i.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
        Timestamp dataFimTs = rs.getTimestamp("data_fim");
        if (dataFimTs != null) {
            i.setDataFim(dataFimTs.toLocalDateTime());
        }
        i.setMotivo(rs.getString("motivo"));

        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        p.setNome(rs.getString("nome_paciente"));
        i.setPaciente(p);

        Leito l = new Leito();
        l.setId(rs.getInt("id_leito"));
        l.setNumero(rs.getString("numero_leito"));
        l.setTipo(rs.getString("tipo_leito"));
        l.setStatus(rs.getString("status_leito"));
        i.setLeito(l);
        
        return i;
    }
}