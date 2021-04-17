package ttps.spring.model.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import ttps.spring.model.Evento;
import ttps.spring.model.Organizador;

public class EventoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String direccion;
	private int codigo_postal;
	private String provincia;
	private String geolocalizacion; 
	private String fecha_hora;
	private String email;
	private String tel_contacto;
	private String descripcion;
	private String tipo_evento;
	private String forma_pago;
	private int eliminado;
	private OrganizadorDTO organizador;
	
	
	public EventoDTO() {}

	public EventoDTO(Evento e) {
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.direccion = e.getDireccion();
		this.codigo_postal = e.getCodigo_postal();
		this.provincia = e.getProvincia();
		this.fecha_hora = e.getFecha_hora();
		this.email = e.getEmail();
		this.tel_contacto = e.getTel_contacto();
		this.descripcion = e.getDescripcion();
		this.tipo_evento = e.getTipo_evento();
		this.forma_pago = e.getForma_pago();
		this.setOrganizador(e.getOrganizador());
		this.eliminado = e.getEliminado();
	}
	
	/*-- prov --*/
	public EventoDTO(Evento e, int n) {
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.direccion = e.getDireccion();
		this.codigo_postal = e.getCodigo_postal();
		this.provincia = e.getProvincia();
		this.fecha_hora = e.getFecha_hora();
		this.email = e.getEmail();
		this.tel_contacto = e.getTel_contacto();
		this.descripcion = e.getDescripcion();
		this.tipo_evento = e.getTipo_evento();
		this.forma_pago = e.getForma_pago();
		this.eliminado = e.getEliminado();
	}

	public String toString() {
		String ret="EVENTO\n";
		ret = ret + "ID: " + String.valueOf(this.id) + "\nNombre: " + this.nombre;
		ret = ret + "\nDireccion: " + this.direccion + " - Codigo Postal: " + String.valueOf(this.codigo_postal);
		ret = ret + "\nProvincia: " + this.provincia + " - Fecha: " + this.fecha_hora;
		ret = ret + "\nTelefono Contacto: " + this.tel_contacto + " - Email Contacto: " + this.email;
		ret = ret + "\nDescripcion: " + this.descripcion + " - Tipo Evento: " + this.tipo_evento + " - Forma de Pago: " + this.forma_pago;
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


	public OrganizadorDTO getOrganizador() {
		return organizador;
	}


	public void setOrganizador(Organizador organizador) {
		this.organizador = new OrganizadorDTO(organizador,1);
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel_contacto() {
		return tel_contacto;
	}

	public void setTel_contacto(String tel_contacto) {
		this.tel_contacto = tel_contacto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo_evento() {
		return tipo_evento;
	}

	public void setTipo_evento(String tipo_evento) {
		this.tipo_evento = tipo_evento;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	
	
}
