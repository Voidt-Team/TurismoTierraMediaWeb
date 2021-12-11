package ttm.model;

import java.util.HashMap;
import java.util.Map;

import ttm.utils.Crypt;

public class Usuario {

	// Ordenamos los atributos segun el orden del txt.-
	private Integer id_usuario;
	private String nombre;
	private Double presupuesto;
	private Double tiempo;
	private Integer idItinerario;
	private Integer tipo_atraccion;
	private Integer admin;
	private String password;
	private HashMap<String, String> errors;

	public Usuario(Integer id_usuario, String nombre, Double presupuesto, Double tiempo, Integer tipo_atraccion, Integer admin, String password) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.tipo_atraccion = tipo_atraccion;
		this.idItinerario = null;
		this.admin = admin;
		this.password=password;
	}
	
	
	//datos que vienen del formulario
	public Usuario(String nombre, Double presupuesto, Double tiempo, String password, Integer admin) {
		super();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.password = password;
		this.tipo_atraccion = 2;
		this.admin=admin;
	}



	// getter y setter de los atributos
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	public Integer getIdItinerario() {
		return idItinerario;
	}

	
	protected void setIdItinerario(Integer idItinerario) {
		this.idItinerario = idItinerario;
	}


	public Integer getTipo_atraccion() {
		return tipo_atraccion;
	}


	public void setTipo_atraccion(Integer tipo_atraccion) {
		this.tipo_atraccion = tipo_atraccion;
	}


	public Integer getId() {
		return id_usuario;
	}
	

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempo="
				+ tiempo + ", idItinerario=" + idItinerario + ", tipo_atraccion=" + tipo_atraccion + ", admin=" + admin
				+ ", password=" + password + "]";
	}


	public boolean isNull() {
		return false;
	}
	
	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	
	//corrobora si tiene dinero el usuario
	public boolean tienedinero(Atraccion attraction) {
		return attraction.getCosto() <= this.presupuesto;
	}
	
	public boolean tienedinero(Promocion promo) {
		return promo.getCosto() <= this.presupuesto;
	}

	//compara el tiempo del usuario con el de la atraccion
	public boolean tienetiempo(Atraccion attraction) {
		return attraction.getTiempo() <= this.tiempo;
	}
	public boolean tienetiempo(Promocion promo) {
		return promo.getTiempo() <= this.tiempo;
	}
	
	public boolean isAdmin() {
		if (admin == 1){
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (presupuesto < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (tiempo < 0) {
			errors.put("time", "No debe ser negativo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

}
