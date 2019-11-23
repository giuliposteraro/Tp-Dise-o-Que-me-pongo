package domain.sugerencias;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Sets;
import domain.clima.ProveedorClima;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;
import domain.prenda.Prenda;
import domain.usuario.Usuario;
import exceptions.NoSePuedeGenerarSugerencia;

public class Sugeridor {

	private Evento evento;
	private ProveedorClima provClima;

	public Sugeridor(Evento evento, ProveedorClima provClima) {
		this.evento = evento;
		this.provClima = provClima;
	}

	public void generarSugerencias() {
		Double temp = provClima.getTemp();

		Set<Prenda> prendas = this.soloPrendasSinUsar(evento.getGuardarropa().getPrendas());

		List<Sugerencia> sugerencias = obtenerSugerencias(prendas);

		sugerencias.sort((a, b) -> a.coeficienteDeAbrigo(temp).compareTo(b.coeficienteDeAbrigo(temp)));

		if (sugerencias.size() > 10) {
			sugerencias = sugerencias.subList(0, 10);
		}

		Usuario usuario = evento.getUsuario();
		sugerencias.forEach(sug -> usuario.agregarSugerencia(sug));
		sugerencias.forEach(sug -> evento.agregarSugerencia(sug));
		usuario.notificarSugerencias(evento);
	}

	private List<Sugerencia> obtenerSugerencias(Set<Prenda> prendas) {
		Set<Set<Prenda>> combinaciones = obtenerCombinacionesValidas(prendas);
		if (combinaciones.size() == 0) {
			throw new NoSePuedeGenerarSugerencia("No se pueden generar sugerencias en este guardarropa");
		}
		return convertirASugerencias(combinaciones);
	}

	private List<Sugerencia> convertirASugerencias(Set<Set<Prenda>> combinaciones) {
		Stream<Atuendo> atuendos = combinaciones.stream().map(c -> new Atuendo(c));
		return atuendos.map(atuendo -> new Sugerencia(atuendo, evento)).collect(Collectors.toList());
	}

	private Set<Set<Prenda>> obtenerCombinacionesValidas(Set<Prenda> prendas) {
		return Sets.powerSet(prendas).stream().filter(c -> Atuendo.esAtuendoValido(c)).collect(Collectors.toSet());
	}

	private Set<Prenda> soloPrendasSinUsar(Set<Prenda> prendas) {
		return prendas.stream().filter(p -> !p.getEnUso()).collect(Collectors.toSet());
	}
}
