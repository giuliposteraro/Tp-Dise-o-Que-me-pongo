package persistency.services;

import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;

public class RegisterService extends Service {
	public void register(String username, String password, TipoUsuario tipo) {
		Usuario user = new Usuario(tipo);
		entityManager().persist(user);
		user.setUsername(username);
		user.setPassword(this.hash(password));
		user.crearGuardarropa().setNombre("Formal");
		user.crearGuardarropa().setNombre("Casual");
	}
}
