package ttps.spring.clasesDAOImpJPA;


import ttps.spring.clasesDAO.EventoDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.DTO.EventoDTO;
import ttps.spring.model.DTO.FoodTruckDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EventoDAOImpJPA extends GenericDAOImpJPA<Evento> implements EventoDAO {

	public EventoDAOImpJPA() {
		super(Evento.class);
	}
		
	@Transactional
	public List<EventoDTO> eventosDeOrganizador(Long id){
		try {
			Query consulta= this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o where eliminado=0 and organizador_id=" + id);
			List<Evento> resultado = (List<Evento>) consulta.getResultList();
			List<EventoDTO> resultadoFinal = new ArrayList<EventoDTO>();
			for (Evento f: resultado) {
				resultadoFinal.add(new EventoDTO(f));				
			}
			return resultadoFinal;
		} catch (RuntimeException e) {
			System.out.println("Problema al buscar "+ this.getPersistentClass().getSimpleName() +" con dueno_id ingresado");
			return null;
		}
	}
	
	@Override
	public Evento recuperarPorId(Serializable id) {
		try {
			Object obj = this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o where eliminado=0 and id=" + id).getSingleResult();
			return (Evento)obj;
		} catch (RuntimeException e) {
			System.out.println("No se encuentra "+ this.getPersistentClass().getSimpleName() +" con id ingresado");
			return null;
		}
	}
	
	@Override
	public List<Evento> recuperarTodos() {
		
		Query consulta = this.getEntityManager().
				createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o where eliminado=0");
		List<Evento> resultado = (List<Evento>) consulta.getResultList();
		return resultado;
	}
}
