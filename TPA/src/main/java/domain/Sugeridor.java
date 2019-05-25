package domain;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import exceptions.NoSePuedeGenerarSugerencia;

public class Sugeridor {

	Usuario usuario;
	Guardarropa guardarropa;
	Date fecha;
//	ProveedorClima provClima;

	public Sugeridor(Usuario usuario, Guardarropa guardarropa, Date fecha/*, ProveedorClima provClima*/) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.fecha = fecha;
//		this.provClima = provClima;
	}

	public void generarSugerencias() {
//		int temp = provClima.getTemp();
		Set<Prenda> sup = guardarropa.prendasSuperiores();
		Set<Prenda> inf = guardarropa.prendasInferiores();
		Set<Prenda> cal = guardarropa.calzados();
		Set<Prenda> acc = guardarropa.accesorios();

		List<Sugerencia> sugerencias = obtenerSugerencias(sup, inf, cal, acc);
		
//		sugerencias.stream().filter(sugerencia -> sugerencia.esValidaParaTemp(temp));

		sugerencias.stream().forEach(sug -> usuario.agregarSugerencia(sug));
	}

	private List<Sugerencia> obtenerSugerencias(Set<Prenda> sup, Set<Prenda> inf, Set<Prenda> cal, Set<Prenda> acc) {
		validarPrendasSuficientes(sup, inf, cal, acc);
		
		return convertirASugerencias(obtenerCombinaciones(sup, inf, cal, acc));
	}
	
	private List<Sugerencia> convertirASugerencias(Set<List<Prenda>> combinaciones) {
		Set<Atuendo> atuendos = combinaciones.stream().map(combinacion -> new Atuendo(combinacion)).collect(Collectors.toSet());
		return atuendos.stream().map(atuendo -> new Sugerencia(atuendo, usuario)).collect(Collectors.toList());
	}

	private Set<List<Prenda>> obtenerCombinaciones(Set<Prenda> sup, Set<Prenda> inf, Set<Prenda> cal, Set<Prenda> acc) {
		return Sets.cartesianProduct(ImmutableList.of(sup, inf, cal, acc));
	}

	private void validarPrendasSuficientes(Set<Prenda> sup, Set<Prenda> inf, Set<Prenda> cal, Set<Prenda> acc) {
		if(sup.isEmpty() || inf.isEmpty() || cal.isEmpty() || acc.isEmpty())
			throw new NoSePuedeGenerarSugerencia("No se pueden generar sugerencias en este guardarropa");
	}
}
