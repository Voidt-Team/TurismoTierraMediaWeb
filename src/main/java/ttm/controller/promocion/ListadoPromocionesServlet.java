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


@WebServlet("/promocion/listado.do")
public class ListadoPromocionesServlet extends HttpServlet implements Servlet {
	//este servlet es para editar las promociones desde la pantalla del admin
	/**
	 * 
	 */
	private static final long serialVersionUID = 6469506014897993669L;
	
	

		  private PromocionService promocionService;
		  
		  @Override public void init() throws ServletException { 
			  super.init();
			  this.promocionService = new PromocionService(); 
		  }
		  
		  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			  List<Promocion> listaPromociones=null;
			try {
				listaPromociones = promocionService.list();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  req.setAttribute("listaPromociones", listaPromociones);
		  
			  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/atracciones/index.jsp"); 
			  dispatcher.forward(req, resp);
		  
		  }

}
