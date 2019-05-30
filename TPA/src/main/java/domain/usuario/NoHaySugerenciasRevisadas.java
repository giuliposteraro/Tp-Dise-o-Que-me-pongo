package domain.usuario;

public class NoHaySugerenciasRevisadas extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoHaySugerenciasRevisadas(String mensaje) {
		super(mensaje);
	}
}
