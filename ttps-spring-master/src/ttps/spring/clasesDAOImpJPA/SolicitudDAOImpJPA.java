package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.SolicitudDAO;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.Solicitud;
import ttps.spring.model.DTO.FoodTruckDTO;
import ttps.spring.model.DTO.SolicitudDTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SolicitudDAOImpJPA extends GenericDAOImpJPA<Solicitud> implements SolicitudDAO {

	public SolicitudDAOImpJPA() {
		super(Solicitud.class);
	}

	@Transactional
	public List<SolicitudDTO> solicitudesDeOrganizador(Long id) {
		try {
			System.out.print(id);
			Query consulta = this.getEntityManager().createQuery(
					"SELECT s FROM " + this.getPersistentClass().getSimpleName() + " s where creador_id=" + id);
			List<Solicitud> resultado = (List<Solicitud>) consulta.getResultList();
			List<SolicitudDTO> resultadoFinal = new ArrayList<SolicitudDTO>();
			for (Solicitud s : resultado) {
				resultadoFinal.add(new SolicitudDTO(s));
			}
			return resultadoFinal;
		} catch (RuntimeException e) {
			System.out.println(
					"Problema al buscar " + this.getPersistentClass().getSimpleName() + " con creador_id ingresado");
			return null;
		}
	}

	@Transactional
	public List<SolicitudDTO> solicitudesDeFoodTrucker(Long id) {
		try {
			Query consulta = this.getEntityManager().createQuery(
					"SELECT s FROM " + this.getPersistentClass().getSimpleName() + " s where solicitado_id=" + id);
			List<Solicitud> resultado = (List<Solicitud>) consulta.getResultList();
			List<SolicitudDTO> resultadoFinal = new ArrayList<SolicitudDTO>();
			for (Solicitud s : resultado) {
				resultadoFinal.add(new SolicitudDTO(s));
			}
			return resultadoFinal;
		} catch (RuntimeException e) {
			System.out.println(
					"Problema al buscar " + this.getPersistentClass().getSimpleName() + " con solicitado_id ingresado");
			return null;
		}
	}

	@Transactional
	public List<Solicitud> recuperarPorFt(FoodTruck ft) {
		try {
			Query consulta = this.getEntityManager().createQuery(
					"SELECT s FROM " + this.getPersistentClass().getSimpleName() + " s where foodtruck=" + ft.getId());
			List<Solicitud> resultado = (List<Solicitud>) consulta.getResultList();
			return resultado;
		} catch (RuntimeException e) {
			System.out.println(
					"Problema al buscar " + this.getPersistentClass().getSimpleName() + " con solicitado_id ingresado");
			return null;
		}
	}

	@Transactional
	public boolean verificarSolicitud(SolicitudDTO soli) {
		try {
			Query consulta = this.getEntityManager()
					.createQuery("SELECT s FROM " + this.getPersistentClass().getSimpleName()
							+ " s where (estado = 'Aceptada' or estado = 'Enviada') and foodtruck=" + soli.getFoodtruck().getId()
							+ " and evento=" + soli.getEvento().getId());
			List<Solicitud> resultado = (List<Solicitud>) consulta.getResultList();
			if (resultado.isEmpty()) {
				//verificacion exitosa, se puede crear solicitud
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			System.out.println("Problema al buscar " + this.getPersistentClass().getSimpleName()
					+ " con evento y foodtruck ingresado");
			return false;
		}
	}

}
