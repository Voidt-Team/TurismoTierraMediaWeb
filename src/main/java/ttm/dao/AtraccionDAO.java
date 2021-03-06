package ttm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ttm.db.ConnectionProvider;
import ttm.model.Atraccion;

public class AtraccionDAO {
	//Metodo que actualiza el cupo de una atraccion
	public void actualizarAtraccion(Atraccion atra) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "UPDATE atracciones set cupo = ? WHERE id_atraccion = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		if (atra.getCupo() > 0) {
			preparedStatement.setInt(1, atra.getCupo() - 1);
			preparedStatement.setInt(2, atra.getId());
			preparedStatement.executeUpdate();
		}
	}

	//Metodo que devuelve un listado de Atracciones preferidas de un usuario dado
	public List<Atraccion> atraccionesPreferidas(Integer id_usuario) throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT DISTINCT A.* FROM atracciones A " + "INNER JOIN usuarios U "
				+ "WHERE A.id_tipo_de_atraccion = U.id_tipo_de_atraccion " + "AND A.costo <= U.presupuesto "
				+ "AND A.tiempo <= U.tiempo " + "AND A.cupo > 0" + "AND U.id_usuario = ?" + "EXCEPT "
				+ "SELECT DISTINCT A.* FROM atracciones A " + "INNER JOIN itinerarios I " + "INNER JOIN usuarios U "
				+ "WHERE U.id_itinerario = I.id_itinerario " + "AND A.id_atraccion = I.id_atraccion";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		preparedStatement.setInt(2, id_usuario);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Atraccion atraccion = toAtraccion(resultSet);
			atracciones.add(atraccion);
		}
		return atracciones;
	}

	//Metodo que devuelve un listado de Atracciones NO preferidas de un usuario dado
	public List<Atraccion> atraccionesNoPreferidas(Integer id) throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT DISTINCT A.* FROM atracciones A " + "INNER JOIN usuarios U "
				+ "WHERE A.id_tipo_de_atraccion <> U.id_tipo_de_atraccion " + "AND A.costo <= U.presupuesto "
				+ "AND A.tiempo <= U.tiempo " + "AND A.cupo > 0" + "AND U.id_usuario = ?" + "EXCEPT "
				+ "SELECT DISTINCT A.* FROM atracciones A " + "INNER JOIN itinerarios I " + "INNER JOIN usuarios U "
				+ "WHERE U.id_itinerario = I.id_itinerario " + "AND A.id_atraccion = I.id_atraccion";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Atraccion atraccion = toAtraccion(resultSet);
			atracciones.add(atraccion);
		}
		return atracciones;
	}

	//Metodo que devuelve una atraccion buscando por su id
	public Atraccion findById(Integer id_atraccion) throws SQLException {
		Atraccion atraccion = null;
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "SELECT * FROM atracciones WHERE id_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_atraccion);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			atraccion = toAtraccion(resultSet);
		}
		return atraccion;
	}

	//Metodo que devuelve un listado de todas las atracciones
	public List<Atraccion> findAll() throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT * FROM atracciones ";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Atraccion atraccion = toAtraccion(resultSet);
			atracciones.add(atraccion);
		}
		return atracciones;
	}

	//Metodo que crea un objeto de tipo atraccion
	public Atraccion toAtraccion(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id_atraccion");
		String nombre = resultSet.getString("nombre");
		Double costo = resultSet.getDouble("costo");
		Double tiempo = resultSet.getDouble("tiempo");
		Integer cupo = resultSet.getInt("cupo");
		Integer tipo_atraccion = resultSet.getInt("id_tipo_de_atraccion");
		String descripcion = resultSet.getString("descripcion");
		String imagen = resultSet.getString("imagen");

		return new Atraccion(id, nombre, costo, tiempo, cupo, tipo_atraccion, descripcion, imagen);
	}

	//Metodo que crea un objeto de tipo atraccion con tres datos
	public Atraccion toAtraccionCorto(ResultSet resultSet) throws SQLException {
		String nombre = resultSet.getString("nombre");
		Double costo = resultSet.getDouble("costo");
		Double tiempo = resultSet.getDouble("tiempo");

		return new Atraccion(nombre, costo, tiempo);
	}

	//Metodo que inserta una atraccion en la base de datos
	public void insert(Atraccion nuevaAtraccion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "INSERT INTO atracciones(nombre, costo, tiempo, cupo, id_tipo_de_atraccion,descripcion,imagen)"
				+ " VALUES (?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, nuevaAtraccion.getNombre());
		preparedStatement.setDouble(2, nuevaAtraccion.getCosto());
		preparedStatement.setDouble(3, nuevaAtraccion.getTiempo());
		preparedStatement.setInt(4, nuevaAtraccion.getCupo());
		preparedStatement.setInt(5, nuevaAtraccion.getTipo_atraccion());
		preparedStatement.setString(6, nuevaAtraccion.getDescripcion());
		preparedStatement.setString(7, nuevaAtraccion.getImagen());
		preparedStatement.executeUpdate();

	}

	//Metodo que borra una atraccion
	public void delete(Integer id) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "DELETE FROM atracciones WHERE id_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();

	}

	//Metodo que modifica una atraccion con los datos cargados en el formulario
	public void modificar(Atraccion atraccion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE atracciones set nombre = ?, costo = ?, tiempo = ?, cupo = ?, id_tipo_de_atraccion = ?, descripcion= ?, imagen= ?  "
				+ "WHERE id_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, atraccion.getNombre());
		preparedStatement.setDouble(2, atraccion.getCosto());
		preparedStatement.setDouble(3, atraccion.getTiempo());
		preparedStatement.setInt(4, atraccion.getCupo());
		preparedStatement.setInt(5, atraccion.getTipo_atraccion());
		preparedStatement.setString(6, atraccion.getDescripcion());
		preparedStatement.setString(7, atraccion.getImagen());
		preparedStatement.setInt(8, atraccion.getId());
		preparedStatement.executeUpdate();
	}

}
