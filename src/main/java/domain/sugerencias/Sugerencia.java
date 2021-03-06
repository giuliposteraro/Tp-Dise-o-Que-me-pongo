package domain.sugerencias;

import java.util.List;

import com.google.common.collect.Streams;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import domain.algoritmoSugeridor.CampanaDeGauss;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;


@Entity
public class Sugerencia {
	@Id @GeneratedValue
	private Long id_sugerencia;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Atuendo atuendo;
	@Enumerated(EnumType.STRING)
	private EstadoSugerencia estado;
	@ManyToOne
	public Evento evento;
	@OneToOne(optional=true)
	private Calificacion calificacion;

	public Sugerencia(Atuendo atuendo, Evento evento) {
		this.atuendo = atuendo;
		this.evento = evento;
		this.estado = EstadoSugerencia.PENDIENTE;
	}
	
	private Sugerencia() {}
	
	public void setEstado(EstadoSugerencia estado) {
		this.estado = estado;
	}

	public Double coeficienteDeAbrigo(Double temp) {
		List<Double> toleranciasAlFrio = evento.getUsuario().getToleranciasAlFrio();
		List<Double> nivelesAbrigo = this.getNivelesAbrigo();
		Double coeficiente = Streams
				.zip(toleranciasAlFrio.stream(), nivelesAbrigo.stream(), (t, a) -> this.operarGauss(t, a, temp))
				.mapToDouble(d -> Double.valueOf(d)).sum();
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
		atuendo.setPrendasEnUso(true);
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}
	
	public Long getId_sugerencia() {
		return id_sugerencia;
	}

	public Evento getEvento() {
		return evento;
	}

}
