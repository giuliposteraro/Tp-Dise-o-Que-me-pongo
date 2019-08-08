package domain.guardarropa;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import domain.prenda.Prenda;
import domain.tipoPrenda.ECategoria;
import exceptions.*;

public class Guardarropa {

	private Set<Prenda> prendas;
	private TipoGuardarropa tipoGuardarropa;

	public Guardarropa(TipoGuardarropa tipo) {
		this.prendas = new HashSet<Prenda>();
		this.tipoGuardarropa = tipo;

		// Prendas Opcionales
		agregarPrenda(Prenda.SIN_ABRIGO);
		agregarPrenda(Prenda.SIN_ACCESORIO);
	}
	
	public TipoGuardarropa getTipoGuardarropa() {
		return this.tipoGuardarropa;
	}
	
	public void setTipoGuardarropa(TipoGuardarropa tipo) {
		this.tipoGuardarropa = tipo;
	}
	
	public Boolean tieneLugar() {
		return tipoGuardarropa.tieneLugar(this);
	}
	
	public int cantidadPrendas() {
		return prendas.size();
	}

	public Boolean tienePrenda(Prenda unaPrenda) {
		return prendas.contains(unaPrenda);
	}

	public void agregarPrenda(Prenda prenda) {
		if(!tipoGuardarropa.tieneLugar(this)) {
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
	
	public Set<Prenda> prendasSuperioresDeAbrigo() {
		return filtrarPrendasPorCategoria(ECategoria.ABRIGO);
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
				.filter(p -> !p.getEnUso())
				.filter(p -> categoria.equals(p.getCategoria()))
				.collect(Collectors.toSet());
	}
}
