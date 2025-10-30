// InternacaoDAO.java
package Model.dao.interfaces;

import Model.Internacao;
import java.util.List;

public interface InternacaoDAO {
    void salvar(Internacao internacao);
    void atualizar(Internacao internacao);
    void excluir(int id_internacao);
    Internacao buscarPorId(int id_internacao);
    List<Internacao> listarTodos();
    List<Internacao> listarPorPaciente(int id_paciente); // Método útil
}