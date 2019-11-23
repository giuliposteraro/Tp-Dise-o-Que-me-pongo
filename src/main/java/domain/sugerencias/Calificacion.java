package domain.sugerencias;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calificacion {
	
	@Id @GeneratedValue
	private long id_calificacion;
	
	private Double superior;
	private Double inferior;
	private Double calzado;

	public Calificacion(Double superior, Double inferior, Double calzado) {
		this.superior = superior;
		this.inferior = inferior;
		this.calzado = calzado;
	}

	private Calificacion() {}
	
	public static List<Double> promedio(Collection<Calificacion> calificaciones) {
		try {
			return calificaciones.stream().map(c -> c.getCalificaciones()).reduce((a, b) -> vectorialSum(a, b))
					.orElse(Arrays.asList(0.0, 0.0, 0.0)).stream().map(s -> s / calificaciones.size())
					.collect(Collectors.toList());
		} catch (ArithmeticException e) {
			return Arrays.asList(0.0, 0.0, 0.0);
		}
	}

	private List<Double> getCalificaciones() {
		return Arrays.asList(superior, inferior, calzado);
	}

	private static List<Double> vectorialSum(List<Double> a, List<Double> b) {
		return a.stream().map(x -> x + b.get(a.indexOf(x))).collect(Collectors.toList());
	}

	public Double getSuperior() {
		return superior;
	}

	public void setSuperior(Double superior) {
		this.superior = superior;
	}
}
