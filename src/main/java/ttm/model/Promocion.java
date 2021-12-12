package ttm.model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Promocion {
	
	private Integer id;
	protected String nombre;
	protected Double costo;
	protected Double tiempo;
	protected Integer tipo;
	protected Integer bonificacion;
	private String descripcion;
	private String imagen;
	private List<Atraccion> lista_atracciones;
	private Map<String, String> errors;
	

	public Promocion(Integer id, String nombre, Double costo, Double tiempo, Integer tipo, Integer bonificacion,
			String descripcion, String imagen, List<Atraccion> lista_atracciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.bonificacion = bonificacion;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.lista_atracciones = lista_atracciones;
	}


	
	
	public Promocion(Integer id) {
		super();
		this.id = id;
	}




	public Promocion(String nombre, Double costo, Double tiempo, Integer tipo, Integer bonificacion, String descripcion,
			String imagen) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.bonificacion = bonificacion;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}


	//se utiliza para mostrar en el itinerario...
	public Promocion(String nombre, Double costo, Double tiempo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Double getCosto() {
		return costo;
	}



	public void setCosto(Double costo) {
		this.costo = costo;
	}



	public Double getTiempo() {
		return tiempo;
	}



	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}



	public Integer getTipo() {
		return tipo;
	}



	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}



	public Integer getBonificacion() {
		return bonificacion;
	}



	public void setBonificacion(Integer bonificacion) {
		this.bonificacion = bonificacion;
	}



	public List<Atraccion> getLista_atracciones() {
		return lista_atracciones;
	}



	public void setLista_atracciones(List<Atraccion> lista_atracciones) {
		this.lista_atracciones = lista_atracciones;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	@Override
	public String toString() {
		return "Promocion [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", tipo="
				+ tipo + ", bonificacion=" + bonificacion + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", lista_atracciones=" + lista_atracciones + "]";
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (tiempo <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

}
