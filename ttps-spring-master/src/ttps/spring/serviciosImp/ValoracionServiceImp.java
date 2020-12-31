package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.ValoracionDAOImpJPA;
import ttps.spring.model.*;
import ttps.spring.model.DTO.*;
import ttps.spring.servicios.ValoracionService;
import ttps.spring.servicios.ValoracionService;

@Service
@Transactional
public class ValoracionServiceImp implements ValoracionService {

	@Autowired
	private ValoracionDAO ValoracionImp;
	
	public List<ValoracionDTO> recuperarTodos(){
        List<Valoracion> vs = ValoracionImp.recuperarTodos();
        List<ValoracionDTO> ValoracionsFinal = new ArrayList<ValoracionDTO>();
        for (Valoracion v:vs) {
        	ValoracionsFinal.add(new ValoracionDTO(v));
        }
        return ValoracionsFinal;
    }
	
	public ValoracionDTO recuperarPorId(Long id) {
		Valoracion v= ValoracionImp.recuperarPorId(id);
		return new ValoracionDTO(v);
	}
	
	public void persistir(Valoracion Valoracion) {
		this.ValoracionImp.persistir(Valoracion);
	}

	public void actualizar(Valoracion Valoracion) {
		this.ValoracionImp.actualizar(Valoracion);
	}

}
