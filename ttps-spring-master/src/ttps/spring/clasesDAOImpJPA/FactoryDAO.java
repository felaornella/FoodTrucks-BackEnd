package ttps.spring.clasesDAOImpJPA;

import org.springframework.transaction.annotation.*;

import ttps.spring.clasesDAO.*;
@Transactional
public class FactoryDAO {

	public static EventoDAO getEventoDAO() {
		return new EventoDAOImpJPA();
	}
	
	public static FoodTruckDAO getFoodTruckDAO() {
		return new FoodTruckDAOImpJPA();
	}
	
	public static FoodTruckerDAO getFoodTruckerDAO() {
		return new FoodTruckerDAOImpJPA();
	}
	
	public static OrganizadorDAO getOrganizadorDAO() {
		return new OrganizadorDAOImpJPA();
	}
	public static SolicitudDAO getSolicitudDAO() {
		return new SolicitudDAOImpJPA();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpJPA();
	}
	
	public static ValoracionDAO getValoracionDAO() {
		return new ValoracionDAOImpJPA();
	}
	
}
