package domain.guardarropa;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import domain.tipoPrenda.ECategoria;

import domain.prenda.Prenda;

@Entity
public class Atuendo {
	
	@Id @GeneratedValue
	private Long id_atuendo;
	@ManyToMany
	private Set<Prenda> prendas;

	
	public Atuendo(Set<Prenda> prendas) {
		this.prendas = prendas;
	}
	
	private Atuendo() {}

	public Atuendo(Collection<Prenda> prendas) {
		this.prendas = prendas.stream().collect(Collectors.toSet());
	}

	public Set<Prenda> getPrendas() {
		return prendas;
	}

	public List<Double> getNivelesAbrigo() {
		return Arrays.asList(this.getNivelDeAbrigo(ECategoria.SUPERIOR) + this.getNivelDeAbrigo(ECategoria.ABRIGO),
				this.getNivelDeAbrigo(ECategoria.INFERIOR), this.getNivelDeAbrigo(ECategoria.CALZADO));
	}

	private Double getNivelDeAbrigo(ECategoria categoria) {
		return this.prendas.stream().filter(p -> p.getCategoria().equals(categoria)).map(p -> p.getNivelAbrigo())
				.mapToDouble(d -> Double.valueOf(d)).sum();
	}

	public void setPrendasEnUso(Boolean uso) {
		prendas.stream().forEach(p -> p.setEnUso(uso));
	}

	public static Boolean esAtuendoValido(Set<Prenda> prendas) {
		return cantidadDeSuperiores(prendas)==1 && cantidadDeInferiores(prendas)==1 && cantidadDeCalzado(prendas)==1
				&& cantidadDeAbrigos(prendas) <= 3;
	}
	
	public static Integer cantidadDeAbrigos(Set<Prenda> prendas) {
		return (int) prendas.stream().filter(p -> p.esAbrigo()).count();
	}
	
	private static Integer cantidadDeSuperiores(Set<Prenda> prendas) {
		return (int) prendas.stream().filter(p -> p.esSuperior()).count();
	}

	private static Integer cantidadDeInferiores(Set<Prenda> prendas) {
		return (int) prendas.stream().filter(p -> p.esInferior()).count();
	}

	private static Integer cantidadDeCalzado(Set<Prenda> prendas) {
		return (int) prendas.stream().filter(p -> p.esCalzado()).count();
	}

}
