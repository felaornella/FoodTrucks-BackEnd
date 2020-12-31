package ttps.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.FoodTrucker;
import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.FoodTruckerDTO;
import ttps.spring.model.DTO.UsuarioDTO;

@Service
public interface FoodTruckerService {

	public List<FoodTruckerDTO> recuperarTodos();
    
	public FoodTrucker recuperarPorId(Long id) ;
	
	public void persistir(FoodTrucker f);

	public void actualizar(FoodTrucker f);

}
