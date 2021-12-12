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

public class PromocionDAO {
	//Metodo que devuelve una promocion correspondiente al id pasado por parametro
	public Promocion findById(Integer id_promo) throws SQLException {
		Promocion promo = null;
		Connection connection = ConnectionProvider.getConnection();
		String query = "SELECT * FROM promociones WHERE id_promocion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_promo);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			promo = toPromocion(resultSet);
		}
		return promo;
	}

	//Metodo que devuelve una lista con las atracciones que contiene una promocion dada
	public List<Atraccion> findAllAttractionsByPromoId(Integer id_promo) throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT DISTINCT A.* FROM atracciones A " + "INNER JOIN promocion_tiene_atracciones PA "
				+ "INNER JOIN promociones " + "WHERE PA.id_promocion = ? " + "AND PA.id_atraccion = A.id_atraccion";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_promo);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Atraccion atraccion = atraccionDAO.toAtraccion(resultSet);// devuelve una atraccion...
			atracciones.add(atraccion);
		}
		return atracciones;
	}

	//Metodo que devuelve todas las promociones que hay en la base de datos
	public List<Promocion> findAll() throws SQLException {
		List<Promocion> lpromociones = new ArrayList<Promocion>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "select * from promociones";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Promocion promocion = toPromocion(resultSet);
			lpromociones.add(promocion);
		}
		return lpromociones;
	}

	//Metodo que crea un objeto de tipo promocion
	public Promocion toPromocion(ResultSet resultSet) throws SQLException {
		List<Atraccion> lista_atracciones = new ArrayList<Atraccion>();

		Integer id = resultSet.getInt("id_promocion");
		String nombre = resultSet.getString("nombre");
		Double costo = resultSet.getDouble("costo");
		Double tiempo = resultSet.getDouble("tiempo");
		Integer tipo = resultSet.getInt("tipo");
		Integer bonificacion = resultSet.getInt("bonificacion");
		String descripcion = resultSet.getString("descripcion");
		String imagen = resultSet.getString("imagen");
		lista_atracciones = findAllAttractionsByPromoId(id);
		return new Promocion(id, nombre, costo, tiempo, tipo, bonificacion, descripcion, imagen, lista_atracciones);
	}

	//Metodo que crea un objeto de tipo promocion con menos datos
	public Promocion toPromocionCorto(ResultSet resultSet) throws SQLException {
		String nombre = resultSet.getString("nombre");
		Double costo = resultSet.getDouble("costo");
		Double tiempo = resultSet.getDouble("tiempo");
		return new Promocion(nombre, costo, tiempo);
	}

	//Metodo que inserta una promocion en la base de datos
	public void insert(Promocion nuevaPromocion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "INSERT INTO promociones(nombre, costo, tiempo, tipo, bonificacion,descripcion,imagen) VALUES (?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, nuevaPromocion.getNombre());
		preparedStatement.setDouble(2, nuevaPromocion.getCosto());
		preparedStatement.setDouble(3, nuevaPromocion.getTiempo());
		preparedStatement.setInt(4, nuevaPromocion.getTipo());
		preparedStatement.setInt(5, nuevaPromocion.getBonificacion());
		preparedStatement.setString(6, nuevaPromocion.getDescripcion());
		preparedStatement.setString(7, nuevaPromocion.getImagen());
		preparedStatement.executeUpdate();
	}

	//Metodo que borra una promocion
	public void delete(Integer id) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String query = "DELETE FROM promociones WHERE id_promocion = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}

	//Metodo que modifica una promocion
	public void modificar(Promocion promocion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE promociones set nombre = ?, costo = ?, tiempo = ?, tipo = ?, bonificacion = ?,descripcion= ?, imagen=?  "
				+ "WHERE id_promocion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, promocion.getNombre());
		preparedStatement.setDouble(2, promocion.getCosto());
		preparedStatement.setDouble(3, promocion.getTiempo());
		preparedStatement.setInt(4, promocion.getTipo());
		preparedStatement.setInt(5, promocion.getBonificacion());
		preparedStatement.setString(6, promocion.getDescripcion());
		preparedStatement.setString(7, promocion.getImagen());
		preparedStatement.setInt(8, promocion.getId());
		preparedStatement.executeUpdate();
	}
}
