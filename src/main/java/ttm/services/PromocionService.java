package ttm.services;

import java.sql.SQLException;
import java.util.List;
import ttm.dao.PromocionDAO;
import ttm.model.Promocion;



public class PromocionService {

	//Service que lista todas las promociones
	public List<Promocion> list() throws SQLException{
		PromocionDAO promociondao=new PromocionDAO();
		return promociondao.findAll();
	}
	
	//Service que crea una promocion
	public Promocion create(String nombre, Double costo, Double tiempo, Integer tipo, Integer bonificacion, String descripcion,
			String imagen) throws SQLException {
		Promocion promo = new Promocion(nombre,costo, tiempo, tipo, bonificacion, descripcion,imagen);

		if (promo.isValid()) {
			PromocionDAO promoDAO = new PromocionDAO();
			promoDAO.insert(promo);
		}
		return promo;
	}


	//Service que actualiza una promocion
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

	//Service que borra una promocion dado su id
	public void delete(Integer id) throws SQLException {
		PromocionDAO promoDAO = new PromocionDAO();
		promoDAO.delete(id);
	}

	//Service que busca una promocion por id
	public Promocion find(Integer id) throws SQLException {
		PromocionDAO promodao = new PromocionDAO();
		return promodao.findById(id);
	}
}
