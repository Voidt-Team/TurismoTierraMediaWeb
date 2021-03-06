package ttm.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ttm.model.*;
import ttm.dao.*;

public class CompraAtraccionService {

	AtraccionDAO attractionDAO = new AtraccionDAO();
	UsuarioDAO userDAO = new UsuarioDAO();

	//Service para comprar una atraccion
	public Map<String, String> buy(Integer userId, Integer attractionId) throws SQLException {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = userDAO.findById(userId);
		Atraccion attraction = attractionDAO.findById(attractionId);

		if (!attraction.tienecupo(1)) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.tienedinero(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.tienetiempo(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			userDAO.CompraUsuario(user, attraction);
			attractionDAO.actualizarAtraccion(attraction);
		}
		return errors;

	}

}
