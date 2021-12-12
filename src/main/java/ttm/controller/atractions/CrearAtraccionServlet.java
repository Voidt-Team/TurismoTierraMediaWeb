package ttm.controller.atractions;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Atraccion;
import ttm.services.AtraccionService;

//Sevlet para crear una nueva atraccion
@WebServlet("/attractions/create.do")
public class CrearAtraccionServlet extends HttpServlet {
	private static final long serialVersionUID = -7542258709089360621L;
	private AtraccionService attractionService;

	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AtraccionService();
	}

	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/vistas/atracciones/crear.jsp");
		dispatcher.forward(req, resp);
	}

	//Post
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		Integer tipoAtraccion = Integer.parseInt(req.getParameter("tipo"));
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");

		Atraccion attraction = null;
		try {
			attraction = attractionService.create(nombre, costo, tiempo, cupo, tipoAtraccion, descripcion, imagen);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (attraction.isValid()) {
			//Invoca el camino para refrescar las listas
			resp.sendRedirect("/ttm_web_voidteam/attractions/listado.do"); 
		} else {
			req.setAttribute("attraction", attraction);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/vistas/atracciones/crear.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
