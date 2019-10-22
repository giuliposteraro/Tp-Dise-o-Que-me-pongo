package persistency.services;

import javax.persistence.NoResultException;
import domain.usuario.Usuario;

public class LoginService extends Service {
	public Boolean autenticar(String username, String password) {
		String query = "from Usuario u where u.username = :username";
		try {
			Usuario user = em.createQuery(query ,Usuario.class).setParameter("username", username).getSingleResult();
			return user.getPassword().equals(this.hash(password));
		} catch (NoResultException e) {
			return false;
		}
	}
}
