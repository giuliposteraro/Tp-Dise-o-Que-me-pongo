package domain.eventos;
import java.time.temporal.ChronoUnit;

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
	
	public void actualizarFecha(Evento evento) {	
		evento.fecha(evento.fecha().plus(cantidad, unidad));
	}

	
}