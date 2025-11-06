// HospitalDAOImpl.java
package Model.dao.impl; // Define o pacote onde a classe HospitalDAOImpl se encontra.

import Model.Hospital; // Importa a classe de modelo/entidade Hospital.
import Model.dao.interfaces.HospitalDAO; // Importa a interface HospitalDAO que esta classe implementa.
import conexao.ConexaoBD; // Importa a classe utilitária responsável por gerenciar a conexão com o banco de dados.

import java.sql.Connection; // Classe para estabelecer a conexão com o BD.
import java.sql.PreparedStatement; // Interface para executar comandos SQL pré-compilados e seguros (evita SQL Injection).
import java.sql.ResultSet; // Interface para manipular o resultado de consultas SQL (SELECT).
import java.sql.SQLException; // Classe de exceção padrão para erros de SQL.
import java.sql.Statement; // Interface base para executar comandos SQL. Usada aqui para obter chaves geradas.
import java.util.ArrayList; // Implementação de lista para armazenar múltiplos objetos Hospital.
import java.util.List; // Interface de lista.

// Implementação da interface HospitalDAO para gerenciar a persistência da entidade Hospital.
public class HospitalDAOImpl implements HospitalDAO {

    // Método para inserir um novo registro de Hospital no banco de dados.
    @Override
    public void salvar(Hospital hospital) {
        // SQL para inserção, usando '?' como placeholders para segurança.
        String sql = "INSERT INTO Hospital (nome, endereco, telefone) VALUES (?, ?, ?)";
        Connection conexao = null; // Declaração da variável de conexão.
        PreparedStatement stmt = null; // Declaração da variável de statement.

        try {
            conexao = ConexaoBD.getConexao(); // Obtém a conexão com o banco de dados.
            // Prepara o statement, instruindo o JDBC a retornar as chaves geradas (id_hospital).
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Seta os valores dos parâmetros do SQL (nome, endereço, telefone).
            stmt.setString(1, hospital.getNome());
            stmt.setString(2, hospital.getEndereco());
            stmt.setString(3, hospital.getTelefone());

            // Executa o comando SQL de inserção e retorna o número de linhas afetadas.
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se a inserção foi bem-sucedida.
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys(); // Obtém o ResultSet contendo as chaves geradas.

                // Se houver uma chave gerada (o ID).
                if (rs.next()) {
                    // Seta o ID gerado automaticamente de volta no objeto Hospital.
                    hospital.setId_hospital(rs.getInt(1));
                }
                // Fecha o ResultSet imediatamente após o uso.
                ConexaoBD.fecharConexao(null, null, rs);
            }
        } catch (SQLException e) {
            // Captura e imprime a mensagem de erro em caso de falha na operação SQL.
            System.err.println("Erro ao salvar hospital: " + e.getMessage());
        } finally {
            // Garante que a conexão e o statement sejam fechados, mesmo em caso de erro.
            ConexaoBD.fecharConexao(conexao, stmt);
        }
    }

    // Método para atualizar um registro de Hospital existente.
    @Override
    public void atualizar(Hospital hospital) {
        // SQL para atualização, usando o id_hospital na cláusula WHERE.
        String sql = "UPDATE Hospital SET nome = ?, endereco = ?, telefone = ? WHERE id_hospital = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao(); // Obtém a conexão.
            stmt = conexao.prepareStatement(sql); // Prepara o statement.

            // Seta os novos valores.
            stmt.setString(1, hospital.getNome());
            stmt.setString(2, hospital.getEndereco());
            stmt.setString(3, hospital.getTelefone());
            // Seta a condição WHERE (o ID do hospital a ser atualizado).
            stmt.setInt(4, hospital.getId_hospital());

            stmt.executeUpdate(); // Executa o comando de atualização.
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar hospital: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt); // Fecha os recursos.
        }
    }

    //Método para excluir um registro de Hospital pelo seu ID.
    @Override
    public void excluir(int id_hospital) {
        // SQL para exclusão.
        String sql = "DELETE FROM Hospital WHERE id_hospital = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBD.getConexao(); // Obtém a conexão.
            stmt = conexao.prepareStatement(sql); // Prepara o statement.

            stmt.setInt(1, id_hospital); // Seta o ID do hospital a ser excluído.

            stmt.executeUpdate(); // Executa o comando de exclusão.
        } catch (SQLException e) {
            System.err.println("Erro ao excluir hospital: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conexao, stmt); // Fecha os recursos.
        }
    }

    // Método para buscar um Hospital pelo seu ID.
    @Override
    public Hospital buscarPorId(int id_hospital) {
        // SQL para consulta de um único registro.
        String sql = "SELECT * FROM Hospital WHERE id_hospital = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; // Variável para armazenar o resultado da consulta.
        Hospital hospital = null; // Objeto a ser retornado (inicialmente nulo).

        try {
            conexao = ConexaoBD.getConexao(); // Obtém a conexão.
            stmt = conexao.prepareStatement(sql); // Prepara o statement.

            stmt.setInt(1, id_hospital); // Seta o ID para a consulta.

            rs = stmt.executeQuery(); // Executa a consulta e armazena o resultado.

            // Verifica se o ResultSet retornou algum registro.
            if (rs.next()) {
                hospital = new Hospital(); // Instancia um novo objeto Hospital.
                // Popula o objeto Hospital com os dados recuperados do banco de dados.
                hospital.setId_hospital(rs.getInt("id_hospital"));
                hospital.setNome(rs.getString("nome"));
                hospital.setEndereco(rs.getString("endereco"));
                hospital.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar hospital por ID: " + e.getMessage());
        } finally {
            // Garante que a conexão, o statement e o resultset sejam fechados.
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return hospital; // Retorna o hospital encontrado ou null.
    }

    // Método para listar todos os registros de Hospital.
    @Override
    public List<Hospital> listarTodos() {
        // SQL para consulta de todos os registros.
        String sql = "SELECT * FROM Hospital";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Hospital> hospitais = new ArrayList<>(); // Lista para armazenar os hospitais encontrados.

        try {
            conexao = ConexaoBD.getConexao(); // Obtém a conexão.
            stmt = conexao.prepareStatement(sql); // Prepara o statement.

            rs = stmt.executeQuery(); // Executa a consulta.

            // Loop para percorrer todos os registros no ResultSet.
            while (rs.next()) {
                Hospital hospital = new Hospital(); // Cria um novo objeto para cada linha.
                // Popula o objeto Hospital.
                hospital.setId_hospital(rs.getInt("id_hospital"));
                hospital.setNome(rs.getString("nome"));
                hospital.setEndereco(rs.getString("endereco"));
                hospital.setTelefone(rs.getString("telefone"));

                hospitais.add(hospital); // Adiciona o objeto populado à lista.
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar hospitais: " + e.getMessage());
        } finally {
            // Garante que a conexão, o statement e o resultset sejam fechados.
            ConexaoBD.fecharConexao(conexao, stmt, rs);
        }
        return hospitais; // Retorna a lista de hospitais (pode ser vazia).
    }
}