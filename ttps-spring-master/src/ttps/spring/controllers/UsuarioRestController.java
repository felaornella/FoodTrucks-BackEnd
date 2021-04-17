package ttps.spring.controllers;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ttps.spring.model.*;
import ttps.spring.clasesDAO.UsuarioDAO;
import ttps.spring.servicios.*;
import ttps.spring.serviciosImp.TokenServices;
import ttps.spring.model.DTO.*;
import ttps.spring.clasesDAOImpJPA.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path="/usuario", produces= MediaType.APPLICATION_JSON_VALUE)
public class UsuarioRestController {
	
	
	@Autowired
	TokenServices tokenServices;
	
	@Autowired
	UsuarioService usuarioImp;
	
	@Autowired
	SolicitudService soliImp;

	@Autowired
	ValoracionService valoracionImp;
	
	@Autowired
	FoodTruckService foodtruckImp;
	
	@GetMapping()
	public ResponseEntity<List<UsuarioDTO>>getAllUsers(){
		List<UsuarioDTO> users = usuarioImp.recuperarTodos();
		if(users.isEmpty()) {
			return new ResponseEntity<List<UsuarioDTO>>(HttpStatus.NO_CONTENT);
		}		
		return new ResponseEntity<List<UsuarioDTO>>(users, HttpStatus.OK);
	}
	
	

