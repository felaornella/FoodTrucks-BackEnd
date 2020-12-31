package ttps.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="foodtrucker")
public class FoodTrucker extends Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade= {CascadeType.ALL},
			mappedBy = "solicitado")
	private Set<Solicitud> solicitudes= new HashSet<Solicitud>();
	
	@OneToMany(cascade= {CascadeType.ALL},
			mappedBy = "dueño")
	private Set<FoodTruck> foodtrucks= new HashSet<FoodTruck>();
	
	
	
	public FoodTrucker() {}
	

	public FoodTrucker(String nombre, String apellido, String username, String password, String email) {
		super(nombre, apellido, username, password, email);
	}

	public Set<Solicitud> getSolicitudes() {
		return solicitudes;
	}


	public void setSolicitudes(Set<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}


	public Set<FoodTruck> getFoodtrucks() {
		return foodtrucks;
	}


	public void setFoodtrucks(Set<FoodTruck> foodtrucks) {
		this.foodtrucks = foodtrucks;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void agregarFoodTruck(FoodTruck f) {
		this.foodtrucks.add(f);
		if(f.getDueño()==null){
			f.setDueño(this);
		}else {
			if (!f.getDueño().equals(this)){
				f.setDueño(this);	
			}
		}
	}
	
	public void eliminarFoodTruck(FoodTruck f) {
		foodtrucks.remove(f);
	}
	
	public void aceptarSolicitud(Solicitud s) {
		s.setAceptada();
	}
	
	public void rechazarSolicitud(Solicitud s) {
		s.setRechazada();
	}
	
	public Set<Solicitud> getMisSolicitudes() {
		return solicitudes;
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
		if(s.getSolicitado()==null){
			s.setSolicitado(this);
		}else {
			if (!s.getSolicitado().equals(this)){
				s.setSolicitado(this);	
			}
		}
	}
	
}
