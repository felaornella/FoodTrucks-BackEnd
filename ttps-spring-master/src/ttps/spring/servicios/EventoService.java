package ttps.spring.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import ttps.spring.model.Evento;
import ttps.spring.model.DTO.EventoDTO;

@Service
public interface EventoService {

	
	public List<EventoDTO> recuperarTodos();
    
	public EventoDTO recuperarPorId(Long id) ;
	
	public void persistir(Evento f);

	public void actualizar(Evento f);
}
