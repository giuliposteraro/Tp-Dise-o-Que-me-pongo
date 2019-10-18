package persistency.services;

import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.google.common.hash.Hashing;

import domain.usuario.Usuario;

public class LoginService implements WithGlobalEntityManager {
	EntityManager em = entityManager();
	
	public Boolean autenticar(String username, String password) {
		String query = "from Usuario u where u.username = :username";
		try {
			Usuario user = em.createQuery(query ,Usuario.class).setParameter("username", username).getSingleResult();
			return user.getPassword().equals(this.hashPassword(password));
		} catch (NoResultException e) {
			return false;
		}
	}
	
	private String hashPassword(String clearPassword) {
		return Hashing.sha256().hashString(clearPassword, StandardCharsets.UTF_8).toString();
	}
}
