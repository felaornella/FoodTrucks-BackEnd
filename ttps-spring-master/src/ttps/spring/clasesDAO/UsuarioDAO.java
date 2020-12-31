package ttps.spring.clasesDAO;

import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.UsuarioDTO;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	UsuarioDTO autenticar(String username, String clave);

	String tipoUsuario(Long id);

}
