package ttm.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.services.UsuarioService;

//Sevlet para borrar un usuario
@WebServlet("/usuario/delete.do")
public class BorrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -2186741415174402155L;
	private UsuarioService userService;

	//Inicializa el servicio
	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UsuarioService();
	}
	
	//Get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			userService.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("/ttm_web_voidteam/usuarios/index.do");
	}
}
