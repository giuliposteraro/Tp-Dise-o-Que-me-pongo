package exceptions;

public class NoSePuedeGenerarSugerencia extends RuntimeException {
	public NoSePuedeGenerarSugerencia(String mensaje){
		super(mensaje);
	}
}
