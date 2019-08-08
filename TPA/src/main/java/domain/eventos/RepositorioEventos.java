package domain.eventos;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositorioEventos {
	
	public static Set<Evento> eventos;
	
	public RepositorioEventos() {
		this.eventos= new HashSet<Evento>();
	}
	public void agregarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
	}
	
	public Set<Evento> proximosEventos(){
		return eventos.stream().filter(evento->evento.esProximo()).collect(Collectors.toSet());
	}
	
	public Set<Evento> eventosProximosYPendientes(){
		return eventos.stream().filter(evento->evento.esProximo() && evento.estaPendiente()).collect(Collectors.toSet());
	}
	
	public void eventos(Set<Evento> unosEventos){
		eventos = unosEventos;
	}
	
	public Set<Evento> eventos(){
		return eventos;
	}
}