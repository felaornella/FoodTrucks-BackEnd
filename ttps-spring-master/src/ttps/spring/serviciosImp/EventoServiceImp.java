package ttps.spring.serviciosImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.EventoDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.DTO.EventoDTO;
import ttps.spring.servicios.EventoService;

@Service
@Transactional
public class EventoServiceImp implements EventoService {
	
	@Autowired
	private EventoDAO eventoImp;

	@Override
	public List<EventoDTO> recuperarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventoDTO recuperarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistir(Evento f) {
		this.eventoImp.persistir(f);		
	}

	@Override
	public void actualizar(Evento f) {
		// TODO Auto-generated method stub
		
	}

}
