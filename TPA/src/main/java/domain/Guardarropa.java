package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import exceptions.*;

public class Guardarropa {

	private Set<Prenda> prendas;

	public Guardarropa() {
		this.prendas = new HashSet<Prenda>();
	}

	public List<Atuendo> generarSugerencias() {
		validarListas();
		return crearAtuendos(sugerenciasDePrendas());
	}

	public Boolean tienePrenda(Prenda unaPrenda) {
		return prendas.contains(unaPrenda);
	}

	public void agregarPrenda(Prenda prenda) {
		if(!this.tieneLugar()) {
			throw new CapacidadDelGuardarropaLlena("No entran mas prendas en este guardarropa");
		}
		prendas.add(prenda);
	}

	public void eliminarPrenda(Prenda prenda) {
		prendas.remove(prenda);
	}

	public Set<Prenda> getPrendas() {
		return this.prendas;
	}
	
	private Set<Prenda> prendasSuperiores() {
		return filtrarPrendasPorCategoria(ECategoria.SUPERIOR);
	}
	
	private Set<Prenda> prendasSuperioresDeAbrigo() {
		return filtrarPrendasPorCategoria(ECategoria.ABRIGO);
	}
	
	private Set<Prenda> prendasInferiores() {
		return filtrarPrendasPorCategoria(ECategoria.INFERIOR);
	}
	
	private Set<Prenda> calzados() {
		return filtrarPrendasPorCategoria(ECategoria.CALZADO);
	}
	
	private Set<Prenda> accesorios() {
		return filtrarPrendasPorCategoria(ECategoria.ACCESORIO);
	}
	
	private Set<List<Prenda>> sugerenciasDePrendas() {
		return Sets.cartesianProduct(ImmutableList.of(prendasSuperiores(),prendasSuperioresDeAbrigo(), prendasInferiores(), calzados(), accesorios()));
	}

	private List<Atuendo> crearAtuendos(Set<List<Prenda>> prendasSueltas) {
		return prendasSueltas.stream()
				.map(conjuntoDePrendas -> new Atuendo(conjuntoDePrendas))
				.collect(Collectors.toList());
	}
	
	private Set<Prenda> filtrarPrendasPorCategoria(ECategoria categoria) {
		return prendas.stream()
				.filter(p -> categoria.equals(p.getCategoria()))
				.collect(Collectors.toSet());
	}

	private void validarListas() throws NoSePuedeGenerarSugerencia {
		if(prendasSuperiores().isEmpty() || prendasSuperioresDeAbrigo().isEmpty() || prendasInferiores().isEmpty() || calzados().isEmpty() || accesorios().isEmpty())
			throw new NoSePuedeGenerarSugerencia("No se pueden generar sugerencias en este guardarropa");
	}
	
	public Boolean tieneLugar() {
		return true;
	}
	
}
