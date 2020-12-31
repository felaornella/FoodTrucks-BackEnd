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

	private Set<Solicitud> solicitudes = new HashSet<Solicitud>();
	
	private Set<Evento> eventos = new HashSet<Evento>();
	
	
	public OrganizadorDTO() {}
	
	public OrganizadorDTO(Organizador o) {
		this.setId(o.getId());
		this.setNombre(o.getNombre());
		this.setApellido(o.getApellido());
		this.setEmail(o.getEmail());
		this.setUsername(o.getUsername());
		this.setPassword(o.getPassword());
		for (Solicitud s: o.getSolicitudes()) {
			solicitudes.add(s);
		}
		for (Evento e: o.getEventos()) {
			eventos.add(e);
		}
		this.setTipo_usuario("Organizador");
//		this.setSolicitudes(o.getSolicitudes());
//		this.setEventos(o.getEventos());
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

}
