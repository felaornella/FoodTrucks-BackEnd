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
import ttps.spring.servicios.SolicitudService;
import ttps.spring.servicios.SolicitudService;

@Service
@Transactional
public class SolicitudServiceImp implements SolicitudService {

	@Autowired
	private SolicitudDAO SolicitudImp;
	
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
	
	public void persistir(Solicitud Solicitud) {
		this.SolicitudImp.persistir(Solicitud);
	}

	public void actualizar(Solicitud Solicitud) {
		this.SolicitudImp.actualizar(Solicitud);
	}
	
	public List<SolicitudDTO> solicitudesDeOrganizador(Long id) {
		return this.SolicitudImp.solicitudesDeOrganizador(id);
	}

}
