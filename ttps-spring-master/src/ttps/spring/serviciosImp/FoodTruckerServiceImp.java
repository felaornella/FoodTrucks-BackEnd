package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.FoodTruckerDAOImpJPA;
import ttps.spring.model.*;
import ttps.spring.model.DTO.*;
import ttps.spring.servicios.FoodTruckerService;
import ttps.spring.servicios.FoodTruckerService;

@Service
@Transactional
public class FoodTruckerServiceImp implements FoodTruckerService{

	@Autowired
	private FoodTruckerDAO foodtruckerImp;
	
	public List<FoodTruckerDTO> recuperarTodos(){
        List<FoodTrucker> fts = foodtruckerImp.recuperarTodos();
        List<FoodTruckerDTO> foodtruckersFinal = new ArrayList<FoodTruckerDTO>();
        for (FoodTrucker f: fts) {
        	foodtruckersFinal.add(new FoodTruckerDTO(f));
        }
        return foodtruckersFinal;
    }
	
	public FoodTrucker recuperarPorId(Long id) {
		FoodTrucker f= (FoodTrucker)foodtruckerImp.recuperarPorId(id);
		return f;
	}
	
	public void persistir(FoodTrucker foodTrucker) {
		this.foodtruckerImp.persistir(foodTrucker);
	}

	public void actualizar(FoodTrucker foodTrucker) {
		this.foodtruckerImp.actualizar(foodTrucker);
	}

}
