package ttm.services;

import java.sql.SQLException;
import java.util.List;
import ttm.model.Atraccion;
import ttm.dao.AtraccionDAO;

public class AtraccionService {

	public List<Atraccion> list() throws SQLException{
		AtraccionDAO atracciondao=new AtraccionDAO();
		return atracciondao.findAll();
	}
	
	public Atraccion create(String nombre, Double costo, Double tiempo, Integer cupo, Integer tipo_atraccion,String descripcion,String imagen) throws SQLException {
		//metodo inicializador del objeto
		Atraccion attraction = new Atraccion(nombre,costo,tiempo,cupo,tipo_atraccion,descripcion,imagen);

		if (attraction.isValid()) {
			AtraccionDAO attractionDAO = new AtraccionDAO();
			attractionDAO.insert(attraction);
			
		}

		return attraction;
	}


	public Atraccion update(Integer id,String nombre, Double costo, Double tiempo, Integer cupo, Integer tipo_atraccion,String descripcion,String imagen) throws SQLException {
		
		AtraccionDAO attractionDAO = new AtraccionDAO();
		Atraccion atraccion = attractionDAO.findById(id);

		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setTiempo(tiempo);
		atraccion.setCupo(cupo);
		atraccion.setTipo_atraccion(tipo_atraccion);
		atraccion.setDescripcion(descripcion);
		atraccion.setImagen(imagen);

		if (atraccion.isValid()) {
			attractionDAO.modificar(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}
/*
	public void delete(Integer id) {
		Attraction attraction = new Attraction(id, null, null, null, null);

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		attractionDAO.delete(attraction);
	}
*/
	public Atraccion find(Integer id) throws SQLException {
		AtraccionDAO atracciondao = new AtraccionDAO();
		return atracciondao.findById(id);
	}

}
