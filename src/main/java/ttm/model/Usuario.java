package ttm.model;

import ttm.utils.Crypt;

public class Usuario {

	// Ordenamos los atributos segun el orden del txt.-
	private Integer id_usuario;
	private String nombre;
	private Double presupuesto;
	private Double tiempo;
	private Integer idItinerario;
	private Integer atraccion_preferida;
	private Integer admin;
	private String password;

	public Usuario(Integer id_usuario, String nombre, Double presupuesto, Double tiempo, Integer atraccion_preferida, Integer admin, String password) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.atraccion_preferida = atraccion_preferida;
		this.idItinerario = null;
		this.admin = admin;
		this.password=password;
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

	public Integer getAtraccion_preferida() {
		return atraccion_preferida;
	}

	public void setAtraccion_preferida(Integer atraccion_preferida) {
		this.atraccion_preferida = atraccion_preferida;
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
				+ tiempo + ", idItinerario=" + idItinerario + ", atraccion_preferida=" + atraccion_preferida
				+ ", admin=" + admin + ", password=" + password + "]";
	}

	public boolean isNull() {
		return false;
	}
	
	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public boolean isAdmin() {
		if (admin == 1){
			return true;
		}else {
			return false;
		}
		
	}

}
