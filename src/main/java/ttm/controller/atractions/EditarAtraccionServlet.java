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

@WebServlet("/attractions/edit.do")
public class EditarAtraccionServlet extends HttpServlet {

	//editar una atraccion...
	/**
	 * 
	 */
	private static final long serialVersionUID = -1067641124758904693L;
	private AtraccionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Atraccion attraction = null;
		try {
			attraction = attractionService.find(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("attraction", attraction);
		System.out.println("antes de llamar a editar");
		System.out.println(attraction);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/atracciones/editarAtraccion.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("despues del formatraccion y del editar.. paso de modif base");
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		//Double costo = Double.parseDouble(req.getParameter("cost"));
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		Integer tipoAtraccion = Integer.parseInt(req.getParameter("tipo"));
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");

		Atraccion attraction = null;
		try {
			System.out.println("antes de llamar al servicio update");
			attraction = attractionService.update(id, nombre, costo, tiempo, cupo, tipoAtraccion, descripcion, imagen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (attraction.isValid()) {
			resp.sendRedirect("/ttm_web_voidteam/attractions/listado.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/vistas/atracciones/editarAtraccion.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
