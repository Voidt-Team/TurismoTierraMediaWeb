package ttm;

public class TiposDeAtracciones {
	Integer id_tipo_atraccion = 0;
	String nombre = "";

	public TiposDeAtracciones(Integer id_tipo_atraccion, String nombre) {
		super();
		this.id_tipo_atraccion = id_tipo_atraccion;
		this.nombre = nombre;
	}

	public Integer getId_tipo_atraccion() {
		return id_tipo_atraccion;
	}

	public void setId_tipo_atraccion(Integer id_tipo_atraccion) {
		this.id_tipo_atraccion = id_tipo_atraccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
