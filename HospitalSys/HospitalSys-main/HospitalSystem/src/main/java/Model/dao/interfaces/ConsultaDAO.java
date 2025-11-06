// ConsultaDAO.java
package Model.dao.interfaces;

import Model.Consulta;
import java.util.List;

public interface ConsultaDAO {
    void salvar(Consulta consulta);
    void atualizar(Consulta consulta);
    void excluir(int id_consulta);
    Consulta buscarPorId(int id_consulta);
    List<Consulta> listarTodos();
    List<Consulta> listarPorPaciente(int id_paciente); // Método útil
    List<Consulta> listarPorMedico(int id_medico); // Método útil
}