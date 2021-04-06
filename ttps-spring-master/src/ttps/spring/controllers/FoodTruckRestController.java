package ttps.spring.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import ttps.spring.model.*;
import ttps.spring.clasesDAO.FoodTruckDAO;
import ttps.spring.clasesDAOImpJPA.*;
import ttps.spring.servicios.*;
import ttps.spring.model.DTO.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path="/foodtruck", produces= MediaType.APPLICATION_JSON_VALUE)
public class FoodTruckRestController {
	@Autowired
	FoodTruckService foodtruckImp;
	
	@PostMapping()
	public ResponseEntity<FoodTruckDTO> createFoodTruck(@RequestBody FoodTruckDTO ft){
		try {
			foodtruckImp.persistir(ft); 
			 
			return new ResponseEntity<FoodTruckDTO>(ft,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println("Problemas al persistir");
			e.printStackTrace();
			return new ResponseEntity<FoodTruckDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<FoodTruckDTO> deleteFoodTruck(@PathVariable("id") String idPath) {
		Long id = Long.valueOf(idPath);
		FoodTruck ft = foodtruckImp.recuperarFoodTruckPorId(id);
		
		if (ft == null) {
			return new ResponseEntity<FoodTruckDTO>(HttpStatus.NOT_FOUND);
		}
		foodtruckImp.borrar(ft);
		return new ResponseEntity<FoodTruckDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<FoodTruckDTO>>getFoodTrucksFromOwnerById(@PathVariable("id") String idPath){
		System.out.println(idPath);
		Long id = Long.valueOf(idPath);
		List<FoodTruckDTO> list = this.foodtruckImp.foodTrucksDeFtrucker(id);
		if(list.isEmpty()) {
			return new ResponseEntity<List<FoodTruckDTO>>(HttpStatus.NO_CONTENT);
		}
		
		System.out.println(list.size());
		return new ResponseEntity<List<FoodTruckDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/recuperarIndividual/{id}")
	public ResponseEntity<FoodTruckDTO>getOnlyFoodTruck(@PathVariable("id") String idPath){
		System.out.println(idPath);
		Long id = Long.valueOf(idPath);
		FoodTruckDTO ftRec= this.foodtruckImp.recuperarPorId(id);
		if(ftRec == null) {
			return new ResponseEntity<FoodTruckDTO>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<FoodTruckDTO>(ftRec,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<FoodTruck> updateFoodTruck(@PathVariable("id") String idPath,@RequestBody FoodTruckDTO ftruck, @RequestHeader("token")  String token){
		Long id = Long.valueOf(idPath);
		try {
            String[] tokenpartes = new String[2];
            tokenpartes[1] = token.substring(token.length()-6, token.length());
            tokenpartes[0] = token.substring(0, token.length()-6);

            if (!tokenpartes[1].equals(String.valueOf(123456))) {
				System.out.println(tokenpartes[1]);
				return new ResponseEntity<FoodTruck>(HttpStatus.UNAUTHORIZED);
			}
        }catch(Exception e) {
            return new ResponseEntity<FoodTruck>(HttpStatus.UNAUTHORIZED);
        }
		try {
			Boolean check= foodtruckImp.actualizar(id,ftruck);
	        if(!check) {
	            return new ResponseEntity<FoodTruck>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<FoodTruck>(HttpStatus.OK);
		}catch(Exception e) {
            return new ResponseEntity<FoodTruck>(HttpStatus.NOT_FOUND);
        }
    }
	
	//NO FUNCIONA, NO SE PORQUE, PERO EN TEORIA RECIBE UN STRING EN BASE64 DE LA IMAGEN PARA GUARDARLO. DESPUES ANGULAR LO DECODIFICA Y LISTO---------------------------------------
	/*@PostMapping("/pruebaImagen/{id}")
    public ResponseEntity<FoodTruck> agregarImagenAFoodtruck(@PathVariable("id") String idPath,@RequestBody String pic){
		Long id = Long.valueOf(idPath);
		System.out.println("LLEGUE, RECIBI ESTO: \n" + pic);
		try {
			Boolean check= foodtruckImp.agregarFoto(id,pic);
	        if(!check) {
	            return new ResponseEntity<FoodTruck>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<FoodTruck>(HttpStatus.OK);
		}catch(Exception e) {
            return new ResponseEntity<FoodTruck>(HttpStatus.NOT_FOUND);
        }
    }*/
	
}
