package domain.sugerencias;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import domain.clima.ProveedorClima;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;
import domain.prenda.Prenda;
import exceptions.NoSePuedeGenerarSugerencia;

public class Sugeridor {
	Evento evento;
	ProveedorClima provClima;

	public Sugeridor(Evento evento, ProveedorClima provClima) {
		this.evento = evento;
		this.provClima = provClima;
	}

	public void generarSugerencias() {
		Double temp = provClima.getTemp();
		Set<Prenda> abr = evento.getGuardarropa().prendasSuperioresDeAbrigo();
		Set<Prenda> sup = evento.getGuardarropa().prendasSuperiores();
		Set<Prenda> inf = evento.getGuardarropa().prendasInferiores();
		Set<Prenda> cal = evento.getGuardarropa().calzados();
		Set<Prenda> acc = evento.getGuardarropa().accesorios();
		
		List<Sugerencia> sugerencias = obtenerSugerencias(abr, sup, inf, cal, acc);
		
		sugerencias.sort((a, b) -> a.coeficienteDeAbrigo(temp).compareTo(b.coeficienteDeAbrigo(temp)));
		
		if(sugerencias.size() > 10) {
			sugerencias = sugerencias.subList(0, 5);
		}
		
		sugerencias.forEach(sug -> evento.getUsuario().agregarSugerencia(sug));
	}

	private List<Sugerencia> obtenerSugerencias(Set<Prenda> abr, Set<Prenda> sup, Set<Prenda> inf, Set<Prenda> cal, Set<Prenda> acc) {
		validarPrendasSuficientes(abr, sup, inf, cal, acc);
		
		return convertirASugerencias(obtenerCombinaciones(abr, sup, inf, cal, acc));
	}
	
	private List<Sugerencia> convertirASugerencias(Set<List<Prenda>> combinaciones) {
		Set<Atuendo> atuendos = combinaciones.stream().map(combinacion -> new Atuendo(combinacion)).collect(Collectors.toSet());
		return atuendos.stream().map(atuendo -> new Sugerencia(atuendo, evento)).collect(Collectors.toList());
	}

	private Set<List<Prenda>> obtenerCombinaciones(Set<Prenda> abr, Set<Prenda> sup, Set<Prenda> inf, Set<Prenda> cal, Set<Prenda> acc) {
		Set<Prenda> abrigos = obtenerCombinacionesDeAbrigos(abr);
		return Sets.cartesianProduct(ImmutableList.of(abrigos, sup, inf, cal, acc));
	}

	public Set<Prenda> obtenerCombinacionesDeAbrigos(Set<Prenda> abr) {
		Set<Prenda> newAbr = abr.stream().flatMap(abrigo -> combinarAbrigo(abrigo, abr).stream()).collect(Collectors.toSet());
		return newAbr;
	}
	
	private Set<Prenda> combinarAbrigo(Prenda abrigo, Set<Prenda> abr) {
		Set<Prenda> res = abr.stream().map(unAbrigo -> abrigo.ponerSobre(unAbrigo)).filter(e -> e != null).collect(Collectors.toSet());
		if(res.size() == 0)
			res.add(Prenda.SIN_ABRIGO);
		return res;
	}

	private void validarPrendasSuficientes(Set<Prenda> abr, Set<Prenda> sup, Set<Prenda> inf, Set<Prenda> cal, Set<Prenda> acc) {
		if(abr.isEmpty() || sup.isEmpty() || inf.isEmpty() || cal.isEmpty() || acc.isEmpty())
			throw new NoSePuedeGenerarSugerencia("No se pueden generar sugerencias en este guardarropa");
	}
}
