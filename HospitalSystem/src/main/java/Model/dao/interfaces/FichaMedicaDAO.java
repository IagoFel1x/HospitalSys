// FichaMedicaDAO.java
package Model.dao.interfaces;

import Model.FichaMedica;
import java.util.List;

public interface FichaMedicaDAO {
    void salvar(FichaMedica fichaMedica);
    void atualizar(FichaMedica fichaMedica);
    void excluir(int id_ficha);
    FichaMedica buscarPorId(int id_ficha);
    FichaMedica buscarPorIdPaciente(int id_paciente); // Método útil para 1:1
    List<FichaMedica> listarTodos();
}