package ttm.controller.promocion;

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
import ttm.services.PromocionService;

//Sevlet para editar promociones desde la pantalla de admin
@WebServlet("/promocion/listado.do")
public class ListadoPromocionesServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 6469506014897993669L;
	private PromocionService promocionService;

	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}

	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> listaPromociones = null;
		try {
			listaPromociones = promocionService.list();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("listaPromociones", listaPromociones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/atracciones/index.jsp");
		dispatcher.forward(req, resp);
	}
}
