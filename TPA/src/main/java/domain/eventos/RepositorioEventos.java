package domain.eventos;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositorioEventos {
	
	private Set<Evento> eventos;	//TODO Consultar a DB
	
	public RepositorioEventos() {
		this.eventos= new HashSet<Evento>();
	}
	
	public void agregarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
	}
	
	public Set<Evento> proximosEventos(){
		return eventos.stream().filter(evento->evento.proximoPendiente()).collect(Collectors.toSet());
	}
	
	public void eventos(Set<Evento> unosEventos){
		eventos = unosEventos;
	}
	
	public Set<Evento> eventos(){
		return eventos;
	}
	
}