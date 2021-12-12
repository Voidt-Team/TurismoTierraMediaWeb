package ttm.controller.promocion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Atraccion;
import ttm.model.Promocion;
import ttm.services.PromocionService;

//Sevlet que muestra la lista de promociones para todos los usuarios
@WebServlet("/promocion/index.do")
public class ListaPromocionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -5102305310038535666L;
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
		@SuppressWarnings("unchecked")
		List<Atraccion> latracciones = (List<Atraccion>) req.getAttribute("listaAtracciones");
		List<Object> juntas = new ArrayList<>();
		
		for(Atraccion atrac:latracciones) {
			
			juntas.add(atrac);
		}
		for(Promocion prom:listaPromociones) {
			
			juntas.add(prom);
		}
		req.setAttribute("juntas", juntas);
		
		//Invoca al index para mostrar las listas
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
}
