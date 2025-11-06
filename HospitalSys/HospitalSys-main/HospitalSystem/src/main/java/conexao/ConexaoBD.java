// ConexaoBD.java
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USUARIO = "root"; // Mude para seu usuário
    private static final String SENHA = "(BolGie2019)*";   // Mude para sua senha

    public static Connection getConexao() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage(), e);
        }
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        fecharConexao(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage());
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
        }
    }
}