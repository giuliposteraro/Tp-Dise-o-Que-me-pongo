package domain;
import java.time;

public class Evento {
	
	Guardarropa guardarropa;
	Usuario usuario;
	LocalDate fecha;
	String lugar;
	String motivo;
	
	public boolean esProximo() {  // es próximo si el evento es hoy mismo
		return fecha == LocalDate.now();
	}
	
	public void sugerir() {   
		Sugeridor unSugeridor = new Sugeridor(usuario,guardarropa,/*proveedorClima*/,motivo);
		unSugeridor.generarSugerencia();
	}
	
	
	
	
	
	
	
	
	
}