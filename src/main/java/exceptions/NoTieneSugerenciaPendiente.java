package exceptions;

public class NoTieneSugerenciaPendiente extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoTieneSugerenciaPendiente(String mensaje){
		super(mensaje);
	}
}
