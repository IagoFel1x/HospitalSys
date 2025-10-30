package Controller;

import Model.Funcionario;
import Model.Hospital;
import Model.dao.impl.FuncionarioDAOImpl;
import Model.dao.interfaces.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;
    private HospitalController hospitalCtrl; // Dependência
    
    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAOImpl();
        this.hospitalCtrl = new HospitalController();
    }
    
    public boolean adicionarFuncionario(String nome, String cargo, String cpf, String telefone, String email, int idHospital) {
        try {
            Hospital h = hospitalCtrl.buscarHospital(idHospital);
            if (h == null) {
                 System.err.println("Erro: Hospital com ID " + idHospital + " não encontrado!");
                return false;
            }
            
            Funcionario f = new Funcionario(nome, cargo, cpf, telefone, email, h);
            funcionarioDAO.salvar(f);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar funcionário: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Funcionario> listarFuncionarios() {
        try {
            return funcionarioDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar funcionários: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // ... (Métodos adicionarFuncionario, listarFuncionarios já existem) ...

    public Funcionario buscarFuncionario(int id) {
        try {
            return funcionarioDAO.buscarPorId(id); // Requer FuncionarioDAO.buscarPorId()
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar funcionário: " + e.getMessage());
            return null;
        }
    }

    public boolean atualizarFuncionario(int id, String nome, String cargo, String cpf, String telefone, String email, int idHospital) {
        try {
            Hospital h = hospitalCtrl.buscarHospital(idHospital);
            if (h == null) {
                System.err.println("Erro: Hospital com ID " + idHospital + " não encontrado!");
                return false;
            }
            Funcionario f = new Funcionario(nome, cargo, cpf, telefone, email, h);
            f.setId(id);
            funcionarioDAO.atualizar(f); // Requer FuncionarioDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar funcionário: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarFuncionario(int id) {
        try {
            funcionarioDAO.deletar(id); // Requer FuncionarioDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao deletar funcionário: " + e.getMessage());
            return false;
        }
    }
}