package persistency.services;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.eventos.Evento;
import domain.sugerencias.Sugerencia;
import domain.usuario.Usuario;


public class SugerenciasService implements WithGlobalEntityManager {
	EntityManager em = entityManager();

	public List<Sugerencia> getSugerenciasForEvent(Evento evento) {
		String query = "from Sugerencia s where s.evento = :evento";
		return em.createQuery(query, Sugerencia.class)
				.setParameter("evento", evento)
				.getResultList();
	}
	
	public List<Sugerencia> getSugerenciasForUser(String username) {
		String query = "from Usuario u where u.username = :username";
		return em.createQuery(query, Usuario.class)
				.setParameter("username", username)
				.getSingleResult()
				.getSugerenciasPendientes();
	}

	public Sugerencia getSugerencia(Long id) {
		String query = "from Sugerencia s where s.id_sugerencia = :id";
		return em.createQuery(query, Sugerencia.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}

