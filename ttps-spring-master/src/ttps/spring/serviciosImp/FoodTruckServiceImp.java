package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.FoodTruckDAOImpJPA;
import ttps.spring.model.*;
import ttps.spring.model.DTO.*;
import ttps.spring.servicios.FoodTruckService;
import ttps.spring.servicios.FoodTruckerService;
import ttps.spring.servicios.FoodTruckService;

@Service
@Transactional
public class FoodTruckServiceImp implements FoodTruckService {

	@Autowired
	private FoodTruckDAO FoodTruckImp;

	@Autowired
	FoodTruckerService foodtruckerImp;
	
	public List<FoodTruckDTO> recuperarTodos(){
        List<FoodTruck> fts = FoodTruckImp.recuperarTodos();
        List<FoodTruckDTO> FoodTrucksFinal = new ArrayList<FoodTruckDTO>();
        for (FoodTruck f:fts) {
        	FoodTrucksFinal.add(new FoodTruckDTO(f));
        }
        return FoodTrucksFinal;
    }
	 
	public List<FoodTruckDTO> foodTrucksDeFtrucker(Long id){
		return this.FoodTruckImp.foodTrucksDeFtrucker(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getImages(Long id){
		//return this.FoodTruckImp.getImages(id);
		FoodTruck ft = this.FoodTruckImp.recuperarPorId(id);
		ft.getImagenes().size();
		return ft.getImagenes();
	}
	
	public FoodTruckDTO recuperarPorId(Long id) {
		FoodTruck ft= FoodTruckImp.recuperarPorId(id);
		
		return new FoodTruckDTO(ft);
	}

	public FoodTruck recuperarFoodTruckPorId(Long id) {
		System.out.println("Recibi el ID: " + id);
		FoodTruck ft= FoodTruckImp.recuperarPorId(id);
		System.out.println("Encontre el foodtruck: " + ft.toString());
		return ft;
	}
	
	public void persistir(FoodTruckDTO ft) {
		FoodTrucker owner = foodtruckerImp.recuperarPorId(ft.getDueno().getId());
		FoodTruck ft2= new FoodTruck(ft,owner);
		for (String s: ft.getImagenes()) {
			ft2.agregarImagen(s);
		}
		System.out.println("HABIA FOTOS2:  " + ft2.getImagenes().size());
		this.FoodTruckImp.persistir(ft2);
	}

	
	public Boolean actualizar(Long id,FoodTruckDTO ftruck) {
		System.out.println("ENTRE A METODO");
		FoodTruck foodtruck = this.recuperarFoodTruckPorId(id);
		if (foodtruck == null) {
			System.out.println("Fue Verdadero");
			return false;
		}else {
			System.out.println("Fue Falso, resul= " + foodtruck.toString());
		}
		foodtruck.setDescripcion(ftruck.getDescripcion());
        foodtruck.setFacebook(ftruck.getFacebook());
        foodtruck.setInstagram(ftruck.getInstagram());
        foodtruck.setNombre(ftruck.getNombre());
        foodtruck.setURL(ftruck.getURL());
        foodtruck.setWhatsapp(ftruck.getWhatsapp());
        foodtruck.setTipo_servicio(ftruck.getTipo_servicio());	
        foodtruck.setEliminado(ftruck.getEliminado());
        foodtruck.setImagenes(ftruck.getImagenes());
        System.out.println("Termine las asignaciones");
//        System.out.println("Owner: " + ftruck.getDueno().toString());
//        System.out.println("El ID del dueño es : " + ftruck.getDueno().getId());
        FoodTrucker owner = foodtruckerImp.recuperarPorId(ftruck.getDueno().getId());
        System.out.println("El dueño es: " + owner.toString());
        foodtruck.setDueno(owner);
		return this.FoodTruckImp.actualizar(foodtruck);
	}

	public Boolean agregarFoto(Long id,Map<String,Object> pic) {
		System.out.println("ENTRE A ADD_PIC");
		FoodTruck foodtruck = this.recuperarFoodTruckPorId(id);
		if (foodtruck == null) {
			System.out.println("Fue Null");
			return false;
		}
		foodtruck.agregarImagen(pic.get("imagen"));
		//this.FoodTruckImp.agregarImagen(id,pic.get("imagen"));
		return true;
	}
	
	public void borrar(FoodTruck FoodTruck) {
		this.FoodTruckImp.borrar(FoodTruck);
	}
	
	public List<FoodTruckDTO> busqueda(String zona, String nombre, String comida){
		return this.FoodTruckImp.busqueda(zona,nombre,comida);
	}
	
	public List<FoodTruckDTO> topFoodtrucks(){
		return this.FoodTruckImp.topFoodtrucks();
	}
	
	public List<String> topFoodtrucksPics(List<FoodTruckDTO> lista){
		List<String> retorno = new ArrayList<String>();
		for (FoodTruckDTO ft: lista) {
			FoodTruck f = this.FoodTruckImp.recuperarPorId(ft.getId());
			retorno.add(f.getImagenes().get(0));
		}
		return retorno;
	}
}
