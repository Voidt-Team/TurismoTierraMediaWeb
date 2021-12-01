package ttm.manager;

import java.sql.SQLException;
import ttm.Atraccion;
import ttm.dao.AtraccionDAO;



public class AtraccionManager {
	private AtraccionDAO atraccionDAO = new AtraccionDAO();
	
	public void nuevaAtraccion(Integer id,String nombre, Double costo,Double tiempo, Integer cupo, Integer tipo_atraccion) throws SQLException {
		Atraccion nuevaAtraccion = new Atraccion(id,nombre,costo, tiempo, cupo, tipo_atraccion);
		atraccionDAO.insert(nuevaAtraccion);
	}
	
	public void eliminarAtraccion(Atraccion atraccion) throws SQLException {
		atraccionDAO.delete(atraccion);
	}
	
	public void modificarAtraccion(Atraccion atraccion) throws SQLException {
		atraccionDAO.modificar(atraccion);
	}

}
