package ttps.spring.serviciosImp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.clasesDAO.UsuarioDAO;
import ttps.spring.clasesDAOImpJPA.UsuarioDAOImpJPA;
import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.UsuarioDTO;
import ttps.spring.servicios.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioImp;
	
	public List<UsuarioDTO> recuperarTodos(){
        List<Usuario> users = usuarioImp.recuperarTodos();
        List<UsuarioDTO> usuariosFinal = new ArrayList<UsuarioDTO>();
        for (Usuario u:users) {
        	usuariosFinal.add(new UsuarioDTO(u));
        }
        return usuariosFinal;
    }
	
	public UsuarioDTO recuperarPorId(Long id) {
		Usuario usu= usuarioImp.recuperarPorId(id);
		return new UsuarioDTO(usu);
	}
	
	public Usuario recuperarUsuarioPorId(Long id) {
		Usuario usu= usuarioImp.recuperarPorId(id);
		return usu;
	}
	
	public void persistir(Usuario usuario) {
		this.usuarioImp.persistir(usuario);
	}

	public void actualizar(Usuario usuario) {
		this.usuarioImp.actualizar(usuario);
	}

	public UsuarioDTO autenticar(String username, String clave) {
		return this.usuarioImp.autenticar(username, clave);
	}
	
	public String tipoUsuario(Long id) {
		return this.usuarioImp.tipoUsuario(id);
	}
	
}
