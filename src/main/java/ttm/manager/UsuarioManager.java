package ttm.manager;
import java.sql.SQLException;

import ttm.dao.UsuarioDAO;
import ttm.model.Usuario;


public class UsuarioManager {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	//Alta de usuario
	public void nuevoUsuario(Integer id, String nombre, Double presupuesto, Double tiempo, Integer atraccion_preferida, Integer admin) throws SQLException {
		Usuario nuevoUsuario = new Usuario (id, nombre, presupuesto, tiempo, atraccion_preferida, admin);
		
		try {
			usuarioDAO.insert(nuevoUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
