package ttm.controller.atractions;

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
import ttm.services.CompraAtraccionService;

//Sevlet para comprar atraccion
@WebServlet("/attractions/buy.do")
public class compraAtraccionServlet extends HttpServlet {
	private static final long serialVersionUID = 8394487074174932293L;
	private CompraAtraccionService buyAttractionService;

	//Inicializa el servicio
	@Override 
	public void init() throws ServletException {
		super.init();
		this.buyAttractionService = new CompraAtraccionService();
	}

	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsuarioDAO userdao = new UsuarioDAO();
		;
		Integer attractionId = Integer.parseInt(req.getParameter("id"));

		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		Map<String, String> errors = null;
		try {
			errors = buyAttractionService.buy(user.getId(), attractionId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Usuario user2 = null;
		try {
			user2 = userdao.findById(user.getId());
		} catch (SQLException e) {
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
