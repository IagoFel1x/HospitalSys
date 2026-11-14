package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Fábrica de conexões com o MySQL. As credenciais vêm de variáveis de
 * ambiente, para que nenhuma senha fique gravada no código-fonte:
 *
 * <ul>
 *   <li>{@code HOSPITAL_DB_URL} (padrão: jdbc:mysql://localhost:3306/hospital_db)</li>
 *   <li>{@code HOSPITAL_DB_USER} (padrão: root)</li>
 *   <li>{@code HOSPITAL_DB_PASSWORD} (padrão: vazia)</li>
 * </ul>
 */
public final class ConexaoBD {

    private static final String URL = lerVariavel("HOSPITAL_DB_URL", "jdbc:mysql://localhost:3306/hospital_db");
    private static final String USUARIO = lerVariavel("HOSPITAL_DB_USER", "root");
    private static final String SENHA = lerVariavel("HOSPITAL_DB_PASSWORD", "");

    private ConexaoBD() {
    }

    private static String lerVariavel(String nome, String padrao) {
        String valor = System.getenv(nome);
        return (valor == null || valor.isBlank()) ? padrao : valor;
    }

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com " + URL + ": " + e.getMessage(), e);
        }
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    /** Fecha o statement e depois a conexão (ordem inversa da abertura). */
    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage());
        }
        fecharConexao(con);
    }

    /** Fecha o result set, o statement e por fim a conexão. */
    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
        }
        fecharConexao(con, stmt);
    }
}
