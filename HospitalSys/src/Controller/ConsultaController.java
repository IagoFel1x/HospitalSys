package Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Model.dao.interfaces.ConsultaDAO;
import Model.dao.impl.ConsultaDAOImpl;

public class ConsultaController {
    
    private ConsultaDAO consultaDAO;
    private PacienteController pacienteController;
    private MedicoController medicoController;

    public ConsultaController() {
        this.consultaDAO = new ConsultaDAOImpl();
        this.pacienteController = new PacienteController();
        this.medicoController = new MedicoController();
    }

    public boolean agendarConsulta(LocalDate data, LocalTime hora, int idPaciente, int idMedico, String diag) {
        try {
            Paciente p = pacienteController.buscarPaciente(idPaciente);
            Medico m = medicoController.buscarMedico(idMedico);

            if (p == null) {
                System.err.println("Erro: Paciente com ID " + idPaciente + " não encontrado!");
                return false;
            }
            if (m == null) {
                System.err.println("Erro: Médico com ID " + idMedico + " não encontrado!");
                return false;
            }

            Consulta c = new Consulta();
            c.setData(data);
            c.setHora(hora);
            c.setPaciente(p);
            c.setMedico(m);
            c.setDiagnosticoGeral(diag);
            
            consultaDAO.salvar(c);
            return true;
            
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao agendar consulta: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Consulta> listarTodasConsultas() {
        try {
            return consultaDAO.listarTodas();
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao listar consultas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Consulta buscarConsulta(int id) {
         try {
            return consultaDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Falha no Controller ao buscar consulta: " + e.getMessage());
            return null;
        }
    }
}