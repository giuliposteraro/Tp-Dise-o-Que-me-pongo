package exceptions;

public class NoSePuedeCompartirGuardarropa extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NoSePuedeCompartirGuardarropa(String mensaje) {
		super(mensaje);
	}
}
