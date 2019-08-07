package domain.sugerencias;

import domain.algoritmoSugeridor.CampanaDeGauss;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;

public class Sugerencia {

	Atuendo atuendo;
	EstadoSugerencia estado;
	Evento evento;
	int calificacion;
	
	public Sugerencia(Atuendo atuendo, Evento evento) {
		this.atuendo = atuendo;
		this.evento = evento;
		this.estado = EstadoSugerencia.PENDIENTE;
	}

	public void setEstado(EstadoSugerencia estado) {
		this.estado = estado;
	}
	
	public Double coeficienteDeAbrigo(Double temp) {
		Double toleranciaAlFrio = evento.getUsuario().getToleranciaAlFrio();		
		return new CampanaDeGauss().coeficienteDeAbrigo(this.getNivelAbrigo(), temp, toleranciaAlFrio);
	}

	public Double getNivelAbrigo() {
		return atuendo.getNivelAbrigo();
	}
	
	public Atuendo getAtuendo() {
		return atuendo;
	}

	public EstadoSugerencia getEstado() {
		return estado;
	}
	
	public int getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(int cal) {
		calificacion = cal;
	}
}
