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
	
	//Agregar aca querys para ABM de promociones
	
	//Promociones preferidas
	public List<Promocion> promocionesPreferidas(Integer id) throws SQLException {
		List<Promocion> lpromociones = new ArrayList<Promocion>();
		Connection connection = ConnectionProvider.getConnection();

		String query = "SELECT DISTINCT P.* FROM promociones P "
				+ "INNER JOIN promocion_tiene_atracciones PA "
				+ "INNER JOIN atracciones A "
				+ "INNER JOIN usuarios U "
				+ "WHERE A.id_tipo_de_atraccion = U.id_tipo_de_atraccion "
				+ "AND P.id_promocion = PA.id_promocion "
				+ "AND PA.id_atraccion = A.id_atraccion "
				//Comento estas dos lineas hasta que hagamos el ABM de promociones (porque con la base con nulls no devuelve nada)
				//+ "AND U.presupuesto >= P.costo "
				//+ "AND U.tiempo >= P.tiempo "
				+ "AND A.cupo > 0 "
				+ "AND U.id_usuario = ?"
				+ "EXCEPT "
				+ "SELECT DISTINCT P.* FROM promociones P "
				+ "INNER JOIN itinerarios I "
				+ "INNER JOIN usuarios U "
				+ "WHERE U.id_itinerario = I.id_itinerario "
				+ "AND P.id_promocion = I.promo_id";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Promocion promocion = toPromocion(resultSet);
			lpromociones.add(promocion);
		}
		return lpromociones;
	}
	
	//Promociones No Preferidas
	public List<Promocion> promocionesNoPreferidas(Integer id) throws SQLException {
		List<Promocion> lpromociones = new ArrayList<Promocion>();
		Connection connection = ConnectionProvider.getConnection();

		//Esta mostrando igual promos que son preferidas VER (con la version vieja pasaba lo mismo)
		String query = "select Distinct Promo, Nombre_promo , costo , tiempo from ( "
				+ "	select  ia.id_promocion as Promo, "
				+ "	(select nombre from promociones p2 where p2.id_promocion =ia.id_promocion) as Nombre_Promo, "
				+ "	(select costo from promociones p2 where p2.id_promocion =ia.id_promocion) as costo, "
				+ "	(select tiempo from promociones p2 where p2.id_promocion =ia.id_promocion) as tiempo, "
				+ "	ia.id_atraccion as Atraccion , "
				+ "	(select nombre from atracciones a2 where a2.id_atraccion = ia.id_atraccion) as Nombre_Atraccion "
				+ "	from promocion_tiene_atracciones ia where "
				+ "		id_promocion not in ( "
				+ "			select id_promocion "
				+ "			from promocion_tiene_atracciones ib "
				+ "			WHERE ib.id_atraccion in ( "
				+ "				select id_atraccion from atracciones where cupo >0 AND id_tipo_de_atraccion in ( "
				+ "					select distinct	id_tipo_de_atraccion "
				+ "					from atracciones a "
				+ "					where id_tipo_de_atraccion in ( "
				+ "							select id_tipo_de_atraccion from usuarios where id_usuario = id) "
				+ "				) "
				+ "			) "
				+ "		) "
				+ ") t where Promo not in (select promo_id from itinerarios i where id_itinerario=(select id_itinerario from usuarios u where id_usuario = ?))";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Promocion promocion = toPromocion(resultSet);
			lpromociones.add(promocion);
		}
		return lpromociones;
	}
	//devuelve una promocion correspondiente al id pasado
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

		//esta consulta trae la lista de atracciones de una promo buscada por id...
		String query = "SELECT DISTINCT A.* FROM atracciones A "
				+ "INNER JOIN promocion_tiene_atracciones PA "
				+ "INNER JOIN promociones "
				+ "WHERE PA.id_promocion = ? "
				+ "AND PA.id_atraccion = A.id_atraccion";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id_promo);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Atraccion atraccion = atraccionDAO.toAtraccion(resultSet);//devuelve una atraccion...
			atracciones.add(atraccion);
		}
		return atracciones;
	}

	// este metodo se encarga de llamar al constructor con los resultados de la consulta
		public Promocion toPromocion(ResultSet resultSet) throws SQLException {
			List<Atraccion> lista_atracciones = new ArrayList<Atraccion>();
			//AtraccionDAO atraccionExtraDAO = new AtraccionDAO();

			Integer id = resultSet.getInt("id_promocion");
			String nombre = resultSet.getString("nombre");
			Double costo = resultSet.getDouble("costo");
			Double tiempo = resultSet.getDouble("tiempo");
			Integer tipo = resultSet.getInt("tipo");
			Integer bonificacion = resultSet.getInt("bonificacion");

			// Aca le asignamos el resultado de findAllAttractionsByPromoId a la lista de
			// atracciones de la promo que se esta construyendo
			lista_atracciones = findAllAttractionsByPromoId(id);
			return new Promocion(id, nombre, costo, tiempo, tipo,bonificacion, lista_atracciones);
		
		}
		
		// este metodo se encarga de llamar al constructor con los resultados de la consulta
				public Promocion toPromocionCorto(ResultSet resultSet) throws SQLException {
				
					String nombre = resultSet.getString("nombre");
					Double costo = resultSet.getDouble("costo");
					Double tiempo = resultSet.getDouble("tiempo");

					return new Promocion(nombre, costo, tiempo);
				
				}

		//Insert promocion
		//Aca deberia hacerse las insersiones en la tabla Promocion_tiene_Atracciones tambien
		public void insert(Promocion nuevaPromocion) throws SQLException {
			Connection connection = ConnectionProvider.getConnection();

			String query = "INSERT INTO promociones(id_promocion, nombre, costo, tiempo, tipo, bonificacion) VALUES (?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nuevaPromocion.getId());
			preparedStatement.setString(2, nuevaPromocion.getNombre());
			preparedStatement.setDouble(3, nuevaPromocion.getCosto());
			preparedStatement.setDouble(4, nuevaPromocion.getTiempo());
			preparedStatement.setInt(5, nuevaPromocion.getTipo());
			preparedStatement.setInt(6, nuevaPromocion.getBonificacion());
			preparedStatement.executeUpdate();
			
		}

		//Borrar promocion
		public void delete(Promocion promocion) {
			//TENEMOS QUE VER COMO HACERLO PORQUE PIDE BORRADO LOGICO
			//Podriamos implementarlo seteando el valor del cupo a 0 o a un numero negativo
			//Asi no le agregamos campos a la tabla atracciones
			
		}
		
		//Modificacion de promociones
		public void modificar(Promocion promocion) throws SQLException {
			Connection connection = ConnectionProvider.getConnection();

			String query = "UPDATE promociones set nombre = ?, costo = ?, tiempo = ?, tipo = ?, bonificacion = ?  WHERE id_atraccion = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, promocion.getNombre());
			preparedStatement.setDouble(2, promocion.getCosto());
			preparedStatement.setDouble(3, promocion.getTiempo());
			preparedStatement.setInt(4, promocion.getTipo());
			preparedStatement.setInt(5, promocion.getBonificacion());
			preparedStatement.setInt(6, promocion.getId());
			preparedStatement.executeUpdate();
			
		}
		
}
