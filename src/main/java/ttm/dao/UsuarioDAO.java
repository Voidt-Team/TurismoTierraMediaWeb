package ttm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import ttm.db.ConnectionProvider;
import ttm.model.Atraccion;
import ttm.model.Promocion;
import ttm.model.Usuario;

public class UsuarioDAO {

	// Agregar aca querys para ABM de usuarios

	// Actualiza todos los campos del usuario que compro una atraccion suelta
	public void CompraUsuario(Usuario usu, Atraccion atrac) throws SQLException {
		ItinerarioDAO itinerario=new ItinerarioDAO();
		
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE usuarios set presupuesto = ?, tiempo = ?, id_itinerario = ? " + "WHERE id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setDouble(1, usu.getPresupuesto()-atrac.getCosto());
		preparedStatement.setDouble(2, usu.getTiempo()-atrac.getTiempo());
		preparedStatement.setInt(3, usu.getId());
		preparedStatement.setInt(4, usu.getId());
		preparedStatement.executeUpdate();
		
		//invocamos el insert de itinerario
		itinerario.agregarAtraccionComprada(usu.getId(), atrac);
	}

	// Actualiza todos los campos del usuario que compro una promo
	public void CompraUsuario(Usuario usu, Promocion promo) throws SQLException {
		ItinerarioDAO itinerario=new ItinerarioDAO();
		
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE usuarios set presupuesto = ?, tiempo = ?, id_itinerario = ? " + "WHERE id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setDouble(1, usu.getPresupuesto()-promo.getCosto());
		preparedStatement.setDouble(2, usu.getTiempo()-promo.getTiempo());
		preparedStatement.setInt(3, usu.getId());
		preparedStatement.setInt(4, usu.getId());
		preparedStatement.executeUpdate();
		
		//invocamos el insert de itinerario
		itinerario.agregarPromocionComprada(usu.getId(), promo);
	}

	// este metodo devuelve una lista con todos los usuarios que tienen presupuesto
	// y tiempo para hacer atracciones...
	public List<Usuario> findAll() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT U.* FROM usuarios U " + "WHERE U.presupuesto >= (SELECT min(tiempo) FROM atracciones) "
				+ "AND U.tiempo >= (SELECT min(tiempo) FROM atracciones) " + "ORDER BY U.id_usuario";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Usuario usuario = toUsuario(resultSet);
			usuarios.add(usuario);
		}
		return usuarios;
	}

	// Busca un usuario por Id
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

	// Busca un usuario por nombre
	public Usuario findByname(String nomb) throws SQLException {
		Usuario usuario = null;

		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT * FROM usuarios WHERE nombre = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, nomb);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			usuario = toUsuario(resultSet);
		}
		return usuario;
	}

	// este metodo devuelve una lista con todos los usuarios...
	public List<Usuario> listaUsuario() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT * FROM usuarios";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Usuario usuario = toUsuario(resultSet);
			usuarios.add(usuario);
		}
		return usuarios;
	}

	// este metodo se encarga de llamar al constructor con los resultados de la
	// consulta
	public Usuario toUsuario(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id_usuario");
		String nombre = resultSet.getString("nombre");
		Double presupuesto = resultSet.getDouble("presupuesto");
		Double tiempo = resultSet.getDouble("tiempo");
		Integer atraccion_preferida = resultSet.getInt("id_tipo_de_atraccion");
		Integer admin = resultSet.getInt("admin");
		String password = resultSet.getString("password");

		return new Usuario(id, nombre, presupuesto, tiempo, atraccion_preferida, admin, password);
	}

	// Insertar un usuario en la base de datos
	public void insert(Usuario nuevo) throws SQLException {

			Connection connection = ConnectionProvider.getConnection();

			String query = "INSERT INTO usuarios(nombre, presupuesto, tiempo, id_tipo_de_atraccion, admin,password) VALUES (?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nuevo.getNombre());
			preparedStatement.setDouble(2, nuevo.getPresupuesto());
			preparedStatement.setDouble(3, nuevo.getTiempo());
			preparedStatement.setInt(4, nuevo.getTipo_atraccion());
			preparedStatement.setInt(5, nuevo.getAdmin());
			preparedStatement.setString(6, nuevo.getPassword());
			preparedStatement.executeUpdate();

	}
	
	// Delete de usuario
	public void delete(Integer id) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "DELETE FROM usuarios WHERE id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();

	}
	
	// update un usuario en la base de datos
		public void modificar(Usuario nuevo) throws SQLException {

				Connection connection = ConnectionProvider.getConnection();

				String query = "UPDATE usuarios set nombre=?,presupuesto = ?, tiempo = ?,admin=?, id_tipo_de_atraccion=?,password=? WHERE id_usuario = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, nuevo.getNombre());
				preparedStatement.setDouble(2, nuevo.getPresupuesto());
				preparedStatement.setDouble(3, nuevo.getTiempo());
				preparedStatement.setInt(4, nuevo.getAdmin());
				preparedStatement.setInt(5, nuevo.getTipo_atraccion());
				preparedStatement.setString(6, nuevo.getPassword());
				preparedStatement.setInt(7, nuevo.getId());
				preparedStatement.executeUpdate();

		}
}
