package ttm.controller.atractions;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.services.AtraccionService;

@WebServlet("/attractions/delete.do")
public class BorrarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private AtraccionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("por castear");
		Integer id = Integer.parseInt(req.getParameter("id"));

		try {
			attractionService.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.sendRedirect("/ttm_web_voidteam/attractions/index.do");
	}


}
