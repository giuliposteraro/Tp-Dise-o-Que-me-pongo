package domain.eventos;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public enum Frecuencia{
	DIARIA(1,ChronoUnit.DAYS), SEMANAL(1,ChronoUnit.WEEKS), MENSUAL(1,ChronoUnit.MONTHS), ANUAL(1,ChronoUnit.YEARS), UNICA(0,ChronoUnit.DAYS);

	private final int cantidad;
	private final ChronoUnit unidad;
	
	Frecuencia(int cantidad, ChronoUnit unidad){
		this.cantidad = cantidad;
		this.unidad = unidad;
	}
	
	public void actualizarFecha(Evento event) {	
		event.fecha(event.fecha.plus(cantidad, unidad));
	}

	
}