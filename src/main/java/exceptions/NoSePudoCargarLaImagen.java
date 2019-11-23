package exceptions;

public class NoSePudoCargarLaImagen extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSePudoCargarLaImagen(String mensaje) {
		super(mensaje);
	}
}
