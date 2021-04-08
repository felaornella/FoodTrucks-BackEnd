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
	
	public List<SolicitudDTO> solicitudesDeOrganizador(Long id) {
		return this.SolicitudImp.solicitudesDeOrganizador(id);
	}
	
	public void agregarValoracionASolicitud(Long id, Valoracion v) {
		Solicitud s= SolicitudImp.recuperarPorId(id);
		s.setValoracion(v);
		SolicitudImp.actualizar(s);
	}

}
