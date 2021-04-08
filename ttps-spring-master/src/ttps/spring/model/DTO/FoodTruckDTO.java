package ttps.spring.model.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import ttps.spring.model.FoodTruck;
import ttps.spring.model.FoodTrucker;


public class FoodTruckDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String tipo_servicio;
	private String descripcion;
	private String url;
	private String instagram;
	private String whatsapp;
	private String facebook;
	private Integer puntaje;
	
	private Set<String> imagenes = new HashSet<String>();
	
	private FoodTruckerDTO dueno;
	

	public FoodTruckDTO() {}

	public FoodTruckDTO(FoodTruck f) {
		this.id = f.getId();
		this.nombre = f.getNombre();
		this.tipo_servicio = f.getTipo_servicio();
		this.descripcion = f.getDescripcion();
		this.url = f.getURL();
		this.instagram = f.getInstagram();
		this.whatsapp = f.getWhatsapp();
		this.facebook = f.getFacebook();
		this.puntaje = f.getPuntaje();
		this.imagenes= f.getImagenes();
		this.dueno= new FoodTruckerDTO(f.getDueno(), 1);
	}
	
	/*-----*/
	public FoodTruckDTO(FoodTruck f, int n) {
		this.id = f.getId();
		this.nombre = f.getNombre();
		this.tipo_servicio = f.getTipo_servicio();
		this.descripcion = f.getDescripcion();
		this.url = f.getURL();
		this.instagram = f.getInstagram();
		this.whatsapp = f.getWhatsapp();
		this.facebook = f.getFacebook();
		this.puntaje = f.getPuntaje();
		this.imagenes= f.getImagenes();
	}

	public String toString() {
		String ret ="FOODTRUCK";
		ret = ret + "ID: " + String.valueOf(this.id)+"\nNombre: " + this.nombre;
		ret = ret + "\nTipo Servicio: " + this.tipo_servicio +"\nDescripcion: " + this.descripcion;
		ret = ret + "\nURL: " + this.url +"\nInstagram: " + this.instagram + " - WhatsApp: " + this.whatsapp + " - Facebook: " + this.facebook ;
		ret = ret + "\nPuntaje: " + String.valueOf(this.puntaje);
		return ret;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodTruckerDTO getDueno() {
		return dueno;
	}

	public void setDueno(FoodTruckerDTO dueno) {
		this.dueno = dueno;

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo_servicio() {
		return tipo_servicio;
	}


	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getURL() {
		return url;
	}


	public void setURL(String uRL) {
		url = uRL;
	}


	public String getInstagram() {
		return instagram;
	}


	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}


	public String getWhatsapp() {
		return whatsapp;
	}


	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}


	public String getFacebook() {
		return facebook;
	}


	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}


	public Integer getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}


	public Set<String> getImagenes() {
		return imagenes;
	}


	public void setImagenes(Set<String> imagenes) {
		this.imagenes = imagenes;
	}

	public void agregarImagen(String imagen) {
		imagenes.add(imagen);
	}
	
}
