package ttps.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.Usuario;
import ttps.spring.model.Valoracion;
import ttps.spring.model.DTO.UsuarioDTO;
import ttps.spring.model.DTO.ValoracionDTO;

@Service
public interface ValoracionService {

	public List<ValoracionDTO> recuperarTodos();
    
	public ValoracionDTO recuperarPorId(Long id) ;
	
	public void persistir(Valoracion v);

	public void actualizar(Valoracion v);

}
