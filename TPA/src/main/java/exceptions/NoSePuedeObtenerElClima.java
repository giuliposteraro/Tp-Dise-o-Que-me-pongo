package exceptions;

public class NoSePuedeObtenerElClima extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSePuedeObtenerElClima(String mensaje){
		super(mensaje);
	}
}
