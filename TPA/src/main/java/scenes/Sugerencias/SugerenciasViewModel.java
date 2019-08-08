package scenes.Sugerencias;

import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

import domain.eventos.RepositorioEventos;
import domain.sugerencias.Sugerencia;

@Observable
public class SugerenciasViewModel {
	private Set<Sugerencia> sugerencias;
	private Sugerencia sugerenciaSeleccionada;

	public SugerenciasViewModel() {
		this.sugerencias = RepositorioEventos.eventos().stream().flatMap(evento -> evento.sugerencias().stream()).collect(Collectors.toSet());
		
	}

	public Sugerencia getSugerenciaSeleccionada() {
		return sugerenciaSeleccionada;
	}

	public void setSugerenciaaSeleccionada(Sugerencia sugerenciaSeleccionada) {
		this.sugerenciaSeleccionada = sugerenciaSeleccionada;
	}

	public Set<Sugerencia> getSugerencias() {
		return sugerencias;
	}

}
