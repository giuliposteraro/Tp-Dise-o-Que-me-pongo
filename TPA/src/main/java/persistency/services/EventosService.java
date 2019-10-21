package persistency.services;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.eventos.Evento;
import domain.guardarropa.Guardarropa;
import domain.usuario.Usuario;

public class EventosService implements WithGlobalEntityManager {
	EntityManager em = entityManager();

	public List<Evento> getEventosForUser(String username) {
		Usuario user = this.getUsuario(username);
		String query = "from Evento e where e.usuario = :username";
		return em.createQuery(query, Evento.class)
				.setParameter("username", user)
				.getResultList();
	}

	public Evento getEvento(Long id) {
		String query = "from Evento e where e.id_evento = :id";
		return em.createQuery(query, Evento.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public Usuario getUsuario(String username) {
		return em.createQuery("from Usuario u where u.username = :username",Usuario.class).setParameter("username", username).getSingleResult();

	}
}
