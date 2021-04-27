package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Evento;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.DTO.EventoDTO;
import ttps.spring.model.DTO.FoodTruckDTO;
import ttps.spring.servicios.EventoService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path="/eventos", produces= MediaType.APPLICATION_JSON_VALUE)
public class EventoRestController {
	
	@Autowired
	EventoService eventoService;


	@PostMapping()
	public ResponseEntity<EventoDTO> createEvento(@RequestBody EventoDTO evento){
		try {
			Evento ev = eventoService.persistir(evento); 

			return new ResponseEntity<EventoDTO>(new EventoDTO(ev),HttpStatus.OK);
			
		}catch(RuntimeException e) {
			System.out.println("Problemas al persistir");
			e.printStackTrace();
			return new ResponseEntity<EventoDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/recuperarIndividual/{id}")
	public ResponseEntity<EventoDTO>getOnlyEvento(@PathVariable("id") String idPath){
		try {
			System.out.println(idPath);
			Long id = Long.valueOf(idPath);
			Evento ev = this.eventoService.recuperarPorId(id);
//			EventoDTO ev= this.eventoService.recuperarPorIdDTO(id);
			if(ev == null) {
				System.out.println("no se encontro evento con id "+id);
				return new ResponseEntity<EventoDTO>(HttpStatus.NO_CONTENT);
			}
			EventoDTO evento = new EventoDTO(ev);
			return new ResponseEntity<EventoDTO>(evento,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println("Problemas al recuperar evento");
			return new ResponseEntity<EventoDTO>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<EventoDTO>>getEventosFromOrganizadorById(@PathVariable("id") String idPath){
		System.out.println("id recibido: "+idPath);
		Long id = Long.valueOf(idPath);
		List<EventoDTO> list = this.eventoService.eventosDeOrganizador(id);
		if(list.isEmpty()) {
			return new ResponseEntity<List<EventoDTO>>(HttpStatus.NO_CONTENT);
		}
		
		System.out.println("cantidad retornada: "+list.size());
		for (EventoDTO e: list) {
			System.out.println(e.getGeolocalizacion());
		}
		return new ResponseEntity<List<EventoDTO>>(list,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable("id") String idPath,@RequestBody EventoDTO ev){
		System.out.println("entra a la api ");
		try {
			System.out.println("entra a la api ");
			Long id = Long.valueOf(idPath);
			System.out.println("estado de evento a modificar: "+ ev.getEliminado());
			Boolean check= eventoService.actualizar(id,ev);
	        if(!check) {
	            return new ResponseEntity<Evento>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Evento>(HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("no entra a actualizar ");
            return new ResponseEntity<Evento>(HttpStatus.NOT_FOUND);
        }
    }
	
	
}
