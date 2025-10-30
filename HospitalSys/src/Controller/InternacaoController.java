package Controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import Model.Internacao;
import Model.Leito;
import Model.Paciente;
import Model.dao.interfaces.InternacaoDAO;
import Model.dao.impl.InternacaoDAOImpl;

public class InternacaoController {

    private InternacaoDAO internacaoDAO;
    private PacienteController pacienteCtrl;
    private LeitoController leitoCtrl;

    public InternacaoController() {
        this.internacaoDAO = new InternacaoDAOImpl();
        this.pacienteCtrl = new PacienteController();
        this.leitoCtrl = new LeitoController();
    }

    public boolean iniciarInternacao(int idPaciente, int idLeito, String motivo) {
        try {
            Paciente p = pacienteCtrl.buscarPaciente(idPaciente);
            if (p == null) {
                System.err.println("Erro: Paciente não encontrado.");
                return false;
            }
            
            Leito l = leitoCtrl.buscarLeito(idLeito);
            if (l == null) {
                System.err.println("Erro: Leito não encontrado.");
                return false;
            }
            
            if (!l.getStatus().equalsIgnoreCase("Livre")) {
                System.err.println("Erro: Leito " + l.getNumero() + " não está 'Livre'.");
                return false;
            }

            Internacao i = new Internacao();
            i.setPaciente(p);
            i.setLeito(l);
            i.setMotivo(motivo);
            i.setDataInicio(LocalDateTime.now());
            
            internacaoDAO.salvar(i);
            leitoCtrl.atualizarStatusLeito(idLeito, "Ocupado");
            
            System.out.println("Internação iniciada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.err.println("Erro no Controller ao iniciar internação: " + e.getMessage());
            return false;
        }
    }

    public boolean finalizarInternacao(int idInternacao) {
        try {
            Internacao i = internacaoDAO.buscarPorId(idInternacao);
            if (i == null) {
                System.err.println("Erro: Internação com ID " + idInternacao + " não encontrada.");
                return false;
            }
            
            if (i.getDataFim() != null) {
                System.err.println("Erro: Esta internação já foi finalizada.");
                return false;
            }

            internacaoDAO.finalizar(idInternacao);
            leitoCtrl.atualizarStatusLeito(i.getLeito().getId(), "Livre");
            
            System.out.println("Internação finalizada (Alta) com sucesso!");
            return true;
            
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao finalizar internação: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Internacao> listarInternacoesAtivas() {
        try {
            return internacaoDAO.listarAtivas();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar internações ativas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Internacao> listarHistoricoCompleto() {
         try {
            return internacaoDAO.listarTodas();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar histórico: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}