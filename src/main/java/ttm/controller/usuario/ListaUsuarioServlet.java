package ttm.controller.usuario;

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
import ttm.model.Usuario;
import ttm.services.UsuarioService;


@WebServlet("/usuarios/index.do")
public class ListaUsuarioServlet extends HttpServlet implements Servlet {
	//listado de usuarios visto por admin...
	private static final long serialVersionUID = -8346640902238722429L;
	private UsuarioService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Usuario> users = null;
		try {
			users = userService.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("users", users);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/vistas/usuarios/index.jsp");
		dispatcher.forward(req, resp);

	}

}
