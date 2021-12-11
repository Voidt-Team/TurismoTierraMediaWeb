package ttm.controller.promocion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Promocion;
import ttm.services.PromocionService;

@WebServlet("/promocion/index.do")
public class ListaPromocionServlet extends HttpServlet implements Servlet {

	// este servlet genera la lista de promociones a mostrar en el index para todos
	// los usuarios
	/**
	 * 
	 */
	private static final long serialVersionUID = -5102305310038535666L;

	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> listaPromociones = null;
		try {
			listaPromociones = promocionService.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("listaPromociones", listaPromociones);
		// invoca al index para mostrar las listas
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);

	}

}
