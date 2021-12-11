package ttm.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttm.model.Usuario;
import ttm.services.UsuarioService;

@WebServlet("/usuario/edit.do")
public class EditarUsuarioServlet extends HttpServlet {

	// este servlet edita un usuario
	/**
	 * 
	 */
	private static final long serialVersionUID = 3174361794343213864L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Usuario usuario = null;
		try {
			usuario = usuarioService.find(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("user", usuario);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/vistas/usuarios/editarUsuario.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String password = req.getParameter("password");
		Double presupuesto = Double.parseDouble(req.getParameter("presupuesto"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer admin = Integer.parseInt(req.getParameter("admin"));

		Usuario usuario = null;
		try {

			usuario = usuarioService.update(id, nombre, presupuesto, tiempo, admin, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (usuario.isValid()) {
			resp.sendRedirect("/ttm_web_voidteam/usuarios/index.do");
		} else {
			req.setAttribute("attraction", usuario);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/vistas/usuarios/editarUsuario.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
