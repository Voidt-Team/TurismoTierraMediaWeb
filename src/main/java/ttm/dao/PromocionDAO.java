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

	// devuelve una promocion correspondiente al id pasado
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

	// devuelve una lista con las atracciones que contiene una promocion solicitada
	public List<Atraccion> findAllAttractionsByPromoId(Integer id_promo) throws SQLException {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		Connection connection = ConnectionProvider.getConnection();

		// esta consulta trae la lista de atracciones de una promo buscada por id...
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

	// todas las promos
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

	// este metodo se encarga de llamar al constructor con los resultados de la
	// consulta
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

		// findAllAttractionsByPromoId trae la lista de atrcciones de la promo
		lista_atracciones = findAllAttractionsByPromoId(id);
		return new Promocion(id, nombre, costo, tiempo, tipo, bonificacion, descripcion, imagen, lista_atracciones);

	}

	// este metodo se encarga de llamar al constructor con los resultados de la
	// consulta
	public Promocion toPromocionCorto(ResultSet resultSet) throws SQLException {

		String nombre = resultSet.getString("nombre");
		Double costo = resultSet.getDouble("costo");
		Double tiempo = resultSet.getDouble("tiempo");

		return new Promocion(nombre, costo, tiempo);

	}

	// Insert promocion
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

	// Borrar promocion
	public void delete(Integer id) throws SQLException {
Connection connection = ConnectionProvider.getConnection();
		
		String query = "DELETE FROM promociones WHERE id_promocion = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();

	}

	// Modificacion de la promocion
	public void modificar(Promocion promocion) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = "UPDATE promociones set nombre = ?, costo = ?, tiempo = ?, tipo = ?, bonificacion = ?,descripcion= ?, imagen=?  WHERE id_promocion = ?";

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
