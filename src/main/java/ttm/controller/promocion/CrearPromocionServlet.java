package ttm.controller.promocion;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Promocion;
import ttm.services.PromocionService;

//Sevlet para crear una nueva promocion
@WebServlet("/promocion/create.do")
public class CrearPromocionServlet extends HttpServlet {
	private static final long serialVersionUID = 386665313320546213L;
	private PromocionService promoService;

	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		this.promoService = new PromocionService();
	}

	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/vistas/promociones/crear.jsp");
		dispatcher.forward(req, resp);
	}

	//Post
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer tipo = Integer.parseInt(req.getParameter("tipo"));
		Integer bonificacion = Integer.parseInt(req.getParameter("bonificacion"));
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");

		Promocion promo = null;
		try {
			promo = promoService.create(nombre,costo, tiempo, tipo, bonificacion, descripcion,imagen);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (promo.isValid()) {
			//Invoca el camino para refrescar las listas
			resp.sendRedirect("/ttm_web_voidteam/attractions/listado.do"); 
		} else {
			req.setAttribute("attraction", promo);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/vistas/promociones/crear.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
