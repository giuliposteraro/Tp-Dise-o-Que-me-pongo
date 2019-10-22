package domain.eventos;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
	
import domain.Config;
import domain.usuario.RepositorioUsuarios;
import domain.usuario.Usuario;

public class RepositorioEventos implements WithGlobalEntityManager, TransactionalOps{
	EntityManager em = entityManager();
	RepositorioUsuarios repoUsuarios;
	
	private Set<Evento> eventos;	//TODO Consultar a DB
	
	public RepositorioEventos(RepositorioUsuarios ArepoUsuarios) {
		this.eventos= new HashSet<Evento>();
		repoUsuarios = ArepoUsuarios;
	}
	
	public RepositorioEventos() {
		this.eventos= new HashSet<Evento>();
	}
	
	public void agregarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
		em.persist(unEvento);
		
	}
	
	public Set<Evento> proximosEventos(){
		return em.createQuery("from Evento e where e.pendiente = true and fecha<:maniana",Evento.class)
		.setParameter("maniana",LocalDate.now().plus(2,ChronoUnit.DAYS))
		.getResultList().stream().collect(Collectors.toSet());
		
		//return this.getEventos().stream().filter(evento->evento.proximoPendiente()).collect(Collectors.toSet());
	}
	
	public void eventos(Set<Evento> unosEventos){
		eventos = unosEventos;
	}
	
	public Set<Evento> getEventos(){
		return em.createQuery("from Evento",Evento.class).getResultList().stream().collect(Collectors.toSet());
	}
	
	public List<Evento> getEventosForUser(String username) {
		Usuario user = repoUsuarios.getUsuario(username);
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
	

}