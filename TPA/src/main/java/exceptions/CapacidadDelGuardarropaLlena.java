package exceptions;

public class CapacidadDelGuardarropaLlena extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CapacidadDelGuardarropaLlena(String mensaje) {
		super(mensaje);
	}
}
