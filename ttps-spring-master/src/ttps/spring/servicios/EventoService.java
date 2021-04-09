package ttps.spring.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import ttps.spring.model.Evento;
import ttps.spring.model.DTO.EventoDTO;

@Service
public interface EventoService {

	
	public List<EventoDTO> recuperarTodos();
    
	public Evento recuperarPorId(Long id) ;
	
	public EventoDTO recuperarPorIdDTO(Long id) ;
	
	public Evento persistir(EventoDTO f);

	public Boolean actualizar(Long id, EventoDTO f);
	
	public List<EventoDTO> eventosDeOrganizador(Long id);
}
