package ttm.services;

import java.sql.SQLException;
import java.util.List;

import ttm.model.Atraccion;
import ttm.model.Usuario;
import ttm.dao.UsuarioDAO;


public class UsuarioService {
	
	private UsuarioDAO userdao=new UsuarioDAO();
	
	public List<Usuario> list() throws SQLException {
		return userdao.listaUsuario();
	}
	//falta modificar
	/*public Usuario create(String username, String password, Integer coins, Double time) {
		User user = new User(-1, username, password, coins, time, false);
		user.setPassword(password);

		if (user.isValid()) {
			DAOFactory.getUserDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}*/
}
