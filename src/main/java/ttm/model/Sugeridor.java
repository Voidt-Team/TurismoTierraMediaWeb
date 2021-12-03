package ttm.model;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import ttm.dao.AtraccionDAO;
import ttm.dao.ItinerarioDAO;
import ttm.dao.PromocionDAO;
import ttm.dao.UsuarioDAO;

public class Sugeridor {

	public static void sugerir(Usuario usuario, List<Usuario> listaUsuarios) throws SQLException {
		PromocionDAO promociones = new PromocionDAO();
		AtraccionDAO atracciones = new AtraccionDAO();

		List<Promocion> promosPreferidas = promociones.promocionesPreferidas(usuario.getId());
		List<Promocion> promosNoPreferidas = promociones.promocionesNoPreferidas(usuario.getId());
		List<Atraccion> atraccPreferidas = atracciones.atraccionesPreferidas(usuario.getId());
		List<Atraccion> atraccNoPreferidas = atracciones.atraccionesNoPreferidas(usuario.getId());

		sugerirPromocionesPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
				atraccNoPreferidas, 1, listaUsuarios);

	}

	// ------------------------------Metodo que sugiere las promos
	// preferidas--------------------------
	public static void sugerirPromocionesPreferidas(Usuario usuario, List<Promocion> promosPreferidas,
			List<Atraccion> atraccPreferidas, List<Promocion> promosNoPreferidas, List<Atraccion> atraccNoPreferidas,
			int opcion, List<Usuario> listaUsuarios) throws SQLException {

		PromocionDAO promociones = new PromocionDAO();
		AtraccionDAO atracciones = new AtraccionDAO();
		
		
		while (!promosPreferidas.isEmpty()) {
			sugerirPromos(promosPreferidas, usuario, true);
			Scanner sc = new Scanner(new InputStreamReader(System.in));
			String entrada = sc.next();

			//Consultar si cancela la compra 
			if (entrada.toUpperCase().compareTo("F") == 0) {
				noMasCompras(usuario);
			}
			//Esta opcion es por si no quiere comprar promociones
			if (entrada.toUpperCase().compareTo("C") == 0) {
				sugerirAtraccionesPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
						atraccNoPreferidas, opcion, listaUsuarios);
			}
			try {
				if (entrada.matches("-?\\d+(\\.,0\\d+)?")) {
					opcion = (int) Double.parseDouble(entrada);

					//Actualizar itinerario
					List<Atraccion> atracciones_promo = promosPreferidas.get(opcion-1).getLista_atracciones();
					for (Atraccion atraccion : atracciones_promo) {
						actualizarItinerario(usuario, atraccion, promosPreferidas.get(opcion-1).getId());
					}
					//Actualizar presupuesto y tiempo del usuario
					UsuarioDAO usuarioDao = new UsuarioDAO();
					usuario.setPresupuesto(usuario.getPresupuesto() - promosPreferidas.get(opcion-1).getCosto());
					usuario.setTiempo(usuario.getTiempo() - promosPreferidas.get(opcion-1).getTiempo());
					usuarioDao.actualizarUsuarioPromo(usuario, promosPreferidas.get(opcion-1).getId());
					actualizarPromo(usuario, promosPreferidas.get(opcion-1), usuario.getIdItinerario());
					
					// Se filtra nuevamente las listas para volver a sugerir al usuario las listas
					// actualizadas
					promosPreferidas = promociones.promocionesPreferidas(usuario.getId());
					promosNoPreferidas = promociones.promocionesNoPreferidas(usuario.getId());
					atraccPreferidas = atracciones.atraccionesPreferidas(usuario.getId());
					atraccNoPreferidas = atracciones.atraccionesNoPreferidas(usuario.getId());

					sugerirPromocionesPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
							atraccNoPreferidas, opcion, listaUsuarios);
				} else {
					opcion = promosPreferidas.size() + 1;
				}
			} catch (IndexOutOfBoundsException ex) {
				System.out.println(
						"El valor ingresado solamente puede ser un entero entre 1 y " + promosPreferidas.size());
			}
		}

		if (promosPreferidas.isEmpty()) {
			System.out.println("No tienes promociones para elegir!");
			sugerirAtraccionesPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
					atraccNoPreferidas, opcion, listaUsuarios);
		}
	}

	
	// ------------------------------Metodo que sugiere las atracciones
	// preferidas--------------------------
	public static void sugerirAtraccionesPreferidas(Usuario usuario, List<Promocion> promosPreferidas,
			List<Atraccion> atraccPreferidas, List<Promocion> promosNoPreferidas, List<Atraccion> atraccNoPreferidas,
			int opcion, List<Usuario> listaUsuarios) throws SQLException {

		PromocionDAO promociones = new PromocionDAO();
		AtraccionDAO atracciones = new AtraccionDAO();

		sugerirAtracciones(atraccPreferidas, usuario, true);

		while (!atraccPreferidas.isEmpty()) {

			Scanner sc = new Scanner(new InputStreamReader(System.in));
			String entrada = sc.next();

			if (entrada.toUpperCase().compareTo("F") == 0) {
				noMasCompras(usuario);
			}
			if (entrada.toUpperCase().compareTo("C") == 0) {
				sugerirPromocionesNoPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
						atraccNoPreferidas, opcion, listaUsuarios);
			}
			try {
				if (entrada.matches("-?\\d+(\\.,0\\d+)?")) {
					opcion = (int) Double.parseDouble(entrada);
					
					//Aca se llama al metodo que actualiza el itinerario y ahi dentro al que actualiza la promocion y el usuario
					actualizarItinerario(usuario, atraccPreferidas.get(opcion - 1), 0);
					
					promosPreferidas = promociones.promocionesPreferidas(usuario.getId());
					promosNoPreferidas = promociones.promocionesNoPreferidas(usuario.getId());
					atraccPreferidas = atracciones.atraccionesPreferidas(usuario.getId());
					atraccNoPreferidas = atracciones.atraccionesNoPreferidas(usuario.getId());

					sugerirAtraccionesPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
							atraccNoPreferidas, opcion, listaUsuarios);
				} else {
					opcion = atraccPreferidas.size() + 1;
				}
			} catch (IndexOutOfBoundsException ex) {
				System.out.println(
						"El valor ingresado solamente puede ser un entero entre 1 y " + atraccPreferidas.size());
			}
		}
		if (atraccPreferidas.isEmpty()) {
			System.out.println("No tienes atracciones para elegir!");
			sugerirPromocionesNoPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
					atraccNoPreferidas, opcion, listaUsuarios);
		}

	}



	// ------------------------------Metodo que sugiere las promos NO
	// preferidas--------------------------
	public static void sugerirPromocionesNoPreferidas(Usuario usuario, List<Promocion> promosPreferidas,
			List<Atraccion> atraccPreferidas, List<Promocion> promosNoPreferidas, List<Atraccion> atraccNoPreferidas,
			int opcion, List<Usuario> listaUsuarios) throws SQLException {

		PromocionDAO promociones = new PromocionDAO();
		AtraccionDAO atracciones = new AtraccionDAO();

		sugerirPromos(promosNoPreferidas, usuario, false);
		
		while (!promosNoPreferidas.isEmpty()) {

			Scanner sc = new Scanner(new InputStreamReader(System.in));
			String entrada = sc.next();

			if (entrada.toUpperCase().compareTo("F") == 0) {
				noMasCompras(usuario);
			}

			if (entrada.toUpperCase().compareTo("C") == 0) {
				sugerirAtraccionesNoPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
						atraccNoPreferidas, opcion, listaUsuarios);
			}
			try {
				if (entrada.matches("-?\\d+(\\.,0\\d+)?")) {
					opcion = (int) Double.parseDouble(entrada);

					//Aca se llama al metodo que actualiza el itinerario y ahi dentro al que actualiza la promocion y el usuario
					List<Atraccion> atracciones_promo = promosNoPreferidas.get(opcion-1).getLista_atracciones();
					for (Atraccion atraccion : atracciones_promo) {
						actualizarItinerario(usuario, atraccion, promosNoPreferidas.get(opcion-1).getId());
					}

					//Actualizar presupuesto y tiempo del usuario
					UsuarioDAO usuarioDao = new UsuarioDAO();
					usuario.setPresupuesto(usuario.getPresupuesto() - promosNoPreferidas.get(opcion-1).getCosto());
					usuario.setTiempo(usuario.getTiempo() - promosNoPreferidas.get(opcion-1).getTiempo());
					usuarioDao.actualizarUsuarioPromo(usuario, promosNoPreferidas.get(opcion-1).getId());
					actualizarPromo(usuario, promosNoPreferidas.get(opcion-1), usuario.getIdItinerario());
					
					promosPreferidas = promociones.promocionesPreferidas(usuario.getId());
					promosNoPreferidas = promociones.promocionesNoPreferidas(usuario.getId());
					atraccPreferidas = atracciones.atraccionesPreferidas(usuario.getId());
					atraccNoPreferidas = atracciones.atraccionesNoPreferidas(usuario.getId());

					sugerirPromocionesNoPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
							atraccNoPreferidas, opcion, listaUsuarios);
				} else {
					opcion = promosNoPreferidas.size() + 1;
				}
			} catch (IndexOutOfBoundsException ex) {
				System.out.println(
						"El valor ingresado solamente puede ser un entero entre 1 y " + promosNoPreferidas.size());
			}
		}

		if (promosNoPreferidas.isEmpty()) {
			System.out.println("No tienes promociones para elegir!");
			sugerirAtraccionesNoPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
					atraccNoPreferidas, opcion, listaUsuarios);
		}

	}

	// ------------------------------Metodo que sugiere las atracciones NO
	// preferidas--------------------------
	public static void sugerirAtraccionesNoPreferidas(Usuario usuario, List<Promocion> promosPreferidas,
			List<Atraccion> atraccPreferidas, List<Promocion> promosNoPreferidas, List<Atraccion> atraccNoPreferidas,
			int opcion, List<Usuario> listaUsuarios) throws SQLException {

		PromocionDAO promociones = new PromocionDAO();
		AtraccionDAO atracciones = new AtraccionDAO();

		sugerirAtracciones(atraccNoPreferidas, usuario, false);
		
		while (!atraccNoPreferidas.isEmpty()) {

			Scanner sc = new Scanner(new InputStreamReader(System.in));
			String entrada = sc.next();

			if (entrada.toUpperCase().compareTo("F") == 0) {
				noMasCompras(usuario);
			}

			if (entrada.toUpperCase().compareTo("C") == 0) {
				sugerirPromocionesPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
						atraccNoPreferidas, opcion, listaUsuarios);
			}
			try {
				if (entrada.matches("-?\\d+(\\.,0\\d+)?")) {
					opcion = (int) Double.parseDouble(entrada);

					//Aca se llama al metodo que actualiza el itinerario y ahi dentro al que actualiza la promocion y el usuario
					actualizarItinerario(usuario, atraccNoPreferidas.get(opcion - 1), 0);

					promosPreferidas = promociones.promocionesPreferidas(usuario.getId());
					promosNoPreferidas = promociones.promocionesNoPreferidas(usuario.getId());
					atraccPreferidas = atracciones.atraccionesPreferidas(usuario.getId());
					atraccNoPreferidas = atracciones.atraccionesNoPreferidas(usuario.getId());

					sugerirAtraccionesNoPreferidas(usuario, promosPreferidas, atraccPreferidas, promosNoPreferidas,
							atraccNoPreferidas, opcion, listaUsuarios);
				} else {
					opcion = atraccNoPreferidas.size() + 1;
				}
			} catch (IndexOutOfBoundsException ex) {
				System.out.println(
						"El valor ingresado solamente puede ser un entero entre 1 y " + atraccNoPreferidas.size());
			}
		}

		if (atraccNoPreferidas.isEmpty()) {
			System.out.println("No tienes atracciones para elegir!");
			noMasCompras(usuario);
		}

	}
	
	
	//Metodo para crear/actualizar itinerario
	public static void actualizarItinerario(Usuario usuario, Atraccion atraccion, Integer id_promo) throws SQLException {
		ItinerarioDAO itinerario = new ItinerarioDAO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		Integer itinerario_id = usuario.getIdItinerario();

		if (itinerario_id == null) {
			itinerario.crearItinerario(usuario.getId() ,atraccion.getId(), id_promo);
			itinerario_id = itinerario.findById(usuario.getId()).getId_itinerario();
			usuario.setIdItinerario(itinerario_id);
			if(id_promo == 0) {
				usuario.setPresupuesto(usuario.getPresupuesto() - atraccion.getCosto());
				usuario.setTiempo(usuario.getTiempo() - atraccion.getTiempo());
			}
			usuarioDao.actualizarUsuario(usuario,itinerario_id);
		}else {
			itinerario.agregarAItinerario(usuario ,atraccion.getId(), id_promo, itinerario_id);
		}
		
		Itinerario itinerarioOBJ = itinerario.findById(usuario.getId());
		itinerarioOBJ.setId_itinerario(itinerario_id);
		itinerarioOBJ.setId_usuario(usuario.getId());

		// Aca se llaman a los otros metodos
		actualizarAtraccion(usuario, atraccion, itinerario_id);
	}
	
	
	// ----------------------Metodos de
		// actualizacion----------------------------------
		// Llama a los metodos de actualizacion de: presupuesto y tiempo del usuario que
		// compro una promo
		public static void actualizarPromo(Usuario usuario, Promocion promocion, Integer itinerario_id)
				throws SQLException {
			System.out.println("\nHas elegido la promocion: " + promocion.getNombre());
			actualizarUsuarioPromocion(usuario, promocion, itinerario_id);
			actualizarCupoAtraccionPromo(promocion);
		}

		// Llama a los metodos de actualizacion de: presupuesto y tiempo del usuario que
		// compro una atraccion
		public static void actualizarAtraccion(Usuario usuario, Atraccion atraccion, Integer itinerario_id)
				throws SQLException {
			System.out.println("\nHas elegido la atraccion: " + atraccion.getNombre());
			actualizarUsuarioAtraccion(usuario, atraccion, itinerario_id);
			actualizarCupoAtraccion(atraccion);
		}

		// ------------------------------Metodos de Actualizacion de cupos de
		// ATRACCIONES--------------------------

		// Actualizar cupo de atracciones
		public static void actualizarCupoAtraccion(Atraccion atraccion) throws SQLException {
			AtraccionDAO atraccionDao = new AtraccionDAO();
			atraccionDao.actualizarAtraccion(atraccion);
		}

		// Actualizar cupo de atracciones de una promocion
		public static void actualizarCupoAtraccionPromo(Promocion promo) throws SQLException {
			AtraccionDAO atraccionDao = new AtraccionDAO();
			List<Atraccion> atraccionesPromo = promo.getLista_atracciones();
			for (Atraccion atraccion : atraccionesPromo) {
				actualizarCupoAtraccion(atraccion);
			}
			if (promo.getTipo() == 2) {
				Atraccion aGratis = atraccionDao.findById(promo.getBonificacion());
				actualizarCupoAtraccion(aGratis);
			}
		}

		// ------------------------------Metodos de Actualizacion de
		// USUARIO--------------------------
		// Actualizar presupuesto y tiempo del usuario cuando elige atraccion 
		public static void actualizarUsuarioAtraccion(Usuario usuario, Atraccion atraccion, Integer itinerario_id)
				throws SQLException {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.actualizarUsuario(usuario, itinerario_id);
		}

		// Actualizar presupuesto y tiempo del usuario cuando elige promocion 
		public static void actualizarUsuarioPromocion(Usuario usuario, Promocion promocion, Integer itinerario_id)
				throws SQLException {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.actualizarUsuarioPromo(usuario, promocion.getId());
		}

	
	// ------------------------------Metodo para cuando ya no se puede comprar
	// mas--------------------------
	public static void noMasCompras(Usuario usuario) throws SQLException {
		System.out.println("Finalizo la ronda de compras!");
		
		ItinerarioDAO itinerario = new ItinerarioDAO();
		//VER estos metodos con WEB
		List<Promocion> promosItinerario = itinerario.buscarItinerarioPromociones(usuario.getIdItinerario());
		List<Atraccion> atraccItinerario = itinerario.buscarItinerarioAtracciones(usuario.getIdItinerario());

		if (!promosItinerario.isEmpty()) {
			mostrarItinerarioP(promosItinerario);
		}
		if (!atraccItinerario.isEmpty()) {
			mostrarItinerarioA(atraccItinerario);
		}
		App.consola();
	}

	
	private static void mostrarItinerarioA(List<Atraccion> atraccItinerario) {
		List<Atraccion> atraccionesCompradas = atraccItinerario;
		System.out.println("\nAtracciones Compradas:");
		double suma_costos = 0;
		double suma_horas = 0;
		for (Atraccion atraccion : atraccionesCompradas) {
			suma_costos += atraccion.getCosto();
			suma_horas += atraccion.getTiempo();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nAtraccion: " + atraccion.getNombre() + ", precio: " + atraccion.getCosto()
					+ " monedas, duracion: " + atraccion.getTiempo() + " hs.");
		}
		System.out.println(
				"_________________________________________________________________________________________________________________________________");
		System.out.println(
				"\nCosto total de Atracciones: " + suma_costos + " monedas, tiempo necesario: " + suma_horas + " hs.");
		System.out.println(
				"=================================================================================================================================");

	}

	private static void mostrarItinerarioP(List<Promocion> promosItinerario) {
		System.out.println(
				"=================================================================================================================================");
		System.out.println("\nA continuacion le detallamos el itinerario:");
		double suma_costos = 0;
		double suma_horas = 0;
		List<Promocion> promocionesCompradas = promosItinerario;
		System.out.println("\nPromociones Compradas:");
		for (Promocion promo : promocionesCompradas) {
			suma_costos += promo.getCosto();
			suma_horas += promo.getTiempo();
			List<Atraccion> atraccionesPromo = promo.getLista_atracciones();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nPromocion: " + promo.getNombre() + ", precio: " + promo.getCosto()
					+ " monedas, duracion: " + promo.getTiempo() + " hs., bonus: " + promo.getBonificacion());
			
			//deberiamos llamar desde aca a que se guarde la suma de costos y tiempo de una promo
			
			System.out.println("\nAtracciones Incluidas:");
			for (Atraccion atraccion : atraccionesPromo) {
				System.out.println("\n* " + atraccion.getNombre());
			}
		}
		System.out.println(
				"_________________________________________________________________________________________________________________________________");
		System.out.println(
				"\nCosto total de Promociones: " + suma_costos + " monedas, tiempo necesario: " + suma_horas + " hs.");
	
		System.out.println(
				"=================================================================================================================================");
	}

	// Muestra las promociones a sugerir al usuario dependiendo de sus gustos
	public static void sugerirPromos(List<Promocion> promociones, Usuario usuario, boolean prefONo) {
		if (!promociones.isEmpty()) {
			if (prefONo) {
				System.out.println("\nPromociones recomendadas para usted:");
			} else {
				System.out.println("\nOtras Promociones recomendadas para usted:");
			}
			mostrarPromociones(promociones, usuario);

			System.out.println(
					"_________________________________________________________________________________________________________________\n");
			System.out.println(
					"\nSi desea comprar una promocion del listado ingrese su numero\nSino presione la tecla C");
			System.out.println(
					"_________________________________________________________________________________________________________________\n");
			System.out.println("\nSi desea finalizar su comprar ingrese la tecla F");

		}

	}

	// Muestra las atracciones a sugerir al usuario dependiendo de sus gustos
	public static void sugerirAtracciones(List<Atraccion> atracciones, Usuario usuario, boolean prefONo) {

		if (!atracciones.isEmpty()) {

			if (prefONo) {
				System.out.println("\nAtracciones recomendadas para usted:");
			} else {
				System.out.println("\nOtras Atracciones recomendadas para usted:");
			}
			mostrarAtracciones(atracciones);
			System.out.println(
					"_________________________________________________________________________________________________________________\n");
			System.out.println(
					"\nSi desea comprar una atraccion del listado ingrese su numero\nSino presione la tecla C");
			System.out.println(
					"_________________________________________________________________________________________________________________\n");
			System.out.println("\nSi desea finalizar su comprar ingrese la tecla F");
		}
	}

	// ------------------------------Metodos que muestran listados por pantalla
	// Mostrar Promociones
	public static void mostrarPromociones(List<Promocion> promocionesMostrar, Usuario usuario) {
		List<Promocion> promociones = promocionesMostrar;
		int cant = 0;

		for (Promocion promocion : promociones) {		
		
			if (promocion.getCosto() <= usuario.getPresupuesto()
					&& promocion.getTiempo() <= usuario.getTiempo()) {
				
				cant++;
				System.out.print(cant + " - " + promocion.getNombre() + " - Destinos: ");
				for (Atraccion atraccion : promocion.getLista_atracciones()) {
					System.out.print(atraccion.getNombre() + ", ");
				}
				System.out.println(promocion.getBonificacion() + ". Precio de la promo: " + promocion.getCosto()
						+ " monedas, duracion: " + promocion.getTiempo() + " hs.");
			
			}
		}
		if (cant == 0) {
			promocionesMostrar.removeAll(promocionesMostrar);
		}
	}

	// Mostrar Atracciones
	public static void mostrarAtracciones(List<Atraccion> atraccionesMostrar) {
		List<Atraccion> atracciones = atraccionesMostrar;
		int cant = 0;
		for (Atraccion atraccion : atracciones) {
			cant++;
			System.out.println(cant + " - " + atraccion.getNombre() + ": su costo es de " + atraccion.getCosto()
					+ " monedas, debe disponer de " + atraccion.getTiempo() + " hs. y su cupo es de "
					+ atraccion.getCupo() + " personas.");
		}
	}
}
