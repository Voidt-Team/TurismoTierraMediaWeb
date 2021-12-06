package ttm.controller.Session;


import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Usuario;
import ttm.services.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	/*
	 * serial auto-generado...
	 */
	private static final long serialVersionUID = -9218809313895615851L;
	
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}
	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String nombre = req.getParameter("nombre");
    	String password = req.getParameter("password");
    	
    	//aparentemente esta aca el error...
    	Usuario user=null;
    	
    	//aparentemente aca esta el error...
		try {
			user = loginService.login(nombre, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error en loginservlet");
			e.printStackTrace();
		}
    	
    	if (!user.isNull()) {
    		req.getSession().setAttribute("user", user);
    		resp.sendRedirect("index.jsp");    		
       	} else {
    		req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");
    		
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("/login.jsp");
      		    dispatcher.forward(req, resp);
    	}
    }
}
