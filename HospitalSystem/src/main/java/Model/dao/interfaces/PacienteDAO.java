// PacienteDAO.java
package Model.dao.interfaces;

import Model.Paciente;
import java.util.List;

public interface PacienteDAO {
    void salvar(Paciente paciente);
    void atualizar(Paciente paciente);
    void excluir(int id_paciente);
    Paciente buscarPorId(int id_paciente);
    List<Paciente> listarTodos();
}