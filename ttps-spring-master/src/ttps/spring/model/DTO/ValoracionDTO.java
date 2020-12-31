package ttps.spring.model.DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ttps.spring.model.*;

public class ValoracionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private int limpieza;
	private int simpatia;
	private int calidad_precio;
	private int sabor;
	private int dise�o;
	
	
	public ValoracionDTO() {}


	public ValoracionDTO(Valoracion v) {
		this.id = v.getId();
		this.limpieza = v.getLimpieza();
		this.simpatia = v.getSimpatia();
		this.calidad_precio = v.getCalidad_precio();
		this.sabor = v.getSabor();
		this.dise�o = v.getDise�o();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getLimpieza() {
		return limpieza;
	}


	public void setLimpieza(int limpieza) {
		this.limpieza = limpieza;
	}


	public int getSimpatia() {
		return simpatia;
	}


	public void setSimpatia(int simpatia) {
		this.simpatia = simpatia;
	}


	public int getCalidad_precio() {
		return calidad_precio;
	}


	public void setCalidad_precio(int calidad_precio) {
		this.calidad_precio = calidad_precio;
	}


	public int getSabor() {
		return sabor;
	}


	public void setSabor(int sabor) {
		this.sabor = sabor;
	}


	public int getDise�o() {
		return dise�o;
	}


	public void setDise�o(int dise�o) {
		this.dise�o = dise�o;
	}
	
	
}
