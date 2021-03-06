package ttm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ttm.db.ConnectionProvider;
import ttm.model.Atraccion;
import ttm.model.Itinerario;
import ttm.model.Promocion;

public class ItinerarioDAO {
	//Metodo que agrega una atraccion comprada al itinerario
	public void agregarAtraccionComprada(Integer id_usuario, Atraccion atraSelect) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String query = "INSERT INTO itinerarios(id_itinerario,id_atraccion,id_usuario,costo,tiempo) VALUES (?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		preparedStatement.setInt(2, atraSelect.getId());
		preparedStatement.setInt(3, id_usuario);
		preparedStatement.setDouble(4, atraSelect.getCosto());
		preparedStatement.setDouble(5, atraSelect.getTiempo());
		preparedStatement.executeUpdate();
	}

	//Metodo que agrega una promocion comprada al itinerario
	public void agregarPromocionComprada(Integer id_usuario, Promocion promo) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String query = "INSERT INTO itinerarios(id_itinerario,promo_id,id_usuario,costo,tiempo) VALUES (?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		preparedStatement.setInt(2, promo.getId());
		preparedStatement.setInt(3, id_usuario);
		preparedStatement.setDouble(4, promo.getCosto());
		preparedStatement.setDouble(5, promo.getTiempo());
		preparedStatement.executeUpdate();

	}

	//Metodo que devuelve un itinerario filtrado por id de usuario
	public Itinerario findById(Integer id_usuario) throws SQLException {
		Itinerario itinerario = null;
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT * FROM itinerarios i " + "WHERE i.id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			itinerario = toItinerario(resultSet);
		}

		return itinerario;
	}

	//Metodo que crea un objeto de tipo itinerario
	public Itinerario toItinerario(ResultSet resultSet) throws SQLException {
		Integer id_itinerario = resultSet.getInt("id_itinerario");
		Integer id_atraccion = resultSet.getInt("id_atraccion");
		Integer promo_id = resultSet.getInt("promo_id");
		Integer id_usuario = resultSet.getInt("id_usuario");

		return new Itinerario(id_itinerario, id_atraccion, promo_id, id_usuario);
	}

	//Metodo que devuelve la lista de promociones compradas por un usuario
	public List<Promocion> promocionesCompradas(Integer id_usuario) throws SQLException {
		List<Promocion> promocionescompradas = new ArrayList<Promocion>();
		PromocionDAO promocionDAO = new PromocionDAO();
		Connection connection = ConnectionProvider.getConnection();

		String query = "select DISTINCT p.nombre, i.costo, i.tiempo from promociones p inner join itinerarios i "
				+ "inner join usuarios u where i.promo_id = p.id_promocion and u.id_usuario=i.id_itinerario and u.id_usuario=?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Promocion promocion = promocionDAO.toPromocionCorto(resultSet);
			promocionescompradas.add(promocion);
		}
		return promocionescompradas;
	}

	//Metodo que devuelve la lista de atracciones compradas por un usuario
	public List<Atraccion> atraccionesCompradas(Integer id_usuario) throws SQLException {
		List<Atraccion> atraccionescompradas = new ArrayList<Atraccion>();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		Connection connection = ConnectionProvider.getConnection();

		String query = "select DISTINCT a.nombre, i.costo, i.tiempo from atracciones a inner join itinerarios i "
				+ "inner join usuarios u where i.id_atraccion = a.id_atraccion and u.id_usuario=i.id_itinerario and u.id_usuario=?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Atraccion atraccion = atraccionDAO.toAtraccionCorto(resultSet);
			atraccionescompradas.add(atraccion);
		}
		return atraccionescompradas;
	}
}
