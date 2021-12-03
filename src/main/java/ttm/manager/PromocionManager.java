package ttm.manager;

import java.sql.SQLException;
import java.util.List;

import ttm.dao.PromocionDAO;
import ttm.model.Atraccion;
import ttm.model.Promocion;


public class PromocionManager {

	private PromocionDAO promocionDAO = new PromocionDAO();
	
	public void nuevaPromocion(Integer id, String nombre, Double costo, Double tiempo, Integer tipo, Integer bonificacion, List<Atraccion> lista_atracciones) throws SQLException {
		Promocion nuevaPromocion = new Promocion(id, nombre, costo, tiempo, tipo,bonificacion, lista_atracciones);
		promocionDAO.insert(nuevaPromocion);
	}
	
	public void eliminarPromocion(Promocion promocion) throws SQLException {
		promocionDAO.delete(promocion);
	}
	
	public void modificarPromocion(Promocion promocion) throws SQLException {
		promocionDAO.modificar(promocion);
	}

}
