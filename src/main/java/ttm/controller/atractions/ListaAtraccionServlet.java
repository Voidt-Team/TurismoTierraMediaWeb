package ttm.controller.atractions;

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
import ttm.model.Atraccion;
import ttm.services.AtraccionService;

@WebServlet("/attractions/index.do")
public class ListaAtraccionServlet extends HttpServlet implements Servlet {

	
	//este servlet genera la lista de atracciones a mostrar en el index para todos los usuarios
	/**
	 * 
	 */
		  private static final long serialVersionUID = 7277899425392166737L; 
		  private AtraccionService attractionService;
		  
		  @Override public void init() throws ServletException { 
			  super.init();
			  this.attractionService = new AtraccionService(); 
		  }
		  
		  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			  List<Atraccion> listaAtracciones=null;
			try {
				listaAtracciones = attractionService.list();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  req.setAttribute("listaAtracciones", listaAtracciones);
		  
			  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp"); 
			  dispatcher.forward(req, resp);
		  
		  }
		  
		  
		 

}
