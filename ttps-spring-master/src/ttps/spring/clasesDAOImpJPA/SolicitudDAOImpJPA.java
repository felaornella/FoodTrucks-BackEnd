package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.SolicitudDAO;
import ttps.spring.model.Solicitud;

import org.springframework.stereotype.Repository;

@Repository
public class SolicitudDAOImpJPA extends GenericDAOImpJPA<Solicitud> implements SolicitudDAO {

	public SolicitudDAOImpJPA() {
		super(Solicitud.class);
	}
}
