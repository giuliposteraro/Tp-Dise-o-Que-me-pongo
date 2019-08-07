package domain.usuario;

import java.util.HashSet;
import java.util.Set;

public class RepositorioUsuarios {

	private Set<Usuario> usuarios;
	
	public RepositorioUsuarios() {
		this.usuarios = new HashSet<Usuario>();
	}
	
	public void agregarUsuario(Usuario unUsuario) {
		this.usuarios.add(unUsuario);
	}
	
	public Set<Usuario> usuarios(){
		return this.usuarios;
	}
	
	public void notificarAlertaMeteorologica() {
		usuarios.forEach(u -> u.notificarAlertaMeteorologica());
	}
}
