package ttm.services;

import java.sql.SQLException;
import java.util.List;
import ttm.dao.PromocionDAO;
import ttm.model.Promocion;



public class PromocionService {

	public List<Promocion> list() throws SQLException{
		PromocionDAO promociondao=new PromocionDAO();
		return promociondao.findAll();
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

	public Promocion update(Integer id, String nombre, Double costo, Double tiempo, Integer tipo, Integer bonificacion,String descripcion, String imagen) throws SQLException {
		PromocionDAO promoDAO = new PromocionDAO();
		Promocion promo = promoDAO.findById(id);

		promo.setNombre(nombre);
		promo.setCosto(costo);
		promo.setTiempo(tiempo);
		promo.setTipo(tipo);
		promo.setBonificacion(bonificacion);
		promo.setDescripcion(descripcion);
		promo.setImagen(imagen);

		if (promo.isValid()) {
			promoDAO.modificar(promo);
		}

		return promo;
	}
/*
	public void delete(Integer id) {
		Attraction attraction = new Attraction(id, null, null, null, null);

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		attractionDAO.delete(attraction);
	}
*/
	public Promocion find(Integer id) throws SQLException {
		PromocionDAO promodao = new PromocionDAO();
		return promodao.findById(id);
	}
}
