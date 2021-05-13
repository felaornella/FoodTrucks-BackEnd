package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.SolicitudDAOImpJPA;
import ttps.spring.model.*;
import ttps.spring.model.DTO.*;
import ttps.spring.servicios.EventoService;
import ttps.spring.servicios.FoodTruckService;
import ttps.spring.servicios.FoodTruckerService;
import ttps.spring.servicios.OrganizadorService;
import ttps.spring.servicios.SolicitudService;
import ttps.spring.servicios.SolicitudService;

@Service
@Transactional
public class SolicitudServiceImp implements SolicitudService {

	@Autowired
	private SolicitudDAO SolicitudImp;
	
	@Autowired
	FoodTruckService ftService;
	
	@Autowired
	FoodTruckerService ftruckerService;
	
	@Autowired
	OrganizadorService orgService;
	
	@Autowired
	EventoService evService;
	
	public List<SolicitudDTO> recuperarTodos(){
        List<Solicitud> sts = SolicitudImp.recuperarTodos();
        List<SolicitudDTO> SolicitudsFinal = new ArrayList<SolicitudDTO>();
        for (Solicitud s:sts) {
        	SolicitudsFinal.add(new SolicitudDTO(s));
        }
        return SolicitudsFinal;
    }
	
	public SolicitudDTO recuperarPorId(Long id) {
		Solicitud s= SolicitudImp.recuperarPorId(id);
		return new SolicitudDTO(s);
	}
	
	public Solicitud recuperarSolicitudPorId(Long id) {
		Solicitud s= SolicitudImp.recuperarPorId(id);
		return (s);
	}
	
	public void persistir(SolicitudDTO soli) {
		Long evID=soli.getEvento().getId();
		Long ftID=soli.getFoodtruck().getId();
		Long orID=soli.getCreador().getId();
		Long ftrID=soli.getSolicitado().getId();
		Evento evento = evService.recuperarPorId(evID);
		FoodTruck foodtruck = ftService.recuperarFoodTruckPorId(ftID);
		Organizador organizador = orgService.recuperarPorId(orID);
		FoodTrucker foodtrucker = ftruckerService.recuperarPorId(ftrID);
		this.SolicitudImp.persistir(new Solicitud(evento,foodtruck,organizador,foodtrucker));
	}

	public void actualizar(Solicitud Solicitud) {
		this.SolicitudImp.actualizar(Solicitud);
	}
	
	public List<SolicitudDTO> solicitudesDeUsuario(Long id, String tipo) {
		System.out.println("Tipo recibido:	" + tipo);
		List<SolicitudDTO> answer = null;
		if (tipo.equals("Organizador")){
			System.out.println("Entre a orga");	
			answer= this.SolicitudImp.solicitudesDeOrganizador(id);			
		}else {
			if (tipo.equals("FoodTrucker")){
				System.out.println("Entre a foodtruck");
				answer= this.SolicitudImp.solicitudesDeFoodTrucker(id);			
			}
		}
		return answer;
	}
	
	public void agregarValoracionASolicitud(Long id, Valoracion v) {
		Solicitud s= SolicitudImp.recuperarPorId(id);
		FoodTruck f= ftService.recuperarFoodTruckPorId(s.getFoodtruck().getId());
		f.sumarPuntaje(v.getTotal());
		ftService.actualizar(f.getId(), new FoodTruckDTO(f));
		s.setValoracion(v);
		s.setCalificada();
		SolicitudImp.actualizar(s);
	}
	
	public Boolean modificarEstadoSolicitud(Solicitud s, String st) {
		try {
			switch (st) {
			case "Aceptada": s.setAceptada();	
				break;
			case "Rechazada": s.setRechazada();
				break;
			case "Finalizada": s.setFinalizada();
				break;
			case "Cancelada": s.setCancelada();
				break;
			default:
				throw new RuntimeException("estado invalido");
			}
			this.SolicitudImp.actualizar(s);
			return true;
		}catch(Exception e) {
        	System.out.println(st + "es un estado invalido");
        	return false;
			
		}
	}

	public void cerrarFt(FoodTruck ft){
		List<Solicitud> solis=this.SolicitudImp.recuperarPorFt(ft);
		System.out.println("---------------------------------------------------");
		System.out.println("				la cantidad para eliminar es " + solis.size());
		for (Solicitud s: solis) {
			if (s.getEstado().equals("Enviada") || (s.getEstado().equals("Aceptada") || (s.getEstado().equals("Finalizada")))) {
				System.out.println("Rechaze");
				s.setRechazada();
			}
		}
		System.out.println("---------------------------------------------------");
		
	}

	@Override
	public boolean verificarSolicitud(SolicitudDTO soli) {
		return this.SolicitudImp.verificarSolicitud(soli);
		
	}
}
