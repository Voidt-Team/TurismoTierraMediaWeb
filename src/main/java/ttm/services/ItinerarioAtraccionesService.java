package ttm.services;

import java.sql.SQLException;
import java.util.List;
import ttm.model.Atraccion;
import ttm.dao.ItinerarioDAO;

public class ItinerarioAtraccionesService {

	public List<Atraccion> list(Integer id) throws SQLException{
		ItinerarioDAO itinerariodao=new ItinerarioDAO();
		return itinerariodao.atraccionesCompradas(id);
	}

}
