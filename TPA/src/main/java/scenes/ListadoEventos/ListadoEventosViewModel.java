package scenes.ListadoEventos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import org.uqbar.commons.model.annotations.Observable;
import domain.eventos.Evento;
import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaIlimitado;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;

@Observable
public class ListadoEventosViewModel {
	private List<Evento> eventos;
	private Evento eventoSeleccionado;
	
	public ListadoEventosViewModel() {
		Evento unEvento = new Evento(this.nuevoUsuario(), this.nuevoGuardarropa(), LocalDate.now(), "Chivilcoy", "Vicky fea");
		
		this.eventos = new LinkedList<Evento>();
		this.eventos.add(unEvento);
	}
	
	private Usuario nuevoUsuario() {
		return new Usuario(new UsuarioPremium());
	}
	
	private Guardarropa nuevoGuardarropa() {
		return new Guardarropa(new GuardarropaIlimitado());
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}
	
	public void setEventos(List<Evento> eventos) {
	}
	
	public Evento getEventoSeleccionado() {
		return eventoSeleccionado;
	}

	public void setEventoSeleccionado(Evento eventoSeleccionado) {
		this.eventoSeleccionado = eventoSeleccionado;
	}
}
