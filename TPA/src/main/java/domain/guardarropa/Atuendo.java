package domain.guardarropa;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import domain.tipoPrenda.ECategoria;

import domain.prenda.Prenda;

public class Atuendo {

	private Set<Prenda> prendas;

	public Atuendo(Set<Prenda> prendas) {
		this.prendas = prendas;
	}

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

	public void ponerPrendasEnUso() {
		prendas.stream().forEach(p -> p.setEnUso(true));
	}

	public static Boolean esAtuendoValido(Set<Prenda> prendas) {
		return tieneUnSuperior(prendas) && tieneUnInferior(prendas) && tieneUnCalzado(prendas)
				&& cantidadDeAbrigos(prendas) <= 3;
	}

	private static Boolean tieneUnSuperior(Set<Prenda> prendas) {
		return prendas.stream().filter(p -> p.esSuperior()).count() == 1;
	}

	private static Boolean tieneUnInferior(Set<Prenda> prendas) {
		return prendas.stream().filter(p -> p.esInferior()).count() == 1;
	}

	private static Boolean tieneUnCalzado(Set<Prenda> prendas) {
		return prendas.stream().filter(p -> p.esCalzado()).count() == 1;
	}

	private static Integer cantidadDeAbrigos(Set<Prenda> prendas) {
		return (int) prendas.stream().filter(p -> p.esAbrigo()).count();
	}

}
