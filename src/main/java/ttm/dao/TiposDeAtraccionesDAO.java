package ttm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ttm.db.ConnectionProvider;
import ttm.model.TiposDeAtracciones;

public class TiposDeAtraccionesDAO {
	//Metodo que devuelve el nombre de una atraccion dado su Iid
	public String findByIdNombre(Integer id_tipo_atraccion) throws SQLException {
		TiposDeAtracciones tipoDeAtracciones = null;
		Connection connection = ConnectionProvider.getConnection();
		String query = "SELECT T.* FROM tipos_de_atracciones T " + "WHERE T.id_tipo_atraccion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_tipo_atraccion);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			tipoDeAtracciones = toTiposDeAtracciones(resultSet);
		}
		return tipoDeAtracciones.getNombre();
	}
	
	//Metodo que se encarga de crear un objeto tipos de atracciones
	public TiposDeAtracciones toTiposDeAtracciones(ResultSet resultSet) throws SQLException {
		Integer id_tipo_atraccion = resultSet.getInt("id_tipo_atraccion");
		String nombre = resultSet.getString("nombre");

		return new TiposDeAtracciones(id_tipo_atraccion, nombre);
	}

	//Metodo que lista todos los tipos de atracciones
	public List<TiposDeAtracciones> findAll() throws SQLException {
		List<TiposDeAtracciones> tipos_atracciones = new ArrayList<TiposDeAtracciones>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "select * from tipo_de_atraccion";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			TiposDeAtracciones tipo_atraccion = toTiposDeAtracciones(resultSet);
			tipos_atracciones.add(tipo_atraccion);
		}
		return tipos_atracciones;
	}
}
