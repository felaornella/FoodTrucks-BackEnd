package ttps.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import ttps.spring.model.DTO.FoodTruckDTO;

@Entity
@Table(name = "foodtruck")
public class FoodTruck implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nombre;
	private String tipo_servicio;
	private String descripcion;
	private String url;
	private String instagram;
	private String whatsapp;
	private String facebook;
	private Integer puntaje;
	private int eliminado;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "imagenes_foodtrucks", joinColumns = @JoinColumn(name = "id_foodtruck"))
	@Column(name = "file_name")
	private List<String> imagenes = new ArrayList<String>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private FoodTrucker dueno;

	public FoodTruck() {
	}

	public FoodTruck(String nombre, String tipo_servicio, String descripcion, String url, String instagram,
			String whatsapp, String facebook, Integer puntaje, List<String> img) {
		super();
		this.nombre = nombre;
		this.tipo_servicio = tipo_servicio;
		this.descripcion = descripcion;
		this.url = url;
		this.instagram = instagram;
		this.whatsapp = whatsapp;
		this.facebook = facebook;
		this.puntaje = puntaje;
		this.imagenes = img;
		this.eliminado = 0;
	}
	
	public FoodTruck(String nombre, String tipo_servicio, String descripcion, String url, String instagram,
			String whatsapp, String facebook, Integer puntaje) {
		super();
		this.nombre = nombre;
		this.tipo_servicio = tipo_servicio;
		this.descripcion = descripcion;
		this.url = url;
		this.instagram = instagram;
		this.whatsapp = whatsapp;
		this.facebook = facebook;
		this.puntaje = puntaje;
		this.eliminado = 0;
	}

	public FoodTruck(FoodTruckDTO f, FoodTrucker unFoodTrucker) {
		super();
		this.nombre = f.getNombre();
		this.tipo_servicio = f.getTipo_servicio();
		this.descripcion = f.getDescripcion();
		this.url = f.getURL();
		this.instagram = f.getInstagram();
		this.whatsapp = f.getWhatsapp();
		this.facebook = f.getFacebook();
		this.puntaje = 0;
		this.dueno = unFoodTrucker;
		this.eliminado = f.getEliminado();
	}

	public String toString() {
		String ret ="FOODTRUCK";
		ret = ret + "ID: " + String.valueOf(this.id)+"\nNombre: " + this.nombre;
		ret = ret + "\nTipo Servicio: " + this.tipo_servicio +"\nDescripcion: " + this.descripcion;
		ret = ret + "\nurl: " + this.url +"\nInstagram: " + this.instagram + " - WhatsApp: " + this.whatsapp + " - Facebook: " + this.facebook ;
		ret = ret + "\nPuntaje: " + String.valueOf(this.puntaje);
		
		ret = ret + "\nDueno:" + this.dueno.toString() + "\n\n";
		return ret;
	}

	
	public int getEliminado() {
		return eliminado;
	}

	public void setEliminado(int eliminado) {
		this.eliminado = eliminado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodTrucker getDueno() {
		return dueno;
	}

	public void setDueno(FoodTrucker dueno) {
		this.dueno = dueno;
		if(!dueno.getFoodtrucks().contains(this)) {
			dueno.agregarFoodTruck(this);
		}
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

	public void setURL(String Url) {
		url = Url;
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

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

	public void agregarImagen(Object imagen) {
		imagenes.add(imagen.toString());
	}
	
	public void sumarPuntaje(Integer puntos) {
		this.puntaje = this.puntaje + puntos;
	}

}
