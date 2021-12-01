package ttm;

public class Itinerario {
	private Integer id_itinerario;
	private Integer id_atraccion;
	private Integer promo_id;
	private Integer id_usuario;


	public Itinerario(Integer id_itinerario, Integer id_atraccion, Integer promo_id, Integer id_usuario) {
		super();
		this.setId_itinerario(id_itinerario);
		this.setId_atraccion(id_atraccion);
		this.setPromo_id(promo_id);
		this.setId_usuario(id_usuario);
	}


	public Integer getId_itinerario() {
		return id_itinerario;
	}


	public void setId_itinerario(Integer id_itinerario) {
		this.id_itinerario = id_itinerario;
	}


	public Integer getId_atraccion() {
		return id_atraccion;
	}


	public void setId_atraccion(Integer id_atraccion) {
		this.id_atraccion = id_atraccion;
	}


	public Integer getPromo_id() {
		return promo_id;
	}


	public void setPromo_id(Integer promo_id) {
		this.promo_id = promo_id;
	}


	public Integer getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

}
