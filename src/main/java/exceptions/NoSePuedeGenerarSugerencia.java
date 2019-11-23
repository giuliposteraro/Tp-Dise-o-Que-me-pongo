package exceptions;

public class NoSePuedeGenerarSugerencia extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSePuedeGenerarSugerencia(String mensaje){
		super(mensaje);
	}
}
