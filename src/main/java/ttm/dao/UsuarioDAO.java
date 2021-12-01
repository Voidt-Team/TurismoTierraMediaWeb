package ttm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ttm.Usuario;
import ttm.db.ConnectionProvider;

public class UsuarioDAO {

	//Agregar aca querys para ABM de usuarios
	
	//Actualiza todos los campos del usuario que compro una atraccion suelta
	public void actualizarUsuario(Usuario usu, Integer itinerario_id)throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "UPDATE usuarios set presupuesto = ?, tiempo = ?, id_itinerario = ? "
				+ "WHERE id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setDouble(1, usu.getPresupuesto()); 
		preparedStatement.setDouble(2, usu.getTiempo()); 
		preparedStatement.setInt(3, itinerario_id);
		preparedStatement.setInt(4, usu.getId());
		preparedStatement.executeUpdate();
	}
	
	
	//Actualiza todos los campos del usuario que compro una promo
	public void actualizarUsuarioPromo(Usuario usu, Integer id_promo) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE usuarios set presupuesto = ?, tiempo = ?" + "WHERE id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setDouble(1, usu.getPresupuesto());
		preparedStatement.setDouble(2, usu.getTiempo());
		preparedStatement.setInt(3, usu.getId());
		preparedStatement.executeUpdate();
	}
	
	
	// este metodo devuelve una lista con todos los usuarios que tienen presupuesto y tiempo para hacer atracciones...
	public List<Usuario> findAll() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT U.* FROM usuarios U "
				+ "WHERE U.presupuesto >= (SELECT min(tiempo) FROM atracciones) "
				+ "AND U.tiempo >= (SELECT min(tiempo) FROM atracciones) "
				+ "ORDER BY U.id_usuario";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Usuario usuario = toUsuario(resultSet);
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	//Busca un usuario por Id
	public Usuario findById(Integer id) throws SQLException {
		Usuario usuario = null;

		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT * FROM usuarios WHERE id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			usuario = toUsuario(resultSet);
		}
		return usuario;
	}

	
	// este metodo se encarga de llamar al constructor con los resultados de la consulta
	public Usuario toUsuario(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id_usuario");
		String nombre = resultSet.getString("nombre");
		Double presupuesto = resultSet.getDouble("presupuesto");
		Double tiempo = resultSet.getDouble("tiempo");
		Integer atraccion_preferida = resultSet.getInt("id_tipo_de_atraccion");
		Integer admin = resultSet.getInt("admin");
		
		return new Usuario(id, nombre, presupuesto, tiempo, atraccion_preferida, admin);
	}


	//Insertar un usuario en la base de datos
	public void insert(Usuario nuevoUsuario) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "INSERT INTO usuarios(id_usuario, nombre, presupuesto, tiempo, atraccion_preferida, admin) VALUES (?,?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, nuevoUsuario.getId());
		preparedStatement.setString(2, nuevoUsuario.getNombre());
		preparedStatement.setDouble(3, nuevoUsuario.getPresupuesto());
		preparedStatement.setDouble(4, nuevoUsuario.getTiempo());
		preparedStatement.setInt(5, nuevoUsuario.getAtraccion_preferida());
		preparedStatement.setInt(6, nuevoUsuario.getAdmin());
		preparedStatement.executeUpdate();
	}
}
