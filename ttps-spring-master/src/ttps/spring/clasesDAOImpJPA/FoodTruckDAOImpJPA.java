package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.FoodTruckDAO;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.FoodTrucker;
import ttps.spring.model.DTO.FoodTruckDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	
	@Transactional
	public List<String> getImages(Long id){
		try {
			Query consulta= this.getEntityManager().
					createQuery("SELECT file_name FROM imagenes_foodtrucks where id_foodtruck=" + id + ";");
			List<String> resultado = (List<String>) consulta.getResultList();
			return resultado;
		} catch (RuntimeException e) {
			System.out.println("Problema al imagenes para "+ this.getPersistentClass().getSimpleName());
			return null;
		}
	}
	
	
	@Transactional
	public List<FoodTruckDTO> busqueda(String zona, String nombre, String comida){
		try {
			String anexoZona="descripcion LIKE '%" + zona + "%'";
			
			String anexoNombre="nombre LIKE '%" + nombre + "%'";
			
			String anexoComida="tipo_servicio LIKE '%" + comida + "%'";
			
			Query consulta= this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o where " + anexoZona + " AND " + anexoNombre + " AND " + anexoComida);
			
			List<FoodTruck> resultado = (List<FoodTruck>) consulta.getResultList();
			System.out.print(String.valueOf(resultado.size()));
			List<FoodTruckDTO> resultadoFinal = new ArrayList<FoodTruckDTO>();
			for (FoodTruck f: resultado) {
				//f.getImagenes().size();
				System.out.print(f.toString());
				resultadoFinal.add(new FoodTruckDTO(f));				
			}
			return resultadoFinal;
		} catch (RuntimeException e) {
			System.out.println("Problema al buscar "+ this.getPersistentClass().getSimpleName());
			return null;
		}
	}
	
	@Transactional
	public List<FoodTruckDTO> topFoodtrucks(){
		try {
			Query consulta= this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o order by puntaje desc");
			List<FoodTruck> resultado = (List<FoodTruck>) consulta.getResultList();
			System.out.print(String.valueOf(resultado.size()));
			List<FoodTruckDTO> resultadoFinal = new ArrayList<FoodTruckDTO>();
			for (FoodTruck f: resultado) {
				//f.getImagenes().size();
				System.out.print(f.toString());
				resultadoFinal.add(new FoodTruckDTO(f));				
			}
			return resultadoFinal;
		} catch (RuntimeException e) {
			System.out.println("Problema al buscar "+ this.getPersistentClass().getSimpleName());
			return null;
		}
	}

}
