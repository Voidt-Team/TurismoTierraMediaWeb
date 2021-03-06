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

//Sevlet para el login
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -9218809313895615851L;
	private LoginService loginService;
	
	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}
	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String nombre = req.getParameter("nombre");
    	String password = req.getParameter("password");
    	
    	Usuario user=null;
		try {
			user = loginService.login(nombre, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if (!user.isNull()) {
    		req.getSession().setAttribute("usuario", user);
    		
			resp.sendRedirect("/ttm_web_voidteam/attractions/index.do"); /* invoca a la pagina de atracciones */ 
       	} else {
    		req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");
    		
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("/login.jsp");
      		    dispatcher.forward(req, resp);
    	}
    }
}
