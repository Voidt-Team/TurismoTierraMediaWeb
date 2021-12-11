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

@WebServlet("/users/create.do")
public class CrearUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private UsuarioService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String password = req.getParameter("password");
		Double presupuesto = Double.parseDouble(req.getParameter("presupuesto"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer admin = Integer.parseInt(req.getParameter("admin"));

		//con los datos cargados en el form llama al metodo crear usuario
		Usuario tmp_user=null;
		try {
			tmp_user = userService.create(nombre,password,presupuesto,tiempo,admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tmp_user.isValid()) {
			resp.sendRedirect("/ttm_web_voidteam/usuarios/index.do");
		} else {
			req.setAttribute("tmp_user", tmp_user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/vistas/usuarios/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
