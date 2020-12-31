package ttps.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="organizador")
public class Organizador extends Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade= {CascadeType.ALL},
			mappedBy = "creador")
	private Set<Solicitud> solicitudes = new HashSet<Solicitud>();
	
	@OneToMany(cascade= {CascadeType.ALL},
			mappedBy = "organizador")
	private Set<Evento> eventos = new HashSet<Evento>();
	
	
	public Organizador() {}
	
	public Organizador(String nombre, String apellido, String username, String password, String email) {
		super(nombre, apellido, username, password, email);
	}
	


	public Set<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Set<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void agregarEvento(Evento e) {
		eventos.add(e);
		if(e.getOrganizador()==null){
			e.setOrganizador(this);
		}else {
			if (!e.getOrganizador().equals(this)){
				e.setOrganizador(this);	
			}
		}
	}
	
	public void eliminarEvento(Evento e) {
		eventos.remove(e);
	}
	
	public void enviarSolicitud(Evento e, FoodTruck f ) {
		Solicitud solicitud = new Solicitud();
		//solicitud.setEstado(0); se setea en el constructor
		solicitud.setEvento(e);
		solicitud.setFoodtruck(f);
		//solicitud.save();
	}
	
		
	public void calificarFoodTruck (Integer idSolicitud, Valoracion v) {
		//buscar solicitud en BBDD
		//asignar v a solicitud
		//solicitud.save()
	}
	
	/* Estado Solicitudes
	 * 0= Enviada
	 * 1= Confirmada
	 * 2= Rechazada
	 * 3= Finalizada
	 * 4= Calificada
	 * */
	
	private Set<Solicitud> filtrarSolicitudes(String filtro) {
		Set<Solicitud> temp = new HashSet<Solicitud>();
		for (Solicitud s: solicitudes) {
			if (s.getEstado() == filtro) {
				temp.add(s);
			}
		}
		return temp;
	}
	
	
	public Set<Solicitud> getSolicitudesCalificadas() {
		return filtrarSolicitudes("Calificada");
	}
	
	public Set<Solicitud> getSolicitudesFinalizadas() {
		return filtrarSolicitudes("Finalizada");
	}
	
	public Set<Solicitud> getSolicitudesRechazadas() {
		return filtrarSolicitudes("Rechazada");
	}
	
	public Set<Solicitud> getSolicitudesProximas() {
		return filtrarSolicitudes("Confirmada");
	}
	
	public Set<Solicitud> getSolicitudesPendientes() {
		return filtrarSolicitudes("Enviada");
	}

	
	public void agregarSolicitud(Solicitud s) {
		solicitudes.add(s);
		if(s.getCreador()==null){
			s.setCreador(this);
		}else {
			if (!s.getCreador().equals(this)){
				s.setCreador(this);	
			}
		}
	}
}
