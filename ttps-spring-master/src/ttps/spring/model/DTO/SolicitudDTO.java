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
	
	private ValoracionDTO valoracion;
	
	private EventoDTO evento;
	
	private FoodTruckDTO foodtruck;
	
	private OrganizadorDTO creador;
	
	private FoodTruckerDTO solicitado;
	
	public SolicitudDTO() {}
	
	public SolicitudDTO(Solicitud s) {
		setId(s.getId());
		setEstado(s.getEstado());
		setEvento(new EventoDTO(s.getEvento()));
		setFoodtruck(new FoodTruckDTO(s.getFoodtruck()));
		if (s.getValoracion()!= null){
			setValoracion(new ValoracionDTO(s.getValoracion()));
		}else {
			setValoracion(null);
		}
		
		setCreador(new OrganizadorDTO(s.getCreador()));
		setSolicitado(new FoodTruckerDTO(s.getSolicitado()));

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrganizadorDTO getCreador() {
		return creador;
	}

	public void setCreador(OrganizadorDTO creador) {
		this.creador = creador;
	}

	public FoodTruckerDTO getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(FoodTruckerDTO solicitado) {
		this.solicitado = solicitado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setValoracion(ValoracionDTO valoracion) {
		this.valoracion = valoracion;
	}

	public ValoracionDTO getValoracion() {
		return valoracion;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public EventoDTO getEvento() {
		return evento;
	}

	public void setEvento(EventoDTO evento) {
		this.evento = evento;
	}

	public FoodTruckDTO getFoodtruck() {
		return foodtruck;
	}

	public void setFoodtruck(FoodTruckDTO foodtruck) {
		this.foodtruck = foodtruck;
	}
}
