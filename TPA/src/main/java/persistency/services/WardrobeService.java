package persistency.services;

import java.util.Set;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.guardarropa.Guardarropa;
import domain.usuario.Usuario;

public class WardrobeService implements WithGlobalEntityManager {
	EntityManager em = entityManager();
	
	public Set<Guardarropa> getWardrobesForUser(String username) {
		String query = "from Usuario u where u.username = :username";
		return em.createQuery(query, Usuario.class)
				.setParameter("username", username)
				.getSingleResult()
				.getGuardarropas();
	}
}
