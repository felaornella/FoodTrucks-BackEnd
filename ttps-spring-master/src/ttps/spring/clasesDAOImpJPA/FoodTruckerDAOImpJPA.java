package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.FoodTruckerDAO;
import ttps.spring.model.FoodTrucker;

import org.springframework.stereotype.Repository;

@Repository
public class FoodTruckerDAOImpJPA extends GenericDAOImpJPA<FoodTrucker> implements FoodTruckerDAO {

	public FoodTruckerDAOImpJPA() {
		super(FoodTrucker.class);
	}
}
