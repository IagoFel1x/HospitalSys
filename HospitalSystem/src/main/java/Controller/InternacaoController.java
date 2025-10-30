// InternacaoController.java
package Controller;

import Model.Internacao;
import Model.dao.impl.InternacaoDAOImpl;
import Model.dao.interfaces.InternacaoDAO;
import java.util.List;

public class InternacaoController {

    private final InternacaoDAO internacaoDAO;

    public InternacaoController() {
        this.internacaoDAO = new InternacaoDAOImpl();
    }

    public void salvar(Internacao internacao) {
        internacaoDAO.salvar(internacao);
    }

    public void atualizar(Internacao internacao) {
        internacaoDAO.atualizar(internacao);
    }

    public void excluir(int id_internacao) {
        internacaoDAO.excluir(id_internacao);
    }

    public Internacao buscarPorId(int id_internacao) {
        return internacaoDAO.buscarPorId(id_internacao);
    }

    public List<Internacao> listarTodos() {
        return internacaoDAO.listarTodos();
    }

    public List<Internacao> listarPorPaciente(int id_paciente) {
        return internacaoDAO.listarPorPaciente(id_paciente);
    }
}