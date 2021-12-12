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
import ttm.model.Promocion;
import ttm.model.Usuario;
import ttm.services.ItinerarioPromocionesService;


//Sevlet que genera la lista de promociones compradas por el usuario
@WebServlet("/itinerario/PCompras.do")
public class ListaPromocionesCompradasServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 4609665199892700930L;
	private ItinerarioPromocionesService itinerarioPromocionesService;

	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		this.itinerarioPromocionesService = new ItinerarioPromocionesService();
	}

	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> listaPromocionescompradas = null;
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		
		try {
			listaPromocionescompradas = itinerarioPromocionesService.list(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("listaPromocionescompradas", listaPromocionescompradas);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/itinerario/index.jsp"); 
		dispatcher.forward(req, resp);
	}
}
