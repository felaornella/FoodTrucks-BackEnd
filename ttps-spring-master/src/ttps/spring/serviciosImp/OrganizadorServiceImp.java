package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.OrganizadorDAOImpJPA;
import ttps.spring.model.*;
import ttps.spring.model.DTO.*;
import ttps.spring.servicios.OrganizadorService;
import ttps.spring.servicios.OrganizadorService;

@Service
@Transactional
public class OrganizadorServiceImp implements OrganizadorService {

	@Autowired
	private OrganizadorDAO OrganizadorImp;
	
	public List<OrganizadorDTO> recuperarTodos(){
        List<Organizador> orgs = OrganizadorImp.recuperarTodos();
        List<OrganizadorDTO> OrganizadorsFinal = new ArrayList<OrganizadorDTO>();
        for (Organizador o:orgs) {
        	OrganizadorsFinal.add(new OrganizadorDTO(o));
        }
        return OrganizadorsFinal;
    }
	
	public OrganizadorDTO recuperarPorId(Long id) {
		Organizador o= OrganizadorImp.recuperarPorId(id);
		return new OrganizadorDTO(o);
	}
	
	public void persistir(Organizador Organizador) {
		this.OrganizadorImp.persistir(Organizador);
	}

	public void actualizar(Organizador Organizador) {
		this.OrganizadorImp.actualizar(Organizador);
	}

}
