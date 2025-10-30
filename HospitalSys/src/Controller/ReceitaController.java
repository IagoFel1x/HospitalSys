package Controller;

import Model.Diagnostico;
import Model.Medicamento;
import Model.Receita;
import Model.dao.impl.ReceitaDAOImpl;
import Model.dao.interfaces.ReceitaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceitaController {
    
    private ReceitaDAO receitaDAO;
    private DiagnosticoController diagnosticoCtrl;
    private MedicamentoController medicamentoCtrl;
    
    public ReceitaController() {
        this.receitaDAO = new ReceitaDAOImpl();
        this.diagnosticoCtrl = new DiagnosticoController();
        this.medicamentoCtrl = new MedicamentoController();
    }
    
    public boolean prescreverReceita(int idDiagnostico, int idMedicamento, String dosagem, String frequencia, String duracao) {
        try {
            Diagnostico d = diagnosticoCtrl.buscarDiagnostico(idDiagnostico);
            if (d == null) {
                System.err.println("Erro: Diagnóstico com ID " + idDiagnostico + " não encontrado!");
                return false;
            }
            
            Medicamento m = medicamentoCtrl.buscarMedicamento(idMedicamento);
            if (m == null) {
                System.err.println("Erro: Medicamento com ID " + idMedicamento + " não encontrado!");
                return false;
            }
            
            Receita r = new Receita(dosagem, frequencia, duracao, d, m);
            receitaDAO.salvar(r);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao salvar receita: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Receita> listarReceitasPorDiagnostico(int idDiagnostico) {
        try {
            return receitaDAO.listarPorDiagnostico(idDiagnostico);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar receitas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}