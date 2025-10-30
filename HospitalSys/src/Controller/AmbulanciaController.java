package Controller;

import Model.Ambulancia;
import Model.Hospital;
import Model.dao.impl.AmbulanciaDAOImpl;
import Model.dao.interfaces.AmbulanciaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmbulanciaController {
    
    private AmbulanciaDAO ambulanciaDAO;
    private HospitalController hospitalCtrl; // Dependência

    public AmbulanciaController() {
        this.ambulanciaDAO = new AmbulanciaDAOImpl();
        this.hospitalCtrl = new HospitalController();
    }
    
    public boolean adicionarAmbulancia(String placa, String modelo, int ano, int idHospital) {
        try {
            Hospital h = hospitalCtrl.buscarHospital(idHospital);
             if (h == null) {
                 System.err.println("Erro: Hospital com ID " + idHospital + " não encontrado!");
                return false;
            }
            Ambulancia a = new Ambulancia(placa, modelo, ano, h);
            ambulanciaDAO.salvar(a);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar ambulância: " + e.getMessage());
            return false;
        }
    }
    
     public ArrayList<Ambulancia> listarAmbulancias() {
        try {
            return ambulanciaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar ambulâncias: " + e.getMessage());
            return new ArrayList<>();
        }
    }
     
    // ... (Métodos adicionarAmbulancia, listarAmbulancias já existem) ...

    public Ambulancia buscarAmbulancia(int id) {
        try {
            return ambulanciaDAO.buscarPorId(id); // Requer AmbulanciaDAO.buscarPorId()
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar ambulância: " + e.getMessage());
            return null;
        }
    }

    public boolean atualizarAmbulancia(int id, String placa, String modelo, int ano, int idHospital) {
        try {
            Hospital h = hospitalCtrl.buscarHospital(idHospital);
            if (h == null) {
                System.err.println("Erro: Hospital com ID " + idHospital + " não encontrado!");
                return false;
            }
            Ambulancia a = new Ambulancia(placa, modelo, ano, h);
            a.setId(id);
            ambulanciaDAO.atualizar(a); // Requer AmbulanciaDAO.atualizar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar ambulância: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarAmbulancia(int id) {
        try {
            ambulanciaDAO.deletar(id); // Requer AmbulanciaDAO.deletar()
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao deletar ambulância: " + e.getMessage());
            return false;
        }
    }
}