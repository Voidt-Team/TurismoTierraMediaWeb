package ttm.controller.atractions;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.services.AtraccionService;

//Sevlet para borrar atraccion
@WebServlet("/attractions/delete.do")
public class BorrarAtraccionServlet extends HttpServlet {
	private static final long serialVersionUID = 1537949074766873118L;
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
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			attractionService.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/ttm_web_voidteam/attractions/listado.do");
	}
}
