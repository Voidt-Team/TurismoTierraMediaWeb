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
/*
	public Attraction create(String name, Integer cost, Double duration, Integer capacity) {

		Attraction attraction = new Attraction(name, cost, duration, capacity);

		if (attraction.isValid()) {
			AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
			attractionDAO.insert(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}
*/

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
		System.out.println("por salir de atraccion service con:"+atracciondao);
		return atracciondao.findById(id);
	}

}
