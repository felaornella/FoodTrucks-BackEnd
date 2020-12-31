package ttps.spring.model.DTO;

import java.io.Serializable;

import javax.persistence.*;

import ttps.spring.model.*;

public class SolicitudDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String estado;
	
	private Valoracion valoracion;
	
	private Evento evento;
	
	private FoodTruck foodtruck;
	
	private Organizador creador;
	
	private FoodTrucker solicitado;
	
	public SolicitudDTO() {}
	
	public SolicitudDTO(Solicitud s) {
		setId(s.getId());
		setValoracion(s.getValoracion());
		setCreador(s.getCreador());
		setSolicitado(s.getSolicitado());
		setEvento(s.getEvento());
		setFoodtruck(s.getFoodtruck());
		setEstado(s.getEstado());
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Organizador getCreador() {
		return creador;
	}

	public void setCreador(Organizador creador) {
		this.creador = creador;
	}

	public FoodTrucker getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(FoodTrucker solicitado) {
		this.solicitado = solicitado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public FoodTruck getFoodtruck() {
		return foodtruck;
	}

	public void setFoodtruck(FoodTruck foodtruck) {
		this.foodtruck = foodtruck;
	}
}
