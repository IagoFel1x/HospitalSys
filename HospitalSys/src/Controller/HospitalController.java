package Controller;

import Model.Hospital;
import Model.dao.impl.HospitalDAOImpl;
import Model.dao.interfaces.HospitalDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class HospitalController {

    private HospitalDAO hospitalDAO;
    
    public HospitalController() {
        // Você precisará criar HospitalDAO e HospitalDAOImpl
        this.hospitalDAO = new HospitalDAOImpl(); 
    }
    
    public boolean adicionarHospital(String nome, String endereco, String telefone) {
        try {
            Hospital h = new Hospital(nome, endereco, telefone);
            hospitalDAO.salvar(h);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar hospital: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Hospital> listarHospitais() {
        try {
            return hospitalDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar hospitais: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Hospital buscarHospital(int id) {
         try {
            return hospitalDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar hospital: " + e.getMessage());
            return null;
        }
    }
    // ... (Métodos adicionarHospital, listarHospitais, buscarHospital já existem) ...

    public boolean atualizarHospital(int id, String nome, String endereco, String telefone) {
        try {
            Hospital h = new Hospital(nome, endereco, telefone);
            h.setId(id);
            hospitalDAO.atualizar(h); // Requer HospitalDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar hospital: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarHospital(int id) {
        try {
            hospitalDAO.deletar(id); // Requer HospitalDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao deletar hospital: " + e.getMessage());
            return false;
        }
    }
}