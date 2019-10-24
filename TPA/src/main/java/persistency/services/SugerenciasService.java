package persistency.services;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.eventos.Evento;
import domain.sugerencias.Sugerencia;
import domain.usuario.Usuario;


public class SugerenciasService implements WithGlobalEntityManager {

	public Set<Sugerencia> getSugerenciasForEvent(Long id_evento) {
		String query = "from Evento e where e.id_evento = :id_evento";
		return entityManager().createQuery(query, Evento.class)
				.setParameter("id_evento", id_evento)
				.getSingleResult()
				.getSugerencias();
	}
	
	public String getMotivoEvento(Long id_evento) {
		String query = "from Evento e where e.id_evento = :id_evento";
		return entityManager().createQuery(query, Evento.class)
				.setParameter("id_evento", id_evento)
				.getSingleResult()
				.getMotivo();
	}
	
	
	/*public List<Sugerencia> getSugerenciasForUser(String username) {
		String query = "from Usuario u where u.username = :username";
		return em.createQuery(query, Usuario.class)
				.setParameter("username", username)
				.getSingleResult()
				.getSugerenciasPendientes();
	}
	*/

	public Sugerencia getSugerencia(Long id) {
		String query = "from Sugerencia s where s.id_sugerencia = :id";
		return entityManager().createQuery(query, Sugerencia.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}

