package ttm.services;

import java.sql.SQLException;
import java.util.List;
import ttm.model.Promocion;
import ttm.dao.ItinerarioDAO;

public class ItinerarioPromocionesService {

	public List<Promocion> list(Integer id) throws SQLException{
		ItinerarioDAO itinerariodao=new ItinerarioDAO();
		return itinerariodao.promocionesCompradas(id);
	}

}
