package domain.eventos;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioEventos implements WithGlobalEntityManager, TransactionalOps{
	EntityManager em = entityManager();
	
	private Set<Evento> eventos;	//TODO Consultar a DB
	
	public RepositorioEventos() {
		this.eventos= new HashSet<Evento>();
	}
	
	public void agregarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
		withTransaction(()->
			em.persist(unEvento));
		
	}
	
	public Set<Evento> proximosEventos(){
		return this.getEventos().stream().filter(evento->evento.proximoPendiente()).collect(Collectors.toSet());
	}
	
	public void eventos(Set<Evento> unosEventos){
		eventos = unosEventos;
	}
	
	public Set<Evento> getEventos(){
		return em.createQuery("from Evento",Evento.class).getResultList().stream().collect(Collectors.toSet());
	}
	
}