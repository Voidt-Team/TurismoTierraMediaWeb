package ttm.model;
import java.util.List;

public class Promocion {
	
	private Integer id;
	protected String nombre;
	protected Double costo;
	protected Double tiempo;
	protected Integer tipo;
	protected Integer bonificacion;
	private List<Atraccion> lista_atracciones;
	
	public Promocion(Integer id, String nombre, Double costo, Double tiempo, Integer tipo, Integer bonificacion, List<Atraccion> lista_atracciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.bonificacion = bonificacion;
		this.lista_atracciones = lista_atracciones;
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



	//Esto que esta aca deberia ir en la parte del ABM de promociones
//	//Imprime el Bonus de la promo
//	public String ImprimirBonus() {
//		String descuentoS = "";
//		if(getAbsoluta()!= 0) {
//			double costo = 0;
//			for(Atraccion atraccion : this.lista_atracciones) {
//		    	costo += atraccion.getCosto();
//		    }
//			Double descuento = costo - getAbsoluta();
//			descuentoS = descuento.toString();
//			descuentoS = "¡se ahorra " + descuentoS + " monedas!";
//		}else if(getPorcentual() != 0) {
//			Double descuento = this.getPorcentual() * 100;
//			descuentoS = descuento.toString();
//			descuentoS = "obtiene un descuento del " + descuentoS + " %";
//		}else if(getAxb_id() !=0){
//			descuentoS = "¡obtiene " + getAxb().getNombre() + " gratis!";
//		}
//		return descuentoS;
//	}
//	
//	//Retorna el costo de la promocion
//	public double costoPromocion() {
//		double costo = 0;
//		if(getAbsoluta()!= 0) {
//			costo  = getAbsoluta();
//		}else if(getPorcentual()!= 0) {
//			    for(Atraccion atraccion : this.lista_atracciones) {
//			    	costo += atraccion.getCosto();
//			    }
//			    costo = costo - (costo * this.getPorcentual());
//		}else if(getAxb_id() !=0){
//			for(Atraccion atraccion : this.lista_atracciones) {
//			   costo += atraccion.getCosto();
//			}
//		}
//		 return costo;
//	}
//
//	//Retorna el tiempo que dura la promo
//	public double tiempoPromocion() {
//		double horas = 0;
//		if(getAbsoluta() != 0 || getPorcentual() != 0) {
//		    for(Atraccion atraccion : this.lista_atracciones) {
//		    	horas += atraccion.getTiempo();
//		    }
//		   
//		}else if(getAxb_id() !=0){
//			for(Atraccion atraccion : this.lista_atracciones) {
//				horas += atraccion.getTiempo();
//			}
//			horas += getAxb().getTiempo();
//		}
//		 return horas; 
//	}
}
