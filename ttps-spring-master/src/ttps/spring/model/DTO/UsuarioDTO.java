package ttps.spring.model.DTO;


import javax.persistence.*;

import ttps.spring.model.Usuario;


public class UsuarioDTO {
	

	private Long id;
	private String nombre;
	private String apellido;
	private String username;
	private String password;
	private String email;
	private String tipo_usuario;
	
	public String getTipo_usuario() {
		return tipo_usuario;
	}


	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}


	public UsuarioDTO() {}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioDTO(Usuario u) {
		this.id = u.getId();
		this.nombre = u.getNombre();
		this.apellido = u.getApellido();
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.email = u.getEmail();
	}
	
	
	
}
