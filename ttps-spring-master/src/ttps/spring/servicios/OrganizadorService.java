package ttps.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.Organizador;
import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.OrganizadorDTO;
import ttps.spring.model.DTO.UsuarioDTO;

@Service
public interface OrganizadorService {

	public List<OrganizadorDTO> recuperarTodos();
    
	public OrganizadorDTO recuperarPorIdDTO(Long id) ;
	
	public Organizador recuperarPorId(Long id) ;
	
	public void persistir(Organizador o);

	public void actualizar(Organizador o);

}
