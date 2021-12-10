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
import ttm.model.Usuario;


public class ItinerarioDAO {
	
	//revisar porque ahroa hay mas campos en la tabla...
	public void crearItinerario(Integer id_usuario, Integer id_atraccion, Integer promo_id) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String query = "INSERT or IGNORE INTO itinerarios(id_atraccion, promo_id, id_usuario) VALUES (?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_atraccion);
		preparedStatement.setInt(2, promo_id);
		preparedStatement.setInt(3, id_usuario);
		preparedStatement.executeUpdate();
		
	}
	
	//revisar...
	public void agregarAItinerario(Usuario usuario, Integer id_atraccion, Integer promo_id, Integer itinerario_id) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String query = "INSERT INTO itinerarios(id_atraccion, promo_id, id_usuario, id_itinerario) VALUES (?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_atraccion);
		preparedStatement.setInt(2, promo_id);
		preparedStatement.setInt(3, usuario.getId());
		preparedStatement.setInt(4, usuario.getIdItinerario());
		preparedStatement.executeUpdate();
		
	}
	

	//Devuelve un itinerario filtrado por id de usuario
	public Itinerario findById(Integer id_usuario) throws SQLException {
		Itinerario itinerario = null;
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "SELECT * FROM itinerarios i "
				+ "WHERE i.id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			itinerario = toItinerario(resultSet);
		}

		return itinerario;
	}
	
	

	//Se genera el objeto itinerario 
	//ahora tiene mas campos la tabla...
	public Itinerario toItinerario(ResultSet resultSet) throws SQLException {
		Integer id_itinerario = resultSet.getInt("id_itinerario");
		Integer id_atraccion = resultSet.getInt("id_atraccion");
		Integer promo_id = resultSet.getInt("promo_id");
		Integer id_usuario = resultSet.getInt("id_usuario");
	
		return new Itinerario(id_itinerario, id_atraccion, promo_id, id_usuario);
	}


	//devuelve lista de promociones compradas...
	public List<Promocion> promocionesCompradas(Integer id_usuario) throws SQLException {
		List<Promocion> promocionescompradas =  new ArrayList<Promocion>();
		PromocionDAO promocionDAO = new PromocionDAO();
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "SELECT DISTINCT P.* FROM promociones P "
				+ "INNER JOIN itinerarios I "
				+ "WHERE P.id_promocion = I.promo_id"
				+ "AND I.id_itinerario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Promocion promocion = promocionDAO.toPromocion(resultSet);
			promocionescompradas.add(promocion);
		}
		return promocionescompradas;
	}
	
	//devuelve una lista de atracciones compradas...
	public List<Atraccion> atraccionesCompradas(Integer id_usuario) throws SQLException {
		List<Atraccion> atraccionescompradas =  new ArrayList<Atraccion>();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		Connection connection = ConnectionProvider.getConnection();
		
		String query = "select a.nombre, i.costo, i.tiempo "
				+ "from atracciones a inner join itinerarios i inner join usuarios u "
				+ "where i.id_atraccion = a.id_atraccion and u.id_usuario = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_usuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Atraccion atraccion = atraccionDAO.toAtraccion(resultSet);
			atraccionescompradas.add(atraccion);
		}
		return atraccionescompradas;
	}

}
