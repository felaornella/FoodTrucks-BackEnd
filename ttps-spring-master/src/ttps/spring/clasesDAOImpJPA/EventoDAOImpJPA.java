package ttps.spring.clasesDAOImpJPA;


import ttps.spring.clasesDAO.EventoDAO;
import ttps.spring.model.Evento;

import org.springframework.stereotype.Repository;

@Repository
public class EventoDAOImpJPA extends GenericDAOImpJPA<Evento> implements EventoDAO {

	public EventoDAOImpJPA() {
		super(Evento.class);
	}
}
