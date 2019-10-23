package domain.usuario;

import java.util.HashSet;
import java.util.Set;
import persistency.services.Service;

public class RepositorioUsuarios extends Service {
	private Set<Usuario> usuarios;
	
	public RepositorioUsuarios() {
		this.usuarios = new HashSet<Usuario>();
	}
	
	public void agregarUsuario(Usuario unUsuario) {
		this.usuarios.add(unUsuario);
	}
	
	public Set<Usuario> usuarios() {
		return this.usuarios;
	}
	
	public void notificarAlertaMeteorologica() {
		usuarios.forEach(u -> u.notificarAlertaMeteorologica());
	}
	
	public Usuario getUsuario(String username) {
		return em().createQuery("from Usuario u where u.username = :username",Usuario.class).setParameter("username", username).getSingleResult();
	}
	
}
