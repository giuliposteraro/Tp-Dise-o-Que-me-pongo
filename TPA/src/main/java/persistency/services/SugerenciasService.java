package persistency.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.sugerencias.Sugerencia;


public class SugerenciasService implements WithGlobalEntityManager {
	EntityManager em = entityManager();

	public List<Sugerencia> getSugerenciasForEvent(String evento) {
		String query = "from Sugerencia s where s.evento = :evento";
		return em.createQuery(query, Sugerencia.class)
				.setParameter("evento", evento)
				.getResultList();
	}
	

	public Sugerencia getSugerencia(Long id) {
		String query = "from Sugerencia s where s.id_sugerencia = :id";
		return em.createQuery(query, Sugerencia.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}

