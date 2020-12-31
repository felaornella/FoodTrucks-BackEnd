package ttps.spring.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="solicitud")
public class Solicitud implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private String estado;
	
	@OneToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="valoracion")
	private Valoracion valoracion;
	
	@OneToOne()
	@JoinColumn(name="evento")
	private Evento evento;
	
	@OneToOne(cascade= {CascadeType.PERSIST},fetch = FetchType.EAGER)
	@JoinColumn(name="foodtruck")
	private FoodTruck foodtruck;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Organizador creador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="solicitado_id")
	private FoodTrucker solicitado;
	
	public Solicitud() {
		this.estado="Enviada";
	}
	
	public Solicitud(Evento e, FoodTruck f) {
		this.estado="Enviada";
		setEvento(e);
		setFoodtruck(f);
		
	}
	
	public String toString() {
		String ret="SOLICITUD\nEstado:";
		//String[] estados= {"Enviada","Aceptada","Rechazada","Finalizada","Calificada"};
		ret=ret + this.estado;
		if(this.valoracion!=null) {
			ret=ret+"\nValoracion: " + this.valoracion.toString();	
		}
		ret=ret+"\nEvento: " + this.evento.toString();
		ret=ret+"\nFoodTruck: " + this.foodtruck.toString();
		ret=ret+"\nCreador: " + this.creador.toString();
		ret=ret+"\nSolicitado: " + this.solicitado.toString() + "\n\n";
		
		return ret;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Organizador getCreador() {
		return creador;
	}

	public void setCreador(Organizador creador) {
		this.creador = creador;
		if (!creador.getSolicitudes().contains(this)){
			creador.agregarSolicitud(this);
		}
	}

	public FoodTrucker getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(FoodTrucker solicitado) {
		this.solicitado = solicitado;
		if (!solicitado.getSolicitudes().contains(this)){
			solicitado.agregarSolicitud(this);
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public void agregarValoracion(Valoracion v) {
		this.valoracion=v;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public FoodTruck getFoodtruck() {
		return foodtruck;
	}

	public void setFoodtruck(FoodTruck foodtruck) {
		this.foodtruck = foodtruck;
	}
	
	public void setRechazada() {
		this.estado="Rechazada";
	}
	
	public void setAceptada() {
		this.estado="Aceptada";
	}
}
