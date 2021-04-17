package ttps.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.UsuarioDTO;

@Service
public interface UsuarioService {

	public List<UsuarioDTO> recuperarTodos();
    
	public UsuarioDTO recuperarPorId(Long id) ;
	
	public Usuario recuperarUsuarioPorId(Long id) ;
	
	public void persistir(Usuario usuario);

	public void actualizar(Usuario usuario, String usernameOriginal, String emailOriginal);

	public UsuarioDTO autenticar(String username, String clave);

	public String tipoUsuario(Long id);

}
