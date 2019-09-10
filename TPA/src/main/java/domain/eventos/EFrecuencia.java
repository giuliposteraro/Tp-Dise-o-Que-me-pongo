package domain.eventos;
import java.time.temporal.ChronoUnit;

import domain.Config;

public enum EFrecuencia{
	DIARIA(1,ChronoUnit.DAYS),
	SEMANAL(1,ChronoUnit.WEEKS),
	MENSUAL(1,ChronoUnit.MONTHS),
	ANUAL(1,ChronoUnit.YEARS),
	UNICA(0,ChronoUnit.DAYS);

	private final int cantidad;
	private final ChronoUnit unidad;
	
	EFrecuencia(int cantidad, ChronoUnit unidad){
		this.cantidad = cantidad;
		this.unidad = unidad;
	}
	
	public Evento crearProximoEvento(Evento evento) {	
		Evento nuevoEvento = evento.eventoActualizado(evento.getFecha().plus(cantidad, unidad));
		Config.instance().getRepositorioEventos().agregarEvento(nuevoEvento);
		return nuevoEvento;
	}

	
}