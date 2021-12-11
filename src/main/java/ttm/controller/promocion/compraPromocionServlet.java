package ttm.controller.promocion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Usuario;
import ttm.dao.*;
import ttm.services.CompraPromocionService;

@WebServlet("/promocion/buy.do")
public class compraPromocionServlet extends HttpServlet {
	// servlet para cuando se compre una atraccion
	/**
	 * 
	 */
	private static final long serialVersionUID = 6895390940655818962L;
	

	private CompraPromocionService buyPromocionService;

	@Override // inicializa el servicio
	public void init() throws ServletException {
		super.init();
		this.buyPromocionService = new CompraPromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsuarioDAO userdao = new UsuarioDAO();
		;
		Integer promoId = Integer.parseInt(req.getParameter("id"));

		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		Map<String, String> errors = null;
		try {
			errors = buyPromocionService.buy(user.getId(), promoId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Usuario user2 = null;
		try {
			user2 = userdao.findById(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().setAttribute("usuario", user2);

		if (errors.isEmpty()) {
			req.setAttribute("success", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/attractions/index.do");

		dispatcher.forward(req, resp);
	}
}
