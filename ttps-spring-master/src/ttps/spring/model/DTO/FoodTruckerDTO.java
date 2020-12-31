package ttps.spring.model.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.model.FoodTruck;
import ttps.spring.model.FoodTrucker;
import ttps.spring.model.Solicitud;


public class FoodTruckerDTO extends UsuarioDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Set<Solicitud> solicitudes= new HashSet<Solicitud>();
	
	private Set<FoodTruck> foodtrucks= new HashSet<FoodTruck>();
	
	
	
	public FoodTruckerDTO() {}
	

	public FoodTruckerDTO(FoodTrucker f) {
		this.setId(f.getId());
		this.setNombre(f.getNombre());
		this.setApellido(f.getApellido());
		this.setEmail(f.getEmail());
		this.setUsername(f.getUsername());
		this.setPassword(f.getPassword());
		this.setSolicitudes(f.getSolicitudes());
		this.setFoodtrucks(f.getFoodtrucks());
		this.setTipo_usuario("FoodTrucker");
	}

	public FoodTruckerDTO(FoodTrucker f, int n) {
		this.setId(f.getId());
		this.setNombre(f.getNombre());
		this.setApellido(f.getApellido());
		this.setEmail(f.getEmail());
		this.setUsername(f.getUsername());
		this.setPassword(f.getPassword());
//		this.setSolicitudes(f.getSolicitudes());
//		this.setFoodtrucks(f.getFoodtrucks());
		this.setTipo_usuario("FoodTrucker");
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
		foodtrucks.add(f);
	}
	
	public void eliminarFoodTruck(FoodTruckDTO f) {
		foodtrucks.remove(f);
	}
		
}
