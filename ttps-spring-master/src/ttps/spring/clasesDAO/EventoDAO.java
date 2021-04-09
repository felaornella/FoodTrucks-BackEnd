package ttps.spring.clasesDAO;

import java.util.List;

import ttps.spring.model.Evento;
import ttps.spring.model.DTO.EventoDTO;

public interface EventoDAO extends GenericDAO<Evento> {
	public List<EventoDTO> eventosDeOrganizador(Long id);
}
