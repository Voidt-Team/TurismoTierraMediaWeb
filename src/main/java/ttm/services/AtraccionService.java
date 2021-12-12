package ttm.services;

import java.sql.SQLException;
import java.util.List;
import ttm.model.Atraccion;
import ttm.dao.AtraccionDAO;

public class AtraccionService {

	//Service que lista todas las atracciones
	public List<Atraccion> list() throws SQLException{
		AtraccionDAO atracciondao=new AtraccionDAO();
		return atracciondao.findAll();
	}
	
	//Service que inicializa un objeto de tipo atraccion
	public Atraccion create(String nombre, Double costo, Double tiempo, Integer cupo, Integer tipo_atraccion,String descripcion,String imagen) throws SQLException {
		Atraccion attraction = new Atraccion(nombre,costo,tiempo,cupo,tipo_atraccion,descripcion,imagen);
		if (attraction.isValid()) {
			AtraccionDAO attractionDAO = new AtraccionDAO();
			attractionDAO.insert(attraction);
		}
		return attraction;
	}

	//Service que actualiza una atraccion
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
		}
		return atraccion;
	}

	//Service que borra una atraccion
	public void delete(Integer id) throws SQLException {
		AtraccionDAO attractionDAO = new AtraccionDAO();
		attractionDAO.delete(id);
	}

	//Service que busca una atraccion por id
	public Atraccion find(Integer id) throws SQLException {
		AtraccionDAO atracciondao = new AtraccionDAO();
		return atracciondao.findById(id);
	}
}
