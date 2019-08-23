package domain.sugerencias;

import domain.algoritmoSugeridor.CampanaDeGauss;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;

public class Sugerencia {

	private Atuendo atuendo;
	private EstadoSugerencia estado;
	private Evento evento;
	private int calificacion;
	
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
  
	public void ponerPrendasEnUso() {
		atuendo.ponerPrendasEnUso();
  }
  
	public int getCalificacion() {
		return calificacion;
	}
	

	public Evento getEvento() {
		return evento;
	}

	public void setCalificacion(int cal) {
		calificacion = cal;
	}
    

}
