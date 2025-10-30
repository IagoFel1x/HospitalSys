package Controller;

import Model.Triagem;
import Model.dao.impl.TriagemDAOImpl;
import Model.dao.interfaces.TriagemDAO;
import java.sql.SQLException;

public class TriagemController {

    private TriagemDAO triagemDAO;
    
    public TriagemController() {
        this.triagemDAO = new TriagemDAOImpl();
    }
    
    // Triagem geralmente é criada e vinculada a uma consulta ou internação
    // Vamos supor que ela é salva primeiro e retorna o ID
    
    public int criarTriagem(String pressao, double temp, double peso, double altura, String obs) {
        try {
            Triagem t = new Triagem(pressao, temp, peso, altura, obs);
            return triagemDAO.salvar(t); // DAO deve retornar o ID gerado
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao criar triagem: " + e.getMessage());
            return 0; // Indica falha
        }
    }
    
    public Triagem buscarTriagem(int id) {
        try {
            return triagemDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar triagem: " + e.getMessage());
            return null;
        }
    }
}