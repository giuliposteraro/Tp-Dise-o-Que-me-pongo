package domain.guardarropa;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import domain.prenda.Prenda;
import domain.tipoPrenda.ECategoria;
import exceptions.*;
import persistency.converters.TipoGuardarropaConverter;

@Entity
public class Guardarropa {
	@Id @GeneratedValue
	private Long id_guardarropa;
	@OneToMany(cascade= CascadeType.PERSIST) @JoinColumn(name = "id_guardarropa")
	private Set<Prenda> prendas;
	@Convert(converter = TipoGuardarropaConverter.class)
	private TipoGuardarropa tipoGuardarropa;
	private String nombre;

	private Guardarropa() {}
	
	public Guardarropa(TipoGuardarropa tipo) {
		this.prendas = new HashSet<Prenda>();
		this.tipoGuardarropa = tipo;
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
				.filter(p -> categoria.equals(p.getCategoria()))
				.collect(Collectors.toSet());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId_guardarropa() {
		return id_guardarropa;
	}
}
