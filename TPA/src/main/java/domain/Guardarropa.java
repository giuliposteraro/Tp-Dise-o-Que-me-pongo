package domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import exceptions.*;

public class Guardarropa {

	private Set<Prenda> prendas;

	public Guardarropa() {
		this.prendas = new HashSet<Prenda>();
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
	
	public Set<Prenda> prendasSuperiores() {
		return filtrarPrendasPorCategoria(ECategoria.SUPERIOR);
	}
	
	public Set<Prenda> prendasInferiores() {
		return filtrarPrendasPorCategoria(ECategoria.INFERIOR);
	}
	
	public Set<Prenda> calzados() {
		return filtrarPrendasPorCategoria(ECategoria.CALZADO);
	}
	
	public Set<Prenda> accesorios() {
		return filtrarPrendasPorCategoria(ECategoria.ACCESORIO);
	}
	
	private Set<Prenda> filtrarPrendasPorCategoria(ECategoria categoria) {
		return prendas.stream()
				.filter(p -> categoria.equals(p.getCategoria()))
				.collect(Collectors.toSet());
	}
	
	public Boolean tieneLugar() {
		return true;
	}
}
