package ttps.spring.clasesDAO;

import java.util.List;

import ttps.spring.model.FoodTruck;
import ttps.spring.model.DTO.FoodTruckDTO;

public interface FoodTruckDAO extends GenericDAO<FoodTruck>{

	List<FoodTruckDTO> foodTrucksDeFtrucker(Long id);
	
	List<String> getImages(Long id);
	
	public List<FoodTruckDTO> busqueda(String zona, String nombre, String comida);
	
	public List<FoodTruckDTO> topFoodtrucks();
	
}
