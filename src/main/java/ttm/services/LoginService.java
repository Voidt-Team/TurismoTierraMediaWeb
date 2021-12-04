package ttm.services;

import ttm.model.Usuario;
import ttm.model.NullUser;
import java.sql.SQLException;
import ttm.dao.UsuarioDAO;

public class LoginService {

	public Usuario login(String nombre, String password)  throws SQLException {
		UsuarioDAO userDao = new UsuarioDAO();
    	Usuario user = userDao.findByname(nombre);
    	
		
		
		/*
		 * if (user.isNull() || !user.checkPassword(password)) { user =
		 * NullUser.build(); }
		 */
		 
		 
    	return user;
	}
	
}
