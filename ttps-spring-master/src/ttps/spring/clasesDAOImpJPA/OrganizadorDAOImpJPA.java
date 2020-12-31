package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.OrganizadorDAO;
import ttps.spring.model.Organizador;

import org.springframework.stereotype.Repository;

@Repository
public class OrganizadorDAOImpJPA extends GenericDAOImpJPA<Organizador> implements OrganizadorDAO {

	public OrganizadorDAOImpJPA() {
		super(Organizador.class);
	}
	
	
}
