package ttm.controller.atractions;

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
import ttm.services.AtraccionService;

//Sevlet que genera una lista de atracciones que se muestran en el index de los usuarios
@WebServlet("/attractions/index.do")
public class ListaAtraccionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 7277899425392166737L;
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
		List<Atraccion> listaAtracciones = null;
		try {
			listaAtracciones = attractionService.list();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("listaAtracciones", listaAtracciones);
		//Invoca al servlet que genera la lista de promociones
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promocion/index.do");
		dispatcher.forward(req, resp);
	}
}
