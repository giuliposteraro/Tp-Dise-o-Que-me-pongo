package scenes.ListadoEventos;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.uqbar.commons.model.annotations.Observable;

import domain.Config;
import domain.eventos.Evento;
import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaIlimitado;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;

@Observable
public class ListadoEventosViewModel {
	private Set<Evento> eventos = Config.instance().getRepositorioEventos().eventos();
	private Evento eventoSeleccionado;
	private LocalDate fechaDesde;
	
	public ListadoEventosViewModel() {
		Evento unEvento1 = new Evento(this.nuevoUsuario(), this.nuevoGuardarropa(), LocalDate.now(), "", "A");
		Evento unEvento2 = new Evento(this.nuevoUsuario(), this.nuevoGuardarropa(), LocalDate.now(), "", "B");
		Evento unEvento3 = new Evento(this.nuevoUsuario(), this.nuevoGuardarropa(), LocalDate.now(), "", "C");
		Config.instance().getRepositorioEventos().agregarEvento(unEvento1);
		Config.instance().getRepositorioEventos().agregarEvento(unEvento2);
		Config.instance().getRepositorioEventos().agregarEvento(unEvento3);
		
	}
	
	private Usuario nuevoUsuario() {
		return new Usuario(new UsuarioPremium());
	}
	
	private Guardarropa nuevoGuardarropa() {
		return new Guardarropa(new GuardarropaIlimitado());
	}
	
	public Set<Evento> getEventos() {
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

	public void setFechaDesde(String fechaDesde) {
		if(fechaDesde.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
			 String[] fecha = fechaDesde.split("/");
			 this.fechaDesde = LocalDate.of(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
		}
	}
}
