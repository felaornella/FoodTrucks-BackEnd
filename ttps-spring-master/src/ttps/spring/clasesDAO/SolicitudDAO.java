package ttps.spring.clasesDAO;

import java.util.List;

import ttps.spring.model.Solicitud;
import ttps.spring.model.DTO.SolicitudDTO;

public interface SolicitudDAO extends GenericDAO<Solicitud>{

	List<SolicitudDTO> solicitudesDeOrganizador(Long id);
	List<SolicitudDTO> solicitudesDeFoodTrucker(Long id);
}
