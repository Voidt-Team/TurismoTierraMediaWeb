package ttm.controller.itinerario;

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
import ttm.model.Atraccion;
import ttm.model.Usuario;
import ttm.services.ItinerarioAtraccionesService;

//Sevlet que genera la lista de atracciones que compro un usuario
@WebServlet("/itinerario/ACompras.do")
public class ListaAtraccionesCompradasServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -775197332438012340L;
	private ItinerarioAtraccionesService itinerarioAtraccionService;

	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		this.itinerarioAtraccionService = new ItinerarioAtraccionesService();
	}

	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> listaAtraccionescompradas = null;
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		
		try {
			listaAtraccionescompradas = itinerarioAtraccionService.list(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("listaAtraccionesCompradas", listaAtraccionescompradas);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/itinerario/PCompras.do"); /* deberia invocar a promociones */
		dispatcher.forward(req, resp);
	}
}
