package domain.sugerencias;

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
		//Campana de Gauss
		int toleranciaAlFrio = evento.getUsuario().getToleranciaAlFrio();
		//consultar con mauro como modificar la funcion con este parametro.
		return Math.pow(Math.E, (-Math.pow(this.getNivelAbrigo() - (30.0 - temp * 1.25), 2) / 10));
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
		if(cal > 5)
			cal = 5;
		else if(cal < -5)
			cal = -5;
		else
			calificacion = cal;
	}
}
