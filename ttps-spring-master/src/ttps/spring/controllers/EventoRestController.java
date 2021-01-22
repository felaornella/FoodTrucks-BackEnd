package ttps.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Evento;
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
	public ResponseEntity<EventoDTO> createEvento(@RequestBody Evento evento){
		try {
			eventoService.persistir(evento); 
			
			return new ResponseEntity<EventoDTO>(new EventoDTO(evento),HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println("Problemas al persistir");
			e.printStackTrace();
			return new ResponseEntity<EventoDTO>(HttpStatus.NOT_FOUND);
		}
	}
}
