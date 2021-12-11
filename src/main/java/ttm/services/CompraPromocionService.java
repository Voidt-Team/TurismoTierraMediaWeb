package ttm.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ttm.model.*;
import ttm.dao.*;

public class CompraPromocionService {
	PromocionDAO promoDAO = new PromocionDAO();
	UsuarioDAO userDAO = new UsuarioDAO();

	public Map<String, String> buy(Integer userId, Integer promoiconId) throws SQLException {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = userDAO.findById(userId);
		Promocion promocion = promoDAO.findById(promoiconId);

		if (!user.tienedinero(promocion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.tienetiempo(promocion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			userDAO.CompraUsuario(user, promocion);
		}

		return errors;

	}

}
