package persistency.services;

import java.util.Set;
import domain.guardarropa.Guardarropa;
import domain.usuario.Usuario;

public class WardrobeService extends Service {
	public Set<Guardarropa> getWardrobesForUser(String username) {
		String query = "from Usuario u where u.username = :username";
		return em().createQuery(query, Usuario.class)
				.setParameter("username", username)
				.getSingleResult()
				.getGuardarropas();
	}

	public Guardarropa getGuardarropa(Long id) {
		String query = "from Guardarropa g where g.id_guardarropa = :id";
		return em().createQuery(query, Guardarropa.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
