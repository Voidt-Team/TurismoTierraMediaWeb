package ttm.model;

import java.util.HashMap;
import java.util.Map;

public class Atraccion {

	private Integer id;
	private String nombre;
	private Double costo;
	private Double tiempo;
	private Integer cupo;
	private Integer tipo_atraccion;
	private String descripcion;
	private String imagen;
	private Map<String, String> errors;

	public Atraccion(Integer id,String nombre, Double costo, Double tiempo, Integer cupo, Integer tipo_atraccion,String descripcion,String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo_atraccion = tipo_atraccion;
		this.descripcion=descripcion;
		this.imagen=imagen;
		
	} 
	

	public Atraccion(String nombre, Double costo, Double tiempo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
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


	public Integer getCupo() {
		return cupo;
	}


	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}


	public Integer getTipo_atraccion() {
		return tipo_atraccion;
	}


	public void setTipo_atraccion(Integer tipo_atraccion) {
		this.tipo_atraccion = tipo_atraccion;
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


	public Integer getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Atraccion [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", cupo="
				+ cupo + ", tipo_atraccion=" + tipo_atraccion + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ "]";
	}
	
	public boolean tienecupo(int i) {
		//devuelve true si es mayor
		return cupo >= i;
	}

	//le disminuye el cupo
	public void host(int i) {
		this.cupo -= i;
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

		if (cupo <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}


	
}
