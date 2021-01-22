package ttps.spring.model.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.model.Evento;
import ttps.spring.model.Organizador;
import ttps.spring.model.Solicitud;

public class OrganizadorDTO extends UsuarioDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<SolicitudDTO> solicitudes = new HashSet<SolicitudDTO>();
	
	private Set<EventoDTO> eventos = new HashSet<EventoDTO>();
	
	
	public OrganizadorDTO() {}
	
	public OrganizadorDTO(Organizador o) {
		this.setId(o.getId());
		this.setNombre(o.getNombre());
		this.setApellido(o.getApellido());
		this.setEmail(o.getEmail());
		this.setUsername(o.getUsername());
		this.setPassword(o.getPassword());
		for (Solicitud s: o.getSolicitudes()) {
			solicitudes.add(new SolicitudDTO(s));
		}
		for (Evento e: o.getEventos()) {
			eventos.add(new EventoDTO(e));
		}
		this.setTipo_usuario("Organizador");
//		this.setSolicitudes(o.getSolicitudes());
//		this.setEventos(o.getEventos());
	}
	
	//constructor usado en EventoDTO>>setOrganizador(org)
	public OrganizadorDTO(Organizador o, int i) {
		this.setId(o.getId());
		this.setNombre(o.getNombre());
		this.setApellido(o.getApellido());
		this.setEmail(o.getEmail());
		this.setUsername(o.getUsername());
		this.setPassword(o.getPassword());
		this.setTipo_usuario("Organizador");
	}
	


	public Set<SolicitudDTO> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Set<Solicitud> solicitudes) {
		for (Solicitud s: solicitudes ) {
			this.solicitudes.add(new SolicitudDTO(s));
		}
//		this.solicitudes = solicitudes;
	}

	public Set<EventoDTO> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		for (Evento e: eventos ) {
			this.eventos.add(new EventoDTO(e));
		}
//		this.eventos = eventos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
