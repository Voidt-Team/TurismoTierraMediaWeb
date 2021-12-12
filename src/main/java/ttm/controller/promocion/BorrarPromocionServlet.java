package ttm.controller.promocion;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.services.PromocionService;


@WebServlet("/promocion/delete.do")
public class BorrarPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private PromocionService promoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promoService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Integer id = Integer.parseInt(req.getParameter("id"));

		try {
			promoService.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.sendRedirect("/ttm_web_voidteam/attractions/listado.do");
	}


}
