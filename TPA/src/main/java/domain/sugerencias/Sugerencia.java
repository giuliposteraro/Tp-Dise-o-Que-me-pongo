package domain.sugerencias;

import java.util.List;

import com.google.common.collect.Streams;

import domain.algoritmoSugeridor.CampanaDeGauss;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;

public class Sugerencia {

	private Atuendo atuendo;
	private EstadoSugerencia estado;
	private Evento evento;
	private List<Integer> calificaciones;

	public Sugerencia(Atuendo atuendo, Evento evento) {
		this.atuendo = atuendo;
		this.evento = evento;
		this.estado = EstadoSugerencia.PENDIENTE;
	}

	public void setEstado(EstadoSugerencia estado) {
		this.estado = estado;
	}

	public Double coeficienteDeAbrigo(Double temp) { // TODO corregir todo
		List<Double> toleranciasAlFrio = evento.getUsuario().getToleranciasAlFrio();
		List<Double> nivelesAbrigo = this.getNivelesAbrigo();
		Double coeficiente = Streams
				.zip(toleranciasAlFrio.stream(), nivelesAbrigo.stream(), (t, a) -> this.operarGauss(t, a, temp))
				.mapToDouble(num -> num).sum();
		return coeficiente;
	}

	private Double operarGauss(Double t, Double a, Double temp) {
		return new CampanaDeGauss().coeficienteDeAbrigo(a, temp, t);
	}

	public List<Double> getNivelesAbrigo() {
		return atuendo.getNivelesAbrigo();
	}

	public Atuendo getAtuendo() {
		return atuendo;
	}

	public EstadoSugerencia getEstado() {
		return estado;
	}

	public void ponerPrendasEnUso() {
		atuendo.ponerPrendasEnUso();
	}

	public List<Integer> getCalificaciones() {
		return calificaciones;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setCalificaciones(List<Integer> califs) {
		calificaciones = califs;
	}

}
