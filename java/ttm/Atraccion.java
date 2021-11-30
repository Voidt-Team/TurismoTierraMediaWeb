package ttm;


public class Atraccion {

	private Integer id;
	private String nombre;
	private Double costo;
	private Double tiempo;
	private Integer cupo;
	private Integer tipo_atraccion;

	public Atraccion(Integer id,String nombre, Double costo, Double tiempo, Integer cupo, Integer tipo_atraccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo_atraccion = tipo_atraccion;
	} 

	public String getNombre() {
		return nombre;
	}


	public Double getCosto() {
		return costo;
	}

	public double getTiempo() {
		return tiempo;
	}


	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public Integer getId() {
		return id;
	}

	public Integer getTipo_atraccion() {
		return tipo_atraccion;
	}
	
	
	@Override
	public String toString() {
		return "Atraccion [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", cupo="
				+ cupo + ", tipo_atraccion=" + tipo_atraccion + "]";
	}

}
