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
	
	//Agregar aca querys para ABM de atracciones
	
	//Actualizacion de cupo de atraccion
	public void actualizarAtraccion(Atraccion atra) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "UPDATE atracciones set cupo = ? WHERE id_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		if(atra.getCupo() > 0) {
			preparedStatement.setInt(1, atra.getCupo() - 1);
			preparedStatement.setInt(2, atra.getId());
			preparedStatement.executeUpdate();
		}
	}

	// Atracciones preferidas
	public List<Atraccion> atraccionesPreferidas(Integer id_usuario) throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "SELECT DISTINCT A.* FROM atracciones A "
				+ "INNER JOIN usuarios U "
				+ "WHERE A.id_tipo_de_atraccion = U.id_tipo_de_atraccion "
				+ "AND A.costo <= U.presupuesto "
				+ "AND A.tiempo <= U.tiempo "
				+ "AND A.cupo > 0"
				+ "AND U.id_usuario = ?"
				+ "EXCEPT "
				+ "SELECT DISTINCT A.* FROM atracciones A "
				+ "INNER JOIN itinerarios I "
				+ "INNER JOIN usuarios U "
				+ "WHERE U.id_itinerario = I.id_itinerario "
				+ "AND A.id_atraccion = I.id_atraccion";

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

	// Atracciones No preferidas
	public List<Atraccion> atraccionesNoPreferidas(Integer id) throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT DISTINCT A.* FROM atracciones A "
				+ "INNER JOIN usuarios U "
				+ "WHERE A.id_tipo_de_atraccion <> U.id_tipo_de_atraccion "
				+ "AND A.costo <= U.presupuesto "
				+ "AND A.tiempo <= U.tiempo "
				+ "AND A.cupo > 0"
				+ "AND U.id_usuario = ?"
				+ "EXCEPT "
				+ "SELECT DISTINCT A.* FROM atracciones A "
				+ "INNER JOIN itinerarios I "
				+ "INNER JOIN usuarios U "
				+ "WHERE U.id_itinerario = I.id_itinerario "
				+ "AND A.id_atraccion = I.id_atraccion";

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

	//Devuelve una atraccion buscando por su id
	public Atraccion findById(Integer id_atraccion) throws SQLException {
		Atraccion atraccion = null;

		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT A.* FROM atracciones A "
				+ "WHERE A.id_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_atraccion);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			atraccion = toAtraccion(resultSet);
		}

		return atraccion;
	}
	
	
	// este metodo se encarga de crear un objeto con los resultados de la consulta
	public Atraccion toAtraccion(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id_atraccion");
		String nombre = resultSet.getString("nombre");
		Double costo = resultSet.getDouble("costo");
		Double tiempo = resultSet.getDouble("tiempo");
		Integer cupo = resultSet.getInt("cupo");
		Integer tipo_atraccion = resultSet.getInt("id_tipo_de_atraccion");

		return new Atraccion(id, nombre, costo, tiempo, cupo, tipo_atraccion);
	}

	//Insert de atraccion
	public void insert(Atraccion nuevaAtraccion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "INSERT INTO atracciones(id_atraccion, nombre, costo, tiempo, cupo, id_tipo_de_atraccion) VALUES (?,?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, nuevaAtraccion.getId());
		preparedStatement.setString(2, nuevaAtraccion.getNombre());
		preparedStatement.setDouble(3, nuevaAtraccion.getCosto());
		preparedStatement.setDouble(4, nuevaAtraccion.getTiempo());
		preparedStatement.setInt(5, nuevaAtraccion.getCupo());
		preparedStatement.setInt(6, nuevaAtraccion.getTipo_atraccion());
		preparedStatement.executeUpdate();

	}

	//Delete de atraccion
	public void delete(Atraccion atraccion) {
		//TENEMOS QUE VER COMO HACERLO PORQUE PIDE BORRADO LOGICO
		//Podriamos implementarlo seteando el valor del cupo a 0 o a un numero negativo
		//Asi no le agregamos campos a la tabla atracciones
	}

	//Modificar de atraccion
	public void modificar(Atraccion atraccion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE atracciones set nombre = ?, costo = ?, tiempo = ?, cupo = ?, tipo_de_atraccion = ?  WHERE id_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, atraccion.getNombre());
		preparedStatement.setDouble(2, atraccion.getCosto());
		preparedStatement.setDouble(3, atraccion.getTiempo());
		preparedStatement.setInt(4, atraccion.getCupo());
		preparedStatement.setInt(5, atraccion.getTipo_atraccion());
		preparedStatement.setInt(6, atraccion.getId());
		preparedStatement.executeUpdate();
		
	}

}
