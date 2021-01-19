package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.FoodTruckDAO;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.FoodTrucker;
import ttps.spring.model.DTO.FoodTruckDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FoodTruckDAOImpJPA extends GenericDAOImpJPA<FoodTruck> implements FoodTruckDAO {

	public FoodTruckDAOImpJPA() {
		super(FoodTruck.class);
	}
	@Transactional
	public List<FoodTruckDTO> foodTrucksDeFtrucker(Long id){
		try {
			Query consulta= this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o where dueno_id=" + id);
			List<FoodTruck> resultado = (List<FoodTruck>) consulta.getResultList();
			List<FoodTruckDTO> resultadoFinal = new ArrayList<FoodTruckDTO>();
			for (FoodTruck f: resultado) {
				f.getImagenes().size();
				resultadoFinal.add(new FoodTruckDTO(f));				
			}
			return resultadoFinal;
		} catch (RuntimeException e) {
			System.out.println("Problema al buscar "+ this.getPersistentClass().getSimpleName() +" con dueno_id ingresado");
			return null;
		}
	}
}
