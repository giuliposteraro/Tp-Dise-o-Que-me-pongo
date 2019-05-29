package domain;
import java.util.HashSet;

public class RepositorioEventos {
	
	private Set<Evento> eventos;
	
	public RepositorioEventos() {
		this.eventos= new Hashset<Evento>();
	}
	public void agregarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
	}
	
	public Set<Evento> proximosEventos(){
		return eventos.filter(evento->evento.esProximo);
	}
	
	public Set<Evento> eventos(Set<Evento> unosEventos){
		eventos = unosEventos;
	}
	
	public Set<Evento> eventos(){
		return this.eventos;
	}
	
	
	
	
}