package ttps.spring.servicios;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.FoodTruckDTO;
import ttps.spring.model.DTO.FoodTruckerDTO;
import ttps.spring.model.DTO.UsuarioDTO;

@Service
public interface FoodTruckService {

	public List<FoodTruckDTO> recuperarTodos();
    
	public FoodTruckDTO recuperarPorId(Long id) ;
	
	public FoodTruck recuperarFoodTruckPorId(Long id) ;
	
	public void persistir(FoodTruckDTO f);

	public Boolean actualizar(Long id, FoodTruckDTO f);
	
	public Boolean agregarFoto(Long id, Map<String,Object> f);
	
	public void borrar (FoodTruck f);
	
	public List<String> getImages (Long id);
	
	public List<FoodTruckDTO> busqueda(String zona, String nombre, String comida);
	
	public List<FoodTruckDTO> foodTrucksDeFtrucker(Long id);
	
	public List<FoodTruckDTO> topFoodtrucks();

	public List<String> topFoodtrucksPics(List<FoodTruckDTO> lista);

}
