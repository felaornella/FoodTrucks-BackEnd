package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.ValoracionDAO;
import ttps.spring.model.Valoracion;

import org.springframework.stereotype.Repository;

@Repository
public class ValoracionDAOImpJPA extends GenericDAOImpJPA<Valoracion> implements ValoracionDAO{

	public ValoracionDAOImpJPA() {
		super(Valoracion.class);
	}
	
	public String getImagen() {
		
		
		return "";
	}
}
