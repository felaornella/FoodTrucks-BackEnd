package ttps.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Usuario;
import ttps.spring.model.Valoracion;
import ttps.spring.model.DTO.SolicitudDTO;
import ttps.spring.model.DTO.UsuarioDTO;

@Service
public interface SolicitudService {

	public List<SolicitudDTO> recuperarTodos();
    
	public SolicitudDTO recuperarPorId(Long id) ;
	
	public Solicitud recuperarSolicitudPorId(Long id); 
	
	public void persistir(SolicitudDTO s);

	public void actualizar(Solicitud s);

	public List<SolicitudDTO> solicitudesDeUsuario (Long id, String tipo);

	public void agregarValoracionASolicitud(Long id, Valoracion valoracion);

	public Boolean modificarEstadoSolicitud(Solicitud s, String estado);

	public void cerrarFt(FoodTruck foodTruck);

	public boolean verificarSolicitud(SolicitudDTO soli);

}
