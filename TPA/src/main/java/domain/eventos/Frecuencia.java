package domain.eventos;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public enum Frecuencia{
	DIARIA(1,ChronoUnit.DAYS), SEMANAL(1,ChronoUnit.WEEKS), MENSUAL(1,ChronoUnit.MONTHS), ANUAL(1,ChronoUnit.YEARS), UNICA(0,ChronoUnit.DAYS);

	private final int sumaDias;
	private final ChronoUnit unidad;
	
	Frecuencia(int sumaDias, ChronoUnit unidad){
		this.sumaDias = sumaDias;
		this.unidad = unidad;
	}
	
	public void actualizarFecha(Evento event) {	
		event.fecha.plus(sumaDias, unidad);
	}

	
}