package exceptions;

public class FechasInvalidas extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FechasInvalidas(String mensaje) {
		super(mensaje);
	}
}