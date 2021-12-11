package ttm.services;

import java.sql.SQLException;
import java.util.List;


import ttm.model.Usuario;
import ttm.dao.UsuarioDAO;


public class UsuarioService {
	
	private UsuarioDAO userdao=new UsuarioDAO();
	
	public List<Usuario> list() throws SQLException {
		return userdao.listaUsuario();
	}
	
	//no le genera un id itinerario porque se crea con la primer compra...
	public Usuario create(String nombre, String password, Double presupuesto, Double tiempo,Integer admin) throws SQLException {
		Usuario user = new Usuario(nombre, presupuesto, tiempo, password,admin);
		user.setPassword(password);

		UsuarioDAO miuser=new UsuarioDAO();
		if (user.isValid()) {
			miuser.insert(user);
		}

		return user;
	}
}
