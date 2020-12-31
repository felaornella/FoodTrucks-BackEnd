package ttps.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="valoracion")
public class Valoracion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private int limpieza;
	private int simpatia;
	private int calidad_precio;
	private int sabor;
	private int diseño;
	
	
	public Valoracion() {}


	public Valoracion(int limpieza, int simpatia, int calidad_precio, int sabor, int diseño) {
		super();
		this.limpieza = limpieza;
		this.simpatia = simpatia;
		this.calidad_precio = calidad_precio;
		this.sabor = sabor;
		this.diseño = diseño;
	}

	public String toString() {
		return "Valoracion:\nID:	"+ this.id.toString() + "\nLimpieza:	" + String.valueOf(this.limpieza) + "\nSimpatia:	" + String.valueOf(this.simpatia) + "\nCalidad/Precio:	" + String.valueOf(this.calidad_precio) + "\nSabor:	" + String.valueOf(this.sabor) + "\nDiseño:	" + String.valueOf(this.diseño) + "\n\n";
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


	public int getDiseño() {
		return diseño;
	}


	public void setDiseño(int diseño) {
		this.diseño = diseño;
	}
	
	
}
