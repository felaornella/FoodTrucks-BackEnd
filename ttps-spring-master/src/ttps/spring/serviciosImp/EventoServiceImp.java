package ttps.spring.serviciosImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.EventoDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.FoodTrucker;
import ttps.spring.model.Organizador;
import ttps.spring.model.DTO.EventoDTO;
import ttps.spring.model.DTO.FoodTruckDTO;
import ttps.spring.servicios.EventoService;
import ttps.spring.servicios.OrganizadorService;

@Service
@Transactional
public class EventoServiceImp implements EventoService {
	
	@Autowired
	private EventoDAO eventoImp;
	
	@Autowired
	OrganizadorService organizadorImp;
	

	@Override
	public List<EventoDTO> recuperarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento recuperarPorId(Long id) {
		Evento e= eventoImp.recuperarPorId(id);
		e.getOrganizador().getApellido();
		return e;
	}

	@Override
	public Evento persistir(EventoDTO e) {
		Organizador org = organizadorImp.recuperarPorId(e.getOrganizador().getId());
		Evento evento = this.eventoImp.persistir(new Evento(e,org));	
		return evento;
	}

	@Override
	public Boolean actualizar(Long id,EventoDTO e) {
		System.out.println("ENTRE A METODO");
		Evento evento= this.eventoImp.recuperarPorId(id);
		if (evento == null) {
			System.out.println("Fue Verdadero");
			return false;
		}
		evento.setNombre(e.getNombre());
		evento.setDireccion(e.getDireccion());
		evento.setCodigo_postal(e.getCodigo_postal());
		evento.setNombre(e.getNombre());
		evento.setProvincia(e.getProvincia());
		evento.setGeolocalizacion(e.getGeolocalizacion());
		evento.setFecha_hora(e.getFecha_hora());
		evento.setEmail(e.getEmail());
		evento.setTel_contacto(e.getTel_contacto());
		evento.setDescripcion(e.getDescripcion());
		evento.setTipo_evento(e.getTipo_evento());
		evento.setForma_pago(e.getForma_pago());
        System.out.println("Termine las asignaciones");
        System.out.println("Owner: " + e.getOrganizador().toString());
        System.out.println("El ID del dueño es : " + e.getOrganizador().getId());
        Organizador orga = organizadorImp.recuperarPorId(e.getOrganizador().getId());
        System.out.println("El dueño es: " + orga.toString());
        evento.setOrganizador(orga);
		return this.eventoImp.actualizar(evento);
		
	}
	
	public List<EventoDTO> eventosDeOrganizador(Long id){
		return this.eventoImp.eventosDeOrganizador(id);
	}

	@Override
	public EventoDTO recuperarPorIdDTO(Long id) {
		Evento e= eventoImp.recuperarPorId(id);
		return new EventoDTO(e);
	}

}