	@GetMapping(path="/{id}")
	public ResponseEntity<UsuarioDTO> getUser(@PathVariable("id") String idPath, @RequestHeader("token") String token){
		Long id = Long.valueOf(idPath);
		try {
			String[] tokenPartes = new String[2];
			tokenPartes[1]=token.substring(token.length()-6,token.length());
			tokenPartes[0]=token.substring(0,token.length()-6);
			
			if (!tokenPartes[1].equals(String.valueOf(123456))) {
				System.out.println(tokenPartes[1]);
				return new ResponseEntity<UsuarioDTO>(HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception e) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.UNAUTHORIZED);
		}
		try {
			UsuarioDTO user = usuarioImp.recuperarPorId(id);
			if(user==null) {
				return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UsuarioDTO>(user,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping()
	public ResponseEntity<UsuarioDTO> createUser(@RequestBody Usuario usu){
		try {
			usuarioImp.persistir(usu);
			return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usu),HttpStatus.OK);
		}catch (RuntimeException e) {
			System.out.println("Problemas al Persistir Usuario");
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/foodtrucker")
	public ResponseEntity<Map<String,String>> createUserFoodTrucker(@RequestBody FoodTrucker usu){
		try {
			usuarioImp.persistir(usu);
			Map<String,String> body = new HashMap<String,String>();
			body.put("usuario_username",usu.getUsername());
			body.put("usuario_id",String.valueOf(usu.getId()));
			body.put("usuario_tipo_usuario","FoodTrucker");
			body.put("token", tokenServices.generateToken(usu.getUsername(), 1800));
			return new ResponseEntity<Map<String,String>>(body,HttpStatus.OK);
		}catch (RuntimeException e) {
			System.out.println("Problemas al Persistir Foodtrucker");
			return new ResponseEntity<Map<String,String>>(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/organizador")
	public ResponseEntity<Map<String,String>> createUserOrganizador(@RequestBody Organizador usu){
		try {
			usuarioImp.persistir(usu);
			Map<String,String> body = new HashMap<String,String>();
			body.put("usuario_username",usu.getUsername());
			body.put("usuario_id",String.valueOf(usu.getId()));
			body.put("usuario_tipo_usuario","Organizador");
			body.put("token", tokenServices.generateToken(usu.getUsername(), 1800));
			return new ResponseEntity<Map<String,String>>(body,HttpStatus.OK);
		}catch (RuntimeException e) {
			System.out.println("Problemas al Persistir Organizador");
			return new ResponseEntity<Map<String,String>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/autenticacion")
	public ResponseEntity<Map<String,String>> autenticar(@RequestHeader Map<String,String> mapHeaders){
		System.out.println("ENTRE");
		String username=null;
		String clave=null;
		for (Map.Entry<String, String> entry : mapHeaders.entrySet()) {
			if (entry.getKey().equals("usuario")) {
				username=entry.getValue();
			}else {
				if(entry.getKey().equals("clave")) {
					clave=entry.getValue();
				}
			}
		}
		if((username==null) || (clave==null)) {
			return new ResponseEntity<Map<String,String>>(HttpStatus.FORBIDDEN);
		}
		UsuarioDTO usu= usuarioImp.autenticar(username,clave); 
		
		if(usu==null) {
			System.out.println("no existe el usuario");
			return new ResponseEntity<Map<String,String>>(HttpStatus.FORBIDDEN);
		}else {
			String rol = usuarioImp.tipoUsuario(usu.getId());
			usu.setTipo_usuario(rol);
			System.out.println("llega al controller: "+rol);
			
			Map<String,String> body = new HashMap<String,String>();
			body.put("usuario_username",usu.getUsername());
			body.put("usuario_id",String.valueOf(usu.getId()));
			body.put("usuario_tipo_usuario",usu.getTipo_usuario());
			body.put("token", tokenServices.generateToken(usu.getUsername(), 1800));
			
			return new ResponseEntity<Map<String,String>>(body,HttpStatus.OK); 
		}
	}
	
	
	@PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable("id") long id,@RequestBody Usuario user, @RequestHeader("token")  String token){
        try {
            String[] tokenpartes = new String[2];
            tokenpartes[1] = token.substring(token.length()-6, token.length());
            tokenpartes[0] = token.substring(0, token.length()-6);

            if (!tokenpartes[1].equals(String.valueOf(123456))) {
				System.out.println(tokenpartes[1]);
				return new ResponseEntity<UsuarioDTO>(HttpStatus.UNAUTHORIZED);
			}
        }catch(Exception e) {
            return new ResponseEntity<UsuarioDTO>(HttpStatus.UNAUTHORIZED);
        }
        try {
	        Usuario usuario = usuarioImp.recuperarUsuarioPorId(id);
	        if(usuario==null) {
	            return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
	        }
	        usuario.setApellido(user.getApellido());
	        usuario.setEmail(user.getEmail());
	        usuario.setNombre(user.getNombre());
	        usuario.setPassword(user.getPassword());
	        usuario.setUsername(user.getUsername());
	
	        usuarioImp.actualizar(usuario);
	        return new ResponseEntity<UsuarioDTO>(HttpStatus.OK);
       	
        }catch(Exception e) {
            return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
        }
    }
	

	/* ------------------- */
	@GetMapping(path="/{id}/solicitudes")
	public ResponseEntity<List<SolicitudDTO>> getSolicitudes(@PathVariable("id") String idUsuario, @RequestHeader("tipoUsuario") String tipo){
		Long id = Long.valueOf(idUsuario);

		try {
			UsuarioDTO user = usuarioImp.recuperarPorId(id);
			if(user==null) {
				System.out.println("no existe usuario con id = "+ id);
				return new ResponseEntity<List<SolicitudDTO>>(HttpStatus.NOT_FOUND);
			}
			List<SolicitudDTO> solicitudes = this.soliImp.solicitudesDeUsuario(id,tipo);
			if(solicitudes.isEmpty()) {
				return new ResponseEntity<List<SolicitudDTO>>(HttpStatus.NO_CONTENT);
			}
			
			System.out.println(solicitudes.size()+" elementos retornados");
			
			return new ResponseEntity<List<SolicitudDTO>>(solicitudes,HttpStatus.OK);
					
		}catch (Exception e) {
			return new ResponseEntity<List<SolicitudDTO>>(HttpStatus.NOT_FOUND);
		}
	}	

	@PostMapping ("/nuevaSolicitud")
    public ResponseEntity<SolicitudDTO> newSolicitud(@RequestBody SolicitudDTO soli, @RequestHeader("token")  String token){
        try {
            String[] tokenpartes = new String[2];
            tokenpartes[1] = token.substring(token.length()-6, token.length());
            tokenpartes[0] = token.substring(0, token.length()-6);
            
            if (!tokenpartes[1].equals(String.valueOf(123456))) {
				System.out.println(tokenpartes[1]);
				return new ResponseEntity<SolicitudDTO>(HttpStatus.UNAUTHORIZED);
			}
        }catch(Exception e) {
            return new ResponseEntity<SolicitudDTO>(HttpStatus.UNAUTHORIZED);
        }
        try {
			soliImp.persistir(soli);
			System.out.println("Persistio Solicitud");
			return new ResponseEntity<SolicitudDTO>(soli,HttpStatus.OK);
		}catch (RuntimeException e) {
			System.out.println("Problemas al Persistir Solicitud");
			return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
		}
    }
	
	@GetMapping(path="/recuperarSolicitud/{id}")
	public ResponseEntity<SolicitudDTO> getSolicitudEspecifica(@PathVariable("id") String idPath, @RequestHeader("token") String token){
		Long id = Long.valueOf(idPath);
		try {
			String[] tokenPartes = new String[2];
			tokenPartes[1]=token.substring(token.length()-6,token.length());
			tokenPartes[0]=token.substring(0,token.length()-6);
			
			if (!tokenPartes[1].equals(String.valueOf(123456))) {
				System.out.println(tokenPartes[1]);
				return new ResponseEntity<SolicitudDTO>(HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception e) {
			return new ResponseEntity<SolicitudDTO>(HttpStatus.UNAUTHORIZED);
		}
		try {
			SolicitudDTO soli = soliImp.recuperarPorId(id);
			if(soli==null) {
				return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<SolicitudDTO>(soli,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/valorarSolicitud/{id}")
	public ResponseEntity<SolicitudDTO> newValoracion(@PathVariable("id") String idSolicitud, @RequestBody Valoracion valoracion){
		Long id = Long.valueOf(idSolicitud);
		try {
			this.soliImp.agregarValoracionASolicitud(id,valoracion);
			return new ResponseEntity<SolicitudDTO>(this.soliImp.recuperarPorId(id),HttpStatus.OK);
		}catch (RuntimeException e) {
			System.out.println("Problemas al Persistir valoracion");
			return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/buscar")
	public ResponseEntity<List<FoodTruckDTO>> nuevaBusqueda(@RequestParam Map<String, String> parametros){
		String zona = parametros.get("Zona");
		String nombre = parametros.get("Nombre");
		String comida = parametros.get("Comida");
		
		List<FoodTruckDTO> list = this.foodtruckImp.busqueda(zona,nombre,comida);
		if(list.isEmpty()) {
			return new ResponseEntity<List<FoodTruckDTO>>(HttpStatus.NO_CONTENT);
		}
		
		System.out.println(list.size());
		return new ResponseEntity<List<FoodTruckDTO>>(list,HttpStatus.OK);
	}
	

	@PutMapping("/modificarSolicitud/{id}")
    public ResponseEntity<SolicitudDTO> modificarEstadoSolicitud(@PathVariable("id") String idSolicitud, @RequestParam Map<String,String> estado){ 
		//, @RequestHeader("token")  String token
        try {
        	System.out.println(estado.get("estado"));
        	Long id = Long.valueOf(idSolicitud);
        	Solicitud s = this.soliImp.recuperarSolicitudPorId(id);

        	if (this.soliImp.modificarEstadoSolicitud(s, estado.get("estado"))) {
        		return new ResponseEntity<SolicitudDTO>(this.soliImp.recuperarPorId(id), HttpStatus.OK);
        	}
            return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException e) {
			return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
		}
    }
	
	@PutMapping("/cancelarSolicitud/{id}")
    public ResponseEntity<SolicitudDTO> cancelarSolicitud(@PathVariable("id") String idSolicitud){ 
        try {
        	Long id = Long.valueOf(idSolicitud);
        	Solicitud s = this.soliImp.recuperarSolicitudPorId(id);
        	System.out.println("el estado de la solicitud fue "+ s.getEstado());
        	if (s.getEstado().equals("Enviada")) {
        		System.out.println("Entre");	
	        	if (this.soliImp.modificarEstadoSolicitud(s, "Cancelada")) {
	        		System.out.println("Entre2");
	        		return new ResponseEntity<SolicitudDTO>(this.soliImp.recuperarPorId(id), HttpStatus.OK);
	        	}
        	}else {
        		System.out.println("Else1");
        		return new ResponseEntity<SolicitudDTO>(HttpStatus.BAD_REQUEST);
        	}
            return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
        }catch (RuntimeException e) {
			return new ResponseEntity<SolicitudDTO>(HttpStatus.NOT_FOUND);
		}
    }
	
	
	
	

	
	
	
	
	
	
	
	
}
