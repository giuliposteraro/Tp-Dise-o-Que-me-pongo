package domain.sugerencias;

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
	@OneToOne
	private Atuendo atuendo;
	@Enumerated(EnumType.STRING)
	private EstadoSugerencia estado;
	@ManyToOne
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
