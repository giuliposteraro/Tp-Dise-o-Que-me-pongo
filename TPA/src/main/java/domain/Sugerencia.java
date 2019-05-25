package domain;

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

}
