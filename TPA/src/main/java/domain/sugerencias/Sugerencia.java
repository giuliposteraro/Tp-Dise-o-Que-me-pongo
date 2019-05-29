package domain.sugerencias;

import domain.Atuendo;
import domain.usuario.Usuario;

public class Sugerencia {

	Atuendo atuendo;
	Usuario usuario;
	EstadoSugerencia estado;
//	Evento evento;
	
	public Sugerencia(Atuendo atuendo, Usuario usuario) {
		this.atuendo = atuendo;
		this.usuario = usuario;
		this.estado = EstadoSugerencia.PENDIENTE;
	}

	public void setEstado(EstadoSugerencia estado) {
		this.estado = estado;
	}
	
	public Double coeficienteDeAbrigo(Double temp) {
		//Campana de Gauss
		return Math.pow(Math.E, (-Math.pow(this.getNivelAbrigo() - (35.0 - temp), 2) / 10));
	}

	public Double getNivelAbrigo() {
		return atuendo.getNivelAbrigo();
	}
	
	public Atuendo getAtuendo() {
		return atuendo;
	}
}